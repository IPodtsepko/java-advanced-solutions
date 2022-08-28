package info.kgeorgiy.ja.Podtsepko.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Represents remote bank.
 */
public class RemoteBank implements Bank {
    private final int port;
    private final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, RemotePerson> persons = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Set<Account>> personalAccounts = new ConcurrentHashMap<>();

    /**
     * Creates and export remote bank by provided port.
     *
     * @param port port for exporting
     */
    public RemoteBank(final int port) throws RemoteException {
        this.port = port;
        export(this);
    }

    /**
     * Exports {@link Remote} on port that specified when bank was created.
     *
     * @param remote object to export
     * @return provided object
     */
    private <T extends Remote> T export(final T remote) throws RemoteException {
        UnicastRemoteObject.exportObject(remote, port);
        return remote;
    }

    @Override
    public Account createAccount(final Person person, final String subId) throws RemoteException {
        final String id = Account.getId(person.getPassport(), subId);
        if (accounts.containsKey(id)) {
            return accounts.get(id);
        }
        return createAccount(person.getPassport(), id);
    }

    /**
     * Creates account with provided ID and belonging to
     * a person with the provided passport number.
     *
     * @param passport owner's passport number
     * @param id       account ID
     * @return created account
     */
    private Account createAccount(final String passport, final String id) throws RemoteException {
        final Account account = export(new RemoteAccount(id));
        accounts.put(id, account);
        return registerPersonalAccount(passport, account);
    }

    /**
     * Adds a record of the created account for the provided person.
     *
     * @param passport person number
     * @param account  created account
     * @return provided account
     */
    private Account registerPersonalAccount(final String passport, final Account account) {
        if (!personalAccounts.containsKey(passport)) {
            personalAccounts.put(passport, ConcurrentHashMap.newKeySet());
        }
        personalAccounts.get(passport).add(account);
        return account;
    }

    @Override
    public Account getAccount(final Person person, final String subId) throws RemoteException {
        return getAccount(person.getPassport(), subId);
    }

    @Override
    public Account getAccount(final String passport, String subId) throws RemoteException {
        return accounts.getOrDefault(Account.getId(passport, subId), null);
    }

    @Override
    public Set<Account> getAccounts(Person person) throws RemoteException {
        return personalAccounts.get(person.getPassport());
    }

    @Override
    public Person getRemotePerson(final String passport) throws RemoteException {
        return persons.getOrDefault(passport, null);
    }

    @Override
    public Person getLocalPerson(String passport) throws RemoteException {
        final Person person = getRemotePerson(passport);
        if (Objects.isNull(person)) {
            return null;
        }
        final Map<String, Account> accounts = new ConcurrentHashMap<>();
        for (final Account account : getAccounts(person)) {
            accounts.put(account.getId(), new LocalAccount(account));
        }
        return new LocalPerson(person, accounts);
    }

    @Override
    public boolean isKnownPerson(final String passport) throws RemoteException {
        return persons.containsKey(passport);
    }

    @Override
    public Person createPerson(String name,
                               String surname,
                               String passport) throws RemoteException {
        personalAccounts.put(passport, ConcurrentHashMap.newKeySet());
        final RemotePerson person = export(new RemotePerson(name, surname, passport));
        persons.put(passport, person);
        return person;
    }
}
