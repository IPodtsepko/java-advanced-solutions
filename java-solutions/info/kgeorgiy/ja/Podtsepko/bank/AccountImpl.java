package info.kgeorgiy.ja.Podtsepko.bank;

import java.rmi.RemoteException;

/**
 * Basic {@link Account} implementation.
 */
class AccountImpl implements Account {
    private final String id;
    private int amount = 0;

    /**
     * Creates an account with a specific ID and a zero amount of money.
     *
     * @param id account identifier
     */
    public AccountImpl(final String id) {
        this.id = id;
    }

    /**
     * Creates as copy of other account.
     *
     * @param account account to copy
     */
    public AccountImpl(final Account account) throws RemoteException {
        this.id = account.getId();
        this.amount = account.getAmount();
    }

    @Override
    public String getId() throws RemoteException {
        return id;
    }

    @Override
    public synchronized int getAmount() throws RemoteException {
        return amount;
    }

    @Override
    public synchronized void setAmount(final int amount) throws RemoteException {
        this.amount = amount;
    }
}
