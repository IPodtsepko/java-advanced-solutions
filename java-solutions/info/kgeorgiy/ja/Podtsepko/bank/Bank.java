package info.kgeorgiy.ja.Podtsepko.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Represents bank.
 */
public interface Bank extends Remote {
    /**
     * Creates an account associated with the provided individuals
     * and the provided sub-identifier. The ID of the created account
     * will be {@code passport:subId}, where {@code passport} is
     * equals {@link Person#getPassport()} for provided person.
     *
     * @param person account owner
     * @param subId  account sub-identifier
     * @return created account
     */
    Account createAccount(final Person person, final String subId) throws RemoteException;

    /**
     * Searches for and returns the account of the specified
     * person with the provided SubID.
     *
     * @param person account owner
     * @param subId  account sub-identifier
     * @return found account
     */
    Account getAccount(final Person person, final String subId) throws RemoteException;

    /**
     * Searches for and returns the account of the person with
     * specified passport number with the provided SubID.
     *
     * @param passport passport number
     * @param subId    account sub-identifier
     * @return fount account
     */
    Account getAccount(final String passport, final String subId) throws RemoteException;

    /**
     * Returns all accounts of provided person.
     *
     * @param person accounts owner
     * @return set of person accounts
     */
    Set<Account> getAccounts(final Person person) throws RemoteException;

    /**
     * Returns {@code true} if bank has record about person with
     * provided passport number, {@code false} otherwise.
     *
     * @param passport person passport number
     */
    boolean isKnownPerson(final String passport) throws RemoteException;

    /**
     * Returns link to remote person with specific passport number.
     *
     * @param passport person's passport number
     */
    Person getRemotePerson(final String passport) throws RemoteException;

    /**
     * Returns local person by serialization mechanism.
     *
     * @param passport person's passport number
     */
    Person getLocalPerson(final String passport) throws RemoteException;

    /**
     * Creates record about individual by his data.
     *
     * @param name     person name
     * @param surname  person surname
     * @param passport person passport number
     * @return created person
     */
    Person createPerson(final String name, final String surname, final String passport) throws RemoteException;
}
