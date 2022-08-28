package info.kgeorgiy.ja.Podtsepko.hello.details;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A UDP client providing a functionality similar to
 * {@link info.kgeorgiy.java.advanced.hello.HelloClient},
 * but separating responsibility for creating and running
 * the client.
 */
public class NonblockingClient implements AutoCloseable {
    private final String host;
    private final int port;
    private final String prefix;
    private final int threads;
    private final int requests;
    private final InetSocketAddress address;
    private final Selector selector;

    private static final long SO_TIMEOUT = 20;

    /**
     * Closes the selector.
     *
     * @throws ClientException if it can't close the selector
     */
    @Override
    public void close() throws ClientException {
        try {
            selector.close();
        } catch (final IOException exception) {
            throw new ClientException("cannot close selector", exception);
        }
    }

    /**
     * Represents a wrapper for any exception
     * caught while the client is running.
     */
    public class ClientException extends Exception {
        private ClientException(final String message, final Exception cause) {
            super(wrap(message), cause);
        }

        private ClientException(final int thread, final String message, final Exception cause) {
            super(wrap(thread, message), cause);
        }
    }

    /**
     * Creates client which will run with
     * provided parameters.
     *
     * @param host     server host
     * @param port     server port
     * @param prefix   request prefix
     * @param threads  number of request threads
     * @param requests number of requests per thread
     * @throws ClientException if provided unknown host or cannot open selector
     */
    public NonblockingClient(
            final String host,
            final int port,
            final String prefix,
            final int threads,
            final int requests) throws ClientException {
        this.host = host;
        this.port = port;
        this.prefix = prefix;
        this.threads = threads;
        this.requests = requests;
        try {
            this.address = new InetSocketAddress(InetAddress.getByName(host), port);
        } catch (final UnknownHostException exception) {
            throw new ClientException("unknown host", exception);
        }
        try {
            selector = Selector.open();
        } catch (final IOException exception) {
            throw new ClientException("cannot open selector", exception);
        }
    }

    /**
     * Connects and send requests.
     *
     * @throws ClientException if it is not possible to open, close,
     *                         configure and connect channels to the
     *                         server, send requests to the server,
     *                         receive responses, select keys in the
     *                         selector, etc.
     */
    public void run() throws ClientException {
        final List<DatagramChannel> channels = openChannels();
        try {
            while (!Thread.interrupted() && !selector.keys().isEmpty()) {
                final Set<SelectionKey> selected = selectKeys();
                if (selected.isEmpty()) {
                    markKeysWrite();
                } else {
                    process(selected);
                }
            }
        } finally {
            close(channels);
        }
    }

    private class Attachment {
        private final int thread;
        private final ByteBuffer data;
        private int request = 0;

        public Attachment(final int thread, final int size) {
            this.thread = thread;
            this.data = ByteBuffer.allocate(size);
        }

        public void send(final SelectionKey key) throws ClientException {
            final String message = ClientBase.getMessage(prefix, thread, request);
            final DatagramChannel channel = (DatagramChannel) key.channel();
            try {
                channel.send(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)), address);
            } catch (final IOException exception) {
                throw new ClientException(thread, "cannot send request", exception);
            }
            key.interestOps(SelectionKey.OP_READ);
        }

        public void receive(final SelectionKey key) throws ClientException {
            data.clear();
            final DatagramChannel channel = (DatagramChannel) key.channel();
            try {
                channel.receive(data);
            } catch (final IOException exception) {
                throw new ClientException(thread, "cannot receive response", exception);
            }
            data.flip();
            final String message = StandardCharsets.UTF_8.decode(data).toString();
            if (message.contains(ClientBase.getMessage(prefix, thread, request))) {
                key.interestOps(SelectionKey.OP_WRITE);
                request++;
            }
            if (request < requests) {
                return;
            }
            try {
                channel.close();
            } catch (final IOException exception) {
                throw new ClientException("cannot close channel", exception);
            }
        }
    }

    private List<DatagramChannel> openChannels() throws ClientException {
        final List<DatagramChannel> channels = new ArrayList<>();
        try {
            for (int i = 0; i < threads; i++) {
                channels.add(openChannel(i));
            }
        } catch (final ClientException exception) {
            close(channels);
            throw exception;
        }
        return channels;
    }

    private DatagramChannel openChannel(final int thread) throws ClientException {
        final DatagramChannel channel;
        try {
            channel = DatagramChannel.open();
        } catch (final IOException e) {
            throw new ClientException(thread, "cannot open datagram channel", e);
        }
        try {
            channel.configureBlocking(false);
        } catch (final IOException e) {
            throw new ClientException(thread, "cannot configure blocking", e);
        }
        try {
            channel.connect(address);
        } catch (final IOException e) {
            throw new ClientException(thread, "cannot connect", e);
        }
        final int size;
        try {
            size = channel.socket().getReceiveBufferSize();
        } catch (final SocketException e) {
            throw new ClientException(thread, "cannot get receive buffer size", e);
        }
        try {
            channel.register(selector, SelectionKey.OP_WRITE, new Attachment(thread, size));
        } catch (final ClosedChannelException e) {
            throw new ClientException(thread, "cannot register channel", e);
        }
        return channel;
    }

    private void close(final List<DatagramChannel> channels) {
        for (final Channel channel : channels) {
            try {
                channel.close();
            } catch (final IOException e) {
                System.err.println(wrap("cannot close datagram channel"));
            }
        }
    }

    private Set<SelectionKey> selectKeys() throws ClientException {
        try {
            selector.select(SO_TIMEOUT);
        } catch (final IOException e) {
            throw new ClientException("cannot do selection keys", e);
        }
        return selector.selectedKeys();
    }

    private void markKeysWrite() {
        for (final SelectionKey key : selector.keys()) {
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    private void process(final Set<SelectionKey> keys) throws ClientException {
        final Iterator<SelectionKey> iterator = keys.iterator();
        while (iterator.hasNext()) {
            process(iterator);
        }
    }

    private void process(final Iterator<SelectionKey> iterator) throws ClientException {
        try {
            process(iterator.next());
        } finally {
            iterator.remove();
        }
    }

    private void process(final SelectionKey key) throws ClientException {
        final Attachment attachment = (Attachment) key.attachment();
        if (key.isReadable()) {
            attachment.receive(key);
        } else if (key.isWritable()) {
            attachment.send(key);
        }
    }

    private String wrap(final int thread, final String message) {
        return String.format("%s:%s (thread %d): %s", host, port, thread, message);
    }

    private String wrap(final String message) {
        return String.format("%s:%s: %s", host, port, message);
    }
}