package info.kgeorgiy.ja.Podtsepko.bank;

import java.rmi.RemoteException;

/**
 * Basic {@link Person} implementation.
 */
class PersonImpl implements Person {
    final String name;
    final String surname;
    final String passport;

    /**
     * Creates a record about an individual based on his data.
     *
     * @param name     person name
     * @param surname  person surname
     * @param passport person passport
     */
    public PersonImpl(final String name, final String surname, final String passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getSurname() throws RemoteException {
        return surname;
    }

    @Override
    public String getPassport() throws RemoteException {
        return passport;
    }
}
