package info.kgeorgiy.ja.Podtsepko.i18n;

import info.kgeorgiy.ja.Podtsepko.i18n.statistics.Statistic;
import info.kgeorgiy.ja.Podtsepko.i18n.statistics.string.*;
import info.kgeorgiy.ja.Podtsepko.i18n.statistics.numbers.*;
import info.kgeorgiy.ja.Podtsepko.i18n.statistics.dates.*;

import org.junit.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import static org.junit.Assert.*;

import java.util.Locale;
import java.util.function.DoubleBinaryOperator;


public class TextStatisticsTest {
    public static void main(final String[] args) {
        final JUnitCore jUnitCore = new JUnitCore();
        jUnitCore.addListener(new TextListener(System.out));
        final Result result = jUnitCore.run(TextStatisticsTest.class);
        System.exit(result.getFailureCount());
    }

    @Test
    public void checkSentences() {
        final String text = "Hello, Igor! How are you?";

        final Statistic statistic = new Sentences(text, Locale.ENGLISH);

        assertEquals(2, statistic.getOccurrences());
        assertEquals(2, statistic.getUniq());
        assertEquals("Hello, Igor!", statistic.getMinimum());
        assertEquals("How are you?", statistic.getMaximum());
        assertEquals(12, statistic.getShortest().length());
        assertEquals(12, statistic.getLongest().length());
        assertEquals(Double.valueOf(12), Double.valueOf(statistic.getAverage())); // by length
    }

    @Test
    public void checkWords() {
        final String text = "Hello, Igor! Hello, Aleksey!";

        final Statistic statistic = new Words(text, Locale.ENGLISH);

        assertEquals(4, statistic.getOccurrences());
        assertEquals(3, statistic.getUniq());
        assertEquals("Aleksey", statistic.getMinimum());
        assertEquals("Igor", statistic.getMaximum());
        assertEquals("Igor", statistic.getShortest());
        assertEquals("Aleksey", statistic.getLongest());
        assertEquals(Double.valueOf((5.0 + 4.0 + 5.0 + 7.0) / 4), Double.valueOf(statistic.getAverage()));
    }

    @Test
    public void checkNumbers() {
        final String text = "1 2 2 2 3 4 5 6 1 1 1.0";

        final Statistic statistic = new Numbers(text, Locale.ENGLISH);

        assertEquals(11, statistic.getOccurrences());
        assertEquals(6, statistic.getUniq());
        assertEquals("1", statistic.getMinimum());
        assertEquals("6", statistic.getMaximum());
        assertEquals(1, statistic.getShortest().length());
        assertEquals(1, statistic.getLongest().length());
        assertEquals(Double.valueOf(2.545), Double.valueOf(statistic.getAverage()));
    }

    @Test
    public void datesAndCurrencies() {
        final String text = "I bought this laptop for $1,000 on Aug 5, 2020" +
                "(the case for it cost another $100.00).\n" +
                "In Sep 1, 2020 I entered the university. It's been almost 2 years since that time.";

        final Statistic dates = new Dates(text, Locale.US);

        assertEquals(2, dates.getOccurrences());
        assertEquals(2, dates.getUniq());
        assertEquals("Aug 5, 2020", dates.getMinimum());
        assertEquals("Sep 1, 2020", dates.getMaximum());
        assertEquals(11, dates.getShortest().length());
        assertEquals(11, dates.getLongest().length());
        assertEquals("Aug 18, 2020", dates.getAverage());

        final Statistic currencies = new Currencies(text, Locale.US);

        assertEquals(2, currencies.getOccurrences());
        assertEquals(2, currencies.getUniq());
        assertEquals("$100", currencies.getMinimum());
        assertEquals("$1,000", currencies.getMaximum());
        assertEquals("$100", currencies.getShortest());
        assertEquals("$1,000", currencies.getLongest());
        assertEquals("$550", currencies.getAverage());
    }

    @Test
    public void sentencesInKorea() {
        final String text =   "হ্যালো! হ্যালো! এই বাংলা। জাভা মনে করে এটা কোরিয়ান। কিন্তু... এটা বাংলা।";

        final Statistic statistic = new Sentences(text, Locale.KOREA);
        assertEquals(6, statistic.getOccurrences());
        assertEquals(5, statistic.getUniq());
        assertEquals("হ্যালো!", statistic.getShortest());
        assertEquals("জাভা মনে করে এটা কোরিয়ান।", statistic.getLongest());
    }

    @Test
    public void testMain() {
        TextStatistics.main("en-US", "text.in", "ru-RU", "text.out");
    }
}
