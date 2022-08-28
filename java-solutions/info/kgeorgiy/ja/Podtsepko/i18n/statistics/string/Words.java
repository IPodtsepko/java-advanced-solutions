package info.kgeorgiy.ja.Podtsepko.i18n.statistics.string;

import java.text.BreakIterator;
import java.util.Locale;

public class Words extends Strings {
    /**
     * Parse text statistics for words.
     *
     * @param data text
     * @param locale text locale
     */
    public Words(final java.lang.String data, final Locale locale) {
        super(data, locale, BreakIterator.getWordInstance(locale));
    }

    @Override
    protected java.lang.String title() {
        return "words";
    }
}
