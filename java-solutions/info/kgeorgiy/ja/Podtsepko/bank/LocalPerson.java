package info.kgeorgiy.ja.Podtsepko.bank;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents an individual with the possibility of serialization
 */
public class LocalPerson extends PersonImpl implements Serializable {
    private final Map<String, Account> accounts;

    /**
     * Creates a record about an individual based on his data and list of his accounts.
     *
     * @param name     person name
     * @param surname  person surname
     * @param passport person passport number
     * @param accounts list of person accounts
     */
    public LocalPerson(final String name,
                       final String surname,
                       final String passport,
                       final Map<String, Account> accounts) {
        super(name, surname, passport);
        this.accounts = accounts;
    }

    public LocalPerson(final Person person,
                       final Map<String, Account> accounts) throws RemoteException {
        this(person.getName(), person.getSurname(), person.getPassport(), accounts);
    }

    /**
     * Provides access to the specific account of this person.
     *
     * @param subId account sub-identifier
     * @return person account
     */
    public Account getAccount(final String subId) throws RemoteException {
        return accounts.getOrDefault(Account.getId(passport, subId), null);
    }

    /**
     * Returns all saved accounts of this person.
     *
     * @return set of person's accounts.
     */
    public Set<Account> getAccounts() throws RemoteException {
        return new HashSet<>(accounts.values());
    }
}
