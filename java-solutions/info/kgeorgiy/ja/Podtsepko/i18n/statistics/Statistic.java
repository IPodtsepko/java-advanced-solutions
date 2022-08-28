package info.kgeorgiy.ja.Podtsepko.i18n.statistics;

import java.text.BreakIterator;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public abstract class Statistic {
    private final Format format;

    private final int occurrences;
    private final int uniq;
    private final String minimum;
    private final String maximum;
    private final String shortest;
    private final String longest;
    private final String average;

    protected Statistic(final String data, final Format format,
                     final Comparator<Object> comparator,
                     final ToDoubleFunction<Object> value) {
        this(parseTokens(data, format), format, comparator, value);
    }

    protected Statistic(final List<Object> tokens, final Format format,
                     final Comparator<Object> comparator,
                     final ToDoubleFunction<Object> value) {
        this.format = format;
        final SortedSet<Object> uniques = new TreeSet<>(comparator);
        uniques.addAll(tokens);
        occurrences = tokens.size();
        uniq = uniques.size();
        minimum = formatted(uniques.isEmpty() ? null : uniques.first());
        maximum = formatted(uniques.isEmpty() ? null : uniques.last());
        shortest = formatted(uniques.stream().min(this::compareByLen).orElse(null));
        longest = formatted(uniques.stream().max(this::compareByLen).orElse(null));
        average = formatted(getAverage(tokens, value));
    }

    protected abstract String title();

    private class StatisticFormat {
        private final StringBuilder builder = new StringBuilder();
        private final Locale locale;
        private final ResourceBundle bundle;

        public StatisticFormat(final ResourceBundle bundle, final Locale locale) {
            this.bundle = bundle;
            this.locale = locale;
            line("{0} : {1}", get("statistic"), get(title())); // TODO re-use prefix
            line("{0} : {1,number}", get("occurrences"), occurrences);
            line("{0} : {1,number}", get("uniq"), uniq);
            line("{0} : {1}", get("minimum"), minimum);
            line("{0} : {1}", get("maximum"), maximum);
            line("{0} : {1,number} ({2})", get("shortest"), shortest.length(), shortest);
            line("{0} : {1,number} ({2})", get("longest"), longest.length(), longest);
            line("{0} : {1}", get("average"), average);
            builder.append(System.lineSeparator());
        }

        public String get() {
            return builder.toString();
        }

        private String get(final String key) {
            return bundle.getString(key);
        }

        private void line(final String format, final Object... objects) {
            builder.append(new MessageFormat(format, locale).format(objects)).append(System.lineSeparator());
        }
    }

    /**
     * @param bundle 'statistic' resource bundle
     * @param locale locale for output
     * @return text statistic
     */
    public String export(final ResourceBundle bundle, final Locale locale) {
        return new StatisticFormat(bundle, locale).get();
    }

    private int len(final Object object) {
        return object != null ? formatted(object).length() : 0;
    }

    private String formatted(final Object object) {
        return object != null ? format.format(object) : "-";
    }

    private int compareByLen(final Object x, final Object y) {
        return Integer.compare(len(x), len(y));
    }

    private Double getAverage(final List<Object> objects, final ToDoubleFunction<Object> value) {
        final double average = objects.stream().mapToDouble(value).average().orElse(Double.NaN);
        return Double.isNaN(average) ? null : average;
    }

    static protected List<Object> parseTokens(final String data, final BreakIterator it) {
        it.setText(data);
        int start = it.first();
        final List<String> tokens = new ArrayList<>();
        for (int end = it.next(); end != BreakIterator.DONE; start = end, end = it.next()) {
            tokens.add(data.substring(start, end).strip());
        }
        return tokens.stream()
                .filter(token -> !token.isEmpty())
                .filter(token -> token.length() > 1 || Character.isAlphabetic(token.charAt(0)))
                .collect(Collectors.toList());
    }

    static private List<Object> parseTokens(final String data, final Format format) {
        final List<Object> tokens = new ArrayList<>();
        final ParsePosition pos = new ParsePosition(0);
        while (pos.getIndex() < data.length()) {
            final Object token = format.parseObject(data, pos);
            if (token == null) {
                pos.setIndex(pos.getIndex() + 1);
            } else {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public int getUniq() {
        return uniq;
    }

    public String getMinimum() {
        return minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public String getShortest() {
        return shortest;
    }

    public String getLongest() {
        return longest;
    }

    public String getAverage() {
        return average;
    }
}
