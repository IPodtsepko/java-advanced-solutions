package info.kgeorgiy.ja.Podtsepko.i18n;

import info.kgeorgiy.ja.Podtsepko.i18n.statistics.Statistic;
import info.kgeorgiy.ja.Podtsepko.i18n.statistics.dates.Dates;
import info.kgeorgiy.ja.Podtsepko.i18n.statistics.numbers.*;
import info.kgeorgiy.ja.Podtsepko.i18n.statistics.string.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;

public class TextStatistics {

    private final static List<BiFunction<String, Locale, Statistic>> statistics = List.of(
            Sentences::new, Words::new, Numbers::new, Currencies::new, Dates::new
    );


    /**
     * Entry point to TextStatistics.
     *
     * @param args inputLanguageTag inputFile outputLanguageTag outputFile
     */
    public static void main(final String... args) {
        if (args == null || args.length != 4 || Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.err.println("Error: 4 non-null arguments were expected");
            return;
        }
        final Locale from = Locale.forLanguageTag(args[0]);

        final String data;
        try {
            data = Files.readString(Path.of(args[1]));
        } catch (final IOException e) {
            System.err.printf("Error: cannot read data from input file ('%s') - %s", args[1], e.getLocalizedMessage());
            return;
        }

        final Locale to = Locale.forLanguageTag(args[2]);
        final ResourceBundle bundle = ResourceBundle.getBundle("statistic", to);

        final StringBuilder result = new StringBuilder();
        statistics.stream().map(s -> s.apply(data, from)).map(s -> s.export(bundle, to)).forEach(result::append);

        try {
            Files.writeString(Path.of(args[3]), result.toString());
        } catch (final IOException e) {
            System.err.printf("Error: cannot write data to output file ('%s') - %s", args[3], e.getLocalizedMessage());
        }
    }
}
