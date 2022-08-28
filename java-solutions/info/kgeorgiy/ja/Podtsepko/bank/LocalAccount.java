package info.kgeorgiy.ja.Podtsepko.bank;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Represents a bank account with the possibility of serialization
 */
public class LocalAccount extends AccountImpl implements Serializable {
    /**
     * Creates as a copy of another account to save its state.
     *
     * @param account accounts to copy
     */
    public LocalAccount(final Account account) throws RemoteException {
        super(account);
    }
}
