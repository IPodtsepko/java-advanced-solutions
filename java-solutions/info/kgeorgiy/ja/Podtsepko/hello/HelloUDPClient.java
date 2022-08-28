package info.kgeorgiy.ja.Podtsepko.hello;

import info.kgeorgiy.ja.Podtsepko.hello.details.ClientBase;
import info.kgeorgiy.ja.Podtsepko.hello.details.Utils;
import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class HelloUDPClient extends ClientBase implements HelloClient {
    /**
     * Entry point to the {@link HelloUDPClient}.
     * <p>
     * Usage: <code>HelloUDPClient host port prefix [threads [requests]]</code>
     * Where:
     * <code>host</code> - server host;
     * <code>port</code> - server port;
     * <code>prefix</code> - request prefix;
     * <code>threads</code> - number of request threads;
     * <code>requests</code> - number of requests per thread.
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {
        new HelloUDPClient().run(args);
    }

    /**
     * Runs Hello client.
     * This method should return when all requests completed.
     *
     * @param host     server host
     * @param port     server port
     * @param prefix   request prefix
     * @param threads  number of request threads
     * @param requests number of requests per thread.
     */
    @Override
    public void run(final String host, final int port, final String prefix, final int threads, final int requests) {
        final InetAddress address;
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.err.println(e.getLocalizedMessage());
            return;
        }
        final Utils.Executor jobs = new Utils.Executor(threads);
        for (int i = 0; i < threads; i++) {
            final int thread = i;
            jobs.submit(() -> {
                try (final DatagramSocket socket = createSocket()) {
                    final ClientConnection connection = new ClientConnection(socket, address, port);
                    for (int request = 0; request < requests; request++) {
                        final String data = getMessage(prefix, thread, request);
                        connection.tryingToSend(data);
                    }
                } catch (SocketException e) {
                    System.err.printf("Cannot make requests in thread '%d': '%s'%n", thread, e.getLocalizedMessage());
                }
            });
        }
        final long timeout = SHUTDOWN_TIMEOUT * threads * requests;
        jobs.shutdown(timeout);
    }

    private static class ClientConnection {
        private final DatagramSocket socket;
        private final byte[] emptyBuf;
        private final DatagramPacket packet;

        public ClientConnection(final DatagramSocket socket, final InetAddress address, final int port) throws SocketException {
            this.socket = socket;
            final int length = socket.getSendBufferSize();
            emptyBuf = new byte[length];
            packet = new DatagramPacket(emptyBuf, length, address, port);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        public void tryingToSend(final String data) {
            while (!trySend(data)) ;
        }

        public boolean trySend(final String data) {
            try {
                send(data);
            } catch (IOException e) {
                System.err.printf("Cannot send data: '%s'%n", e.getLocalizedMessage());
                return false;
            }
            try {
                return response().contains(data);
            } catch (final IOException e) {
                System.err.printf("Cannot receive response: '%s'%n", e.getLocalizedMessage());
            }
            return false;
        }

        public void send(final String data) throws IOException {
            packet.setData(data.getBytes(StandardCharsets.UTF_8));
            socket.send(packet);
        }

        public String response() throws IOException {
            packet.setData(emptyBuf);
            socket.receive(packet);
            return Utils.getString(packet);
        }
    }

    private static DatagramSocket createSocket() throws SocketException {
        final DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(SO_TIMEOUT);
        return socket;
    }

    protected static final int SO_TIMEOUT = 100;
    private static final long SHUTDOWN_TIMEOUT = 1L;
}
