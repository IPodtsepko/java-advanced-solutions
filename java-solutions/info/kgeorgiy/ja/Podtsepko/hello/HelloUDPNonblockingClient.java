package info.kgeorgiy.ja.Podtsepko.hello;

import info.kgeorgiy.ja.Podtsepko.hello.details.ClientBase;
import info.kgeorgiy.ja.Podtsepko.hello.details.NonblockingClient;

public class HelloUDPNonblockingClient extends ClientBase {
    /**
     * Entry point to the {@link HelloUDPNonblockingClient}.
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
        new HelloUDPNonblockingClient().run(args);
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
        try (final NonblockingClient client = new NonblockingClient(host, port, prefix, threads, requests)) {
            client.run();
        } catch (final NonblockingClient.ClientException exception) {
            System.err.println(exception.getLocalizedMessage());
            exception.printStackTrace();
        }
    }
}
