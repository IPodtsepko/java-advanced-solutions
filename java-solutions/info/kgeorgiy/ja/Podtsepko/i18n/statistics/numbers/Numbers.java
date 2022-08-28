package info.kgeorgiy.ja.Podtsepko.i18n.statistics.numbers;

import java.text.NumberFormat;
import java.util.Locale;

public class Numbers extends Numeral {
    /**
     * Parse text statistics for numbers.
     *
     * @param data text
     * @param locale text locale
     */
    public Numbers(final String data, final Locale locale) {
        super(data, NumberFormat.getNumberInstance(locale));
    }

    @Override
    protected String title() {
        return "numbers";
    }
}
