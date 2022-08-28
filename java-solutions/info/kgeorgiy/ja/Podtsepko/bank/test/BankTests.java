package info.kgeorgiy.ja.Podtsepko.bank.test;

import info.kgeorgiy.ja.Podtsepko.bank.*;
import org.junit.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.MethodSorters;

import java.io.OutputStream;
import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankTests {

    private static Bank bank;

    private static final String NAME = "Igor";
    private static final String SURNAME = "Podtsepko";
    private static final String REMOTE_PERSON_PASSPORT = "123456@Remote";
    private static final String LOCAL_PERSON_PASSPORT = "123456@Local";
    private static final String MULTITHREADING_PERSON_PASSPORT = "123456@Bank";
    private static final int ACCOUNTS = 500;
    private static final int THREADS = 12;
    private static final int ITERATIONS = 1_000;

    public static void main(String[] args) {
        final JUnitCore jUnitCore = new JUnitCore();
        jUnitCore.addListener(new TextListener(System.out));
        final Result result = jUnitCore.run(BankTests.class);
        System.exit(result.getFailureCount());
    }

    @BeforeClass
    public static void before() throws RemoteException, NotBoundException {
        bank = (Bank) Server.start().lookup(Server.URL);
    }

    @Test
    public void test01_Remote_UnknownPerson() throws RemoteException {
        assertFalse(bank.isKnownPerson(REMOTE_PERSON_PASSPORT));
    }

    @Test
    public void test02_Remote_GetUnknownPerson() throws RemoteException {
        assertNull(bank.getRemotePerson(REMOTE_PERSON_PASSPORT));
    }

    @Test
    public void test03_Remote_CreatePerson() throws RemoteException {
        final Person person = bank.createPerson(NAME, SURNAME, REMOTE_PERSON_PASSPORT);
        assertNotNull(person);
        assertTrue(bank.isKnownPerson(REMOTE_PERSON_PASSPORT));

        assertEquals(person.getName(), NAME);
        assertEquals(person.getSurname(), SURNAME);
        assertEquals(person.getPassport(), REMOTE_PERSON_PASSPORT);
    }

    @Test
    public void test04_Remote_CreateAccounts() throws RemoteException {
        final Person person = bank.getRemotePerson(REMOTE_PERSON_PASSPORT);
        assertNotNull(person);

        final Set<Account> accounts = bank.getAccounts(person);
        assertTrue(accounts.isEmpty());

        final Account fst = bank.createAccount(person, "1");
        assertEquals(fst.getId(), "123456@Remote:1");
        assertEquals(fst.getAmount(), 0);

        final Account snd = bank.createAccount(person, "2");
        assertEquals(snd.getId(), "123456@Remote:2");
        assertEquals(snd.getAmount(), 0);

        final Set<Account> accountsAfter = bank.getAccounts(person);
        assertEquals(accountsAfter.size(), 2);

    }

    private Account incrementRemote(final String subId) throws RemoteException {
        final Person person = bank.getRemotePerson(REMOTE_PERSON_PASSPORT);
        assertNotNull(person);

        final Account account = bank.getAccount(person, subId);
        account.setAmount(account.getAmount() + 1);

        return account;
    }

    @Test
    public void test05_Remote_IncrementFirstBalance() throws RemoteException {
        final Account account = incrementRemote("1");
        assertEquals(account.getAmount(), 1);
    }

    @Test
    public void test06_Remote_IncrementSecondBalance() throws RemoteException {
        final Account account = incrementRemote("2");
        assertEquals(account.getAmount(), 1);
    }

    @Test
    public void test07_Remote_SecondIncrementFirstBalance() throws RemoteException {
        final Account account = incrementRemote("1");
        assertEquals(account.getAmount(), 2);
    }

    @Test
    public void test08_Local_UnknownPerson() throws RemoteException {
        assertFalse(bank.isKnownPerson(LOCAL_PERSON_PASSPORT));
    }

    @Test
    public void test09_Local_GetUnknownPerson() throws RemoteException {
        assertNull(bank.getLocalPerson(LOCAL_PERSON_PASSPORT));
    }

    @Test
    public void test10_Local_CreatePerson() throws RemoteException {
        final Person person = bank.createPerson(NAME, SURNAME, LOCAL_PERSON_PASSPORT);
        assertNotNull(person);
        assertTrue(bank.isKnownPerson(LOCAL_PERSON_PASSPORT));

        assertEquals(person.getName(), NAME);
        assertEquals(person.getSurname(), SURNAME);
        assertEquals(person.getPassport(), LOCAL_PERSON_PASSPORT);
    }

    @Test
    public void test11_Local_CreateAccounts() throws RemoteException {
        final LocalPerson person = (LocalPerson) bank.getLocalPerson(LOCAL_PERSON_PASSPORT);
        assertNotNull(person);

        final Set<Account> accounts = person.getAccounts();
        assertTrue(accounts.isEmpty());

        final Account fst = bank.createAccount(person, "1");
        assertEquals(fst.getId(), "123456@Local:1");
        assertEquals(fst.getAmount(), 0);

        fst.setAmount(1);

        final Account snd = bank.createAccount(person, "2");
        assertEquals(snd.getId(), "123456@Local:2");
        assertEquals(snd.getAmount(), 0);

        assertEquals(person.getAccounts().size(), 0);


        final Set<Account> accountsAfter = bank.getAccounts(person);
        assertEquals(accountsAfter.size(), 2);

        final LocalPerson updatedLocalPerson = (LocalPerson) bank.getLocalPerson(LOCAL_PERSON_PASSPORT);
        assertNotNull(person);
        assertEquals(updatedLocalPerson.getAccounts().size(), 2);

        assertEquals(updatedLocalPerson.getAccount("1").getAmount(), 1);
    }

    @Test
    public void test12_Application() throws RemoteException {
        final String subId = "ByApplication";

        System.setOut(new PrintStream(OutputStream.nullOutputStream()));
        Client.main(NAME, SURNAME, REMOTE_PERSON_PASSPORT, subId, "100");
        System.setOut(System.out);

        final Account created = bank.getAccount(REMOTE_PERSON_PASSPORT, subId);
        assertEquals(created.getId(), "123456@Remote:ByApplication");
        assertEquals(created.getAmount(), 100);

        Client.main(NAME, SURNAME, REMOTE_PERSON_PASSPORT, subId, "-50");
        assertEquals(created.getAmount(), 50);
    }

    @Test
    public void test13_Multithreading() throws RemoteException {
        final Person person = bank.createPerson(NAME, SURNAME, MULTITHREADING_PERSON_PASSPORT);
        assertNotNull(person);
        assertTrue(bank.isKnownPerson(MULTITHREADING_PERSON_PASSPORT));

        ExecutorService accountCreators = Executors.newFixedThreadPool(THREADS);
        final Phaser accountCreatorsPhaser = new Phaser(1);
        for (int i = 0; i < ACCOUNTS; i++) {
            final String subId = Integer.toString(i);
            accountCreatorsPhaser.register();
            accountCreators.submit(() -> {
                try {
                    bank.createAccount(person, subId);
                } catch (final RemoteException ignored) {
                }
                accountCreatorsPhaser.arriveAndDeregister();
            });
        }
        accountCreatorsPhaser.arriveAndAwaitAdvance();

        final List<Account> accounts = new ArrayList<>(bank.getAccounts(person));
        assertEquals(accounts.size(), ACCOUNTS);

        final List<ExecutorService> executors = new ArrayList<>();
        for (int i = 0; i < ACCOUNTS; i++) {
            executors.add(Executors.newFixedThreadPool(THREADS));
        }

        final Phaser phaser = new Phaser(1);
        for (int i = 0; i < THREADS; i++) {
            for (int j = 0; j < ACCOUNTS; j++) {
                final Account account = accounts.get(j);
                final int moneyCount = j * 2 + 1;
                phaser.register();
                executors.get(j).submit(() -> {
                    for (int k = 0; k < ITERATIONS; k++) {
                        try {
                            account.setAmount(account.getAmount() + moneyCount);
                        } catch (final RemoteException ignore) {
                        }
                    }
                    phaser.arriveAndDeregister();
                });
            }
        }
        phaser.arriveAndAwaitAdvance();

        for (int i = 0; i < ACCOUNTS; i++) {
            assertEquals(accounts.get(i).getAmount(), (2L * i + 1) * THREADS * ITERATIONS);
        }
    }
}
