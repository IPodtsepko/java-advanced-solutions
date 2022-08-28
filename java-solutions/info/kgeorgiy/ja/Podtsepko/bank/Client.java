package info.kgeorgiy.ja.Podtsepko.bank;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;
import java.util.Objects;

/**
 * Application that provides access to a remote bank:
 * - change the amount of money in the account
 */
public final class Client {
    /**
     * Utility class.
     */
    private Client() {
    }

    /**
     * {@link Client#main(String...)} arguments parser
     */
    private record Arguments(String... args) {
        /**
         * Represents expected arguments
         */
        private enum Name {
            NAME, SURNAME, PASSPORT, SUB_ID, DELTA
        }

        /**
         * Returns {@code true} if arguments contains this positional argument.
         *
         * @param name positional argument name
         */
        private boolean has(final Name name) {
            return name.ordinal() < args.length;
        }

        /**
         * Returns positional argument as {@link String}.
         *
         * @param name positional argument name
         */
        public String get(final Name name) {
            return args[name.ordinal()];
        }

        /**
         * Return positional argument as {@link int} or default value
         * if argument is missed.
         *
         * @param name  positional argument name
         * @param value default value
         */
        public int getOfDefault(final Name name, final int value) {
            return has(name) ? Integer.parseInt(get(name)) : value;
        }

        /**
         * @return person name from provided arguments
         */
        public String name() {
            return get(Name.NAME);
        }

        /**
         * @return person surname from provided arguments
         */
        public String surname() {
            return get(Name.SURNAME);
        }

        /**
         * @return person passport number from provided arguments
         */
        public String passport() {
            return get(Name.PASSPORT);
        }

        /**
         * @return account sub-identifier from provided arguments
         */
        public String subId() {
            return get(Name.SUB_ID);
        }

        /**
         * @return changing the amount of money in the account
         */
        public int delta() {
            return getOfDefault(Name.DELTA, 0);
        }

        /**
         * @return {@code true} if arguments is not {@code null}
         */
        public boolean isValid() {
            return args != null && Arrays.stream(args).allMatch(Objects::nonNull);
        }

        /**
         * @return {@code true} if arguments has {@code null}
         */
        public boolean isInvalid() {
            return !isValid();
        }
    }

    /**
     * Entry point to {@link Client} application.
     * <p>
     * Usage: <code>java Client name surname passport subId [amount]</code>
     * Where:
     * <code>name</code> - person's name;
     * <code>surname</code> - person's surname;
     * <code>passport</code> - person's passport number;
     * <code>subId</code> - account's sub-identifier;
     * <code>amount</code> - changing the amount of money in the account.
     *
     * @param args Client's arguments
     */
    public static void main(final String... args) {
        final Arguments arguments = new Arguments(args);
        if (arguments.isInvalid()) {
            System.err.println("Expected not null arguments");
            return;
        }

        final Bank bank;

        try {
            try {
                bank = (Bank) LocateRegistry.getRegistry(Server.PORT).lookup(Server.URL);
            } catch (final NotBoundException e) {
                System.out.println("Bank is not bound");
                return;
            }

            final Person person;
            if (bank.isKnownPerson(arguments.passport())) {
                person = bank.getRemotePerson(arguments.passport());
            } else {
                person = bank.createPerson(arguments.name(), arguments.surname(), arguments.passport());
            }

            if (!person.getName().equals(arguments.name())) {
                System.out.printf("Error: Invalid person name '%s'%n", person.getName());
                return;
            }

            if (!person.getSurname().equals(arguments.surname())) {
                System.out.printf("Error: Invalid person surname '%s'%n", person.getSurname());
                return;
            }

            final Account account = bank.createAccount(person, arguments.subId());
            account.setAmount(account.getAmount() + arguments.delta());
            System.out.printf("Account '%s' contains %d money%n", account.getId(), account.getAmount());
        } catch (RemoteException e) {
            System.out.printf("Has no access to remote: %s%n", e.getLocalizedMessage());
        }
    }
}

