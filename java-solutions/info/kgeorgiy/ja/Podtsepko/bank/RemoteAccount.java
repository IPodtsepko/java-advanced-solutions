package info.kgeorgiy.ja.Podtsepko.bank;

/**
 * Represents remote bank account.
 */
public class RemoteAccount extends AccountImpl {
    /**
     * Creates an account with a specific ID and a zero amount of money.
     * Should be exported after creation.
     *
     * @param id account identifier
     */
    public RemoteAccount(final String id) {
        super(id);
    }
}
