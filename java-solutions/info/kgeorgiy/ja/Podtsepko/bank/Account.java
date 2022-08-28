package info.kgeorgiy.ja.Podtsepko.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Represents bank account.
 */
public interface Account extends Remote {
    /**
     * Returns account identifier.
     *
     * @return account ID
     */
    String getId() throws RemoteException;

    /**
     * Returns amount of money at the account.
     *
     * @return amount of money at the account
     */
    int getAmount() throws RemoteException;

    /**
     * Sets amount of money at the account.
     *
     * @param amount amount of money to set
     */
    void setAmount(final int amount) throws RemoteException;

    /**
     * Returns the ID of the account belonging to an individual
     * with the provided passport number and having the provided
     * sub-identifier.
     *
     * @param passport account owner's passport number
     * @param subId    account sub-identifier
     * @return account identifier
     */
    static String getId(final String passport, final String subId) throws RemoteException {
        return String.join(":", passport, subId);
    }
}
