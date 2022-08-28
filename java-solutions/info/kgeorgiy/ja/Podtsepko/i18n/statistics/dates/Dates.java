package info.kgeorgiy.ja.Podtsepko.i18n.statistics.dates;

import info.kgeorgiy.ja.Podtsepko.i18n.statistics.Statistic;

import java.text.DateFormat;
import java.util.Locale;

public class Dates extends Statistic {
    /**
     * Parse text statistics for dates.
     *
     * @param data text
     * @param locale text locale
     */
    public Dates(final String data, final Locale locale) {
        super(data, DateFormat.getDateInstance(DateFormat.DEFAULT, locale), Dates::compare, Dates::val);
    }

    @Override
    protected String title() {
        return "dates";
    }

    private static int compare(final Object x, final Object y) {
        return ((java.util.Date) x).compareTo((java.util.Date) y);
    }

    private static double val(final Object object) {
        return ((java.util.Date) object).getTime();
    }
}
