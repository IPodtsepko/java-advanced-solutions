package info.kgeorgiy.ja.Podtsepko.bank;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Represents the server running the {@link RemoteBank}
 * on '//localhost:8088/bank'.
 */
public final class Server {
    /**
     * The port on which the server starts.
     */
    public final static int PORT = 8088;
    /**
     * URL associated with the remote bank being created.
     */
    public final static String URL = "//localhost:" + PORT + "/bank";

    /**
     * @return link to remote bank on this server
     */
    public static Bank getBank() throws RemoteException, NotBoundException {
        return (Bank) LocateRegistry.getRegistry(PORT).lookup(URL);
    }

    /**
     * Starts the server and returns created registry.
     */
    public static Registry start() throws RemoteException {
        final Registry registry = LocateRegistry.createRegistry(PORT);
        registry.rebind(URL, new RemoteBank(PORT));
        return registry;
    }

    /**
     * Entry point to {@link Server}.
     */
    public static void main(final String... ignored) {
        try {
            start();
        } catch (final RemoteException e) {
            System.out.printf("Cannot export object: %s%n", e.getLocalizedMessage());
        }
        System.out.println("Server started...");
    }
}