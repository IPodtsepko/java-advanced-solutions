package info.kgeorgiy.ja.Podtsepko.i18n.statistics.string;

import info.kgeorgiy.ja.Podtsepko.i18n.statistics.Statistic;

import java.text.*;
import java.util.Locale;

public abstract class Strings extends Statistic {
    /**
     * Parse text statistics for strings.
     *
     * @param data text
     * @param locale text locale
     */
    public Strings(final String data, final Locale locale, final BreakIterator it) {
        super(parseTokens(data, it), new StringFormat(), Collator.getInstance(locale), Strings::val);
    }

    private static class StringFormat extends Format {
        @Override
        public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
            return toAppendTo.append(String.valueOf(obj).substring(pos.getBeginIndex()));
        }

        @Override
        public Object parseObject(final String source, final ParsePosition pos) {
            final java.lang.String result = source.substring(pos.getIndex());
            pos.setIndex(source.length());
            return result;
        }
    }

    static private double val(final Object object) {
        return String.valueOf(object).length();
    }
}
