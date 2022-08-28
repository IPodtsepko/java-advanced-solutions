package info.kgeorgiy.ja.Podtsepko.hello;

import info.kgeorgiy.ja.Podtsepko.hello.details.AbstractServer;
import info.kgeorgiy.ja.Podtsepko.hello.details.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class HelloUDPNonblockingServer extends AbstractServer {
    private Selector selector;
    private DatagramChannel channel;
    private Queue<ResponseHandler> handlers;
    private int size;

    /**
     * Entry point to the {@link HelloUDPNonblockingServer}.
     * <p>
     * Usage: <code>HelloUDPNonblockingServer port [threads]</code>
     * Where:
     * <code>port</code> - server port;
     * <code>threads</code> - number of working threads.
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {
        new HelloUDPNonblockingServer().start(args);
    }

    private class ResponseHandler {
        private final SocketAddress address;
        private final String data;

        public ResponseHandler(final SocketAddress address, final String response) {
            this.address = address;
            this.data = response;
        }

        public void send() throws IOException {
            channel.send(Utils.asBuffer(data), address);
        }
    }

    @Override
    public void start(final int port, final int threads) {
        try {
            openSelector();
            configureChannel(port);
        } catch (final ServerException exception) {
            exception.log("Cannot start server");
            close();
            return;
        }
        handlers = new ConcurrentLinkedDeque<>();
        super.updateExecutors(threads);
    }

    private void openSelector() throws ServerException {
        try {
            selector = Selector.open();
        } catch (final IOException exception) {
            throw new ServerException("Cannot open selector", exception);
        }
    }

    private void configureChannel(final int port) throws ServerException {
        try {
            channel = DatagramChannel.open();
        } catch (final IOException exception) {
            throw new ServerException("cannot open datagram channel", exception);
        }
        try {
            channel.configureBlocking(false);
        } catch (final IOException exception) {
            throw new ServerException("cannot configure blocking of channel", exception);
        }
        try {
            size = channel.socket().getReceiveBufferSize();
        } catch (final SocketException exception) {
            throw new ServerException("cannot get receive buffer size for socket", exception);
        }
        try {
            channel.register(selector, SelectionKey.OP_READ);
        } catch (final ClosedChannelException exception) {
            throw new ServerException("cannot register socket in the channel", exception);
        }
        try {
            channel.bind(new InetSocketAddress(port));
        } catch (final Exception exception) {
            throw new ServerException("cannot bind channel with address", exception);
        }
    }

    private static class ServerException extends Exception {
        public ServerException(final String message, final Exception cause) {
            super(message, cause);
        }

        public void log(final String message) {
            System.err.printf("%s: '%s' ('%s')%n",
                    message,
                    getLocalizedMessage(),
                    getCause().getLocalizedMessage());
        }
    }

    @Override
    protected void main() {
        while (!channel.socket().isClosed() && !Thread.interrupted()) {
            try {
                processKeys();
            } catch (final ServerException exception) {
                exception.log("Cannot process keys");
            }
        }
    }

    private void processKeys() throws ServerException {
        try {
            selector.select();
        } catch (final IOException exception) {
            throw new ServerException("Cannot select keys", exception);
        }
        final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            final SelectionKey key = iterator.next();
            try {
                if (key.isReadable()) {
                    createHandler(key);
                } else if (key.isWritable()) {
                    send(key);
                }
            } finally {
                iterator.remove();
            }
        }
    }

    private void createHandler(final SelectionKey key) throws ServerException {
        final ByteBuffer data = ByteBuffer.allocate(size);
        try {
            final SocketAddress address = channel.receive(data);
            responders.submit(() -> {
                handlers.add(new ResponseHandler(address, responseBy(Utils.decode(data))));
                key.interestOps(SelectionKey.OP_WRITE);
                selector.wakeup();
            });
        } catch (final IOException exception) {
            throw new ServerException("Cannot receive data", exception);
        }
    }

    private void send(final SelectionKey key) throws ServerException {
        if (!handlers.isEmpty()) {
            try {
                handlers.poll().send();
            } catch (final IOException exception) {
                throw new ServerException("Cannot send response", exception);
            }
        }
        key.interestOpsOr(SelectionKey.OP_READ);
    }

    private static <T extends AutoCloseable> void close(final T closable) {
        try {
            closable.close();
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void close() {
        close(channel);
        close(selector);
        super.shutdown();
    }
}
