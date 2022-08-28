package info.kgeorgiy.ja.Podtsepko.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Represents an individual in a bank.
 */
public interface Person extends Remote {
    /**
     * Returns person name.
     *
     * @return person name
     */
    String getName() throws RemoteException;

    /**
     * Returns person surname.
     *
     * @return person surname
     */
    String getSurname() throws RemoteException;

    /**
     * Returns person passport number.
     *
     * @return person passport number
     */
    String getPassport() throws RemoteException;
}
