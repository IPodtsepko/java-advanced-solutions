package info.kgeorgiy.ja.Podtsepko.i18n.statistics.numbers;

import java.text.NumberFormat;
import java.util.Locale;

public class Currencies extends Numeral {
    /**
     * Parse text statistics for currencies.
     *
     * @param data text
     * @param locale text locale
     */
    public Currencies(final String data, final Locale locale) {
        super(data, getCurrencyFormat(locale));
    }

    private static NumberFormat getCurrencyFormat(final Locale locale) {
        var format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(20);
        format.setMaximumIntegerDigits(20);
        format.setMinimumFractionDigits(0);
        format.setMinimumIntegerDigits(0);
        return format;
    }

    @Override
    protected String title() {
        return "currencies";
    }
}
