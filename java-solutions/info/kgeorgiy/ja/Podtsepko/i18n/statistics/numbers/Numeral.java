package info.kgeorgiy.ja.Podtsepko.i18n.statistics.numbers;

import info.kgeorgiy.ja.Podtsepko.i18n.statistics.Statistic;

import java.text.Format;
import java.util.Locale;

public abstract class Numeral extends Statistic {
    /**
     * Parse text statistics for numerals.
     *
     * @param data text
     * @param format text format
     */
    public Numeral(final String data, final Format format) {
        super(data, format, Numeral::compare, Numeral::val);
    }

    private static int compare(final Object x, final Object y) {
        return Double.compare(val(x), val(y));
    }

    private static double val(final Object object) {
        return Double.parseDouble(String.valueOf(object));
    }
}
