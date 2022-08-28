package info.kgeorgiy.ja.Podtsepko.i18n.statistics.string;

import java.text.BreakIterator;
import java.util.Locale;

public class Sentences extends Strings {
    /**
     * Parse text statistics for sentences.
     *
     * @param data text
     * @param locale text locale
     */
    public Sentences(final String data, final Locale locale) {
        super(data, locale, BreakIterator.getSentenceInstance(locale));
    }

    @Override
    protected String title() {
        return "sentences";
    }
}
