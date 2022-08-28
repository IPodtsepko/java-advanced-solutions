package info.kgeorgiy.ja.Podtsepko.bank;

/**
 * Represents a remote record about an individual.
 */
public class RemotePerson extends PersonImpl {
    /**
     * Creates a record about an individual based on his data.
     * Should be exported after creation.
     *
     * @param name     person name
     * @param surname  person surname
     * @param passport person passport
     */
    public RemotePerson(final String name, final String surname, final String passport) {
        super(name, surname, passport);
    }
}
