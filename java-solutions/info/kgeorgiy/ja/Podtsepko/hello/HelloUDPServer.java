package info.kgeorgiy.ja.Podtsepko.hello;

import info.kgeorgiy.ja.Podtsepko.hello.details.AbstractServer;
import info.kgeorgiy.ja.Podtsepko.hello.details.Utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class HelloUDPServer extends AbstractServer {
    /**
     * Entry point to the {@link HelloUDPServer}.
     * <p>
     * Usage: <code>HelloUDPServer port [threads]</code>
     * Where:
     * <code>port</code> - server port;
     * <code>threads</code> - number of working threads.
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {
        new HelloUDPServer().start(args);
    }

    /**
     * Starts a new Hello server.
     * This method should return immediately.
     *
     * @param port    server port.
     * @param threads number of working threads.
     */
    @Override
    public void start(final int port, final int threads) {
        if (!updateSocket(port)) {
            return;
        }
        super.updateExecutors(threads);
    }

    private boolean updateSocket(final int port) {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.err.printf("Cannot create socket: '%s'%n", e.getLocalizedMessage());
            return false;
        }
        try {
            receiveBufferSize = socket.getReceiveBufferSize();
        } catch (SocketException e) {
            System.err.printf("Cannot get receive buffer size: '%s'%n", e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    @Override
    protected void main() {
        while (!Thread.interrupted()) {
            final DatagramPacket packet = createPacket();
            try {
                socket.receive(packet);
            } catch (IOException e) {
                if (!socket.isClosed()) {
                    System.err.printf("Cannot receive packet: '%s'%n", e.getLocalizedMessage());
                }
                return;
            }
            responders.submit(() -> sendResponse(packet));
        }
    }

    private void sendResponse(final DatagramPacket packet) {
        packet.setData(responseBy(Utils.getString(packet)).getBytes(StandardCharsets.UTF_8));
        try {
            socket.send(packet);
        } catch (IOException e) {
            System.err.printf("Cannot send response: '%s'%n", e.getLocalizedMessage());
        }
    }

    /**
     * Stops server and deallocates all resources.
     */
    @Override
    public void close() {
        socket.close();
        super.shutdown();
    }

    private DatagramPacket createPacket() {
        return new DatagramPacket(new byte[receiveBufferSize], receiveBufferSize);
    }

    private DatagramSocket socket;
    private int receiveBufferSize;
}
