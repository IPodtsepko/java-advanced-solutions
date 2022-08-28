package info.kgeorgiy.ja.Podtsepko.walk;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.InvalidPathException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Objects;

@SuppressWarnings("StatementWithEmptyBody")
public class Walk {
    private static final int BUFFER_SIZE = 1024 * 8; // 8 KB
    private static final byte[] BUFFER = new byte[BUFFER_SIZE];
    private static final String NULL_HASH = HexFormat.of().formatHex(new byte[20]);
    private static final String ALGORITHM = "SHA-1";

    private static String processFile(String path, MessageDigest digest) {
        Path filePath;
        try {
            filePath = Path.of(path);
        } catch (InvalidPathException e) {
            return NULL_HASH;
        }
        try (DigestInputStream input = new DigestInputStream(Files.newInputStream(filePath), digest)) {
            while (input.read(BUFFER) >= 0) {
                // read file
            }
            MessageDigest messageDigest = input.getMessageDigest();
            return HexFormat.of().formatHex(messageDigest.digest());
            // :NOTE: SecurityException
            // :FIX: Removed processing SecurityException
        } catch (IOException e) {
            return NULL_HASH;
        }
    }

    private static Path safePathOf(String path, String pathName) {
        try {
            return Path.of(path);
        } catch (InvalidPathException e) {
            System.err.printf("Error: Invalid path to %s (%s)%n", path, e.getLocalizedMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.err.println("Error: expected input and output file names as arguments for Walk");
            return;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No such " + ALGORITHM + " algorithm");
            return;
        }

        // :NOTE: process paths. writer's directories.
        // :FIX: added processing paths and creating writer's directories.

        Path inputFilePath = safePathOf(args[0], "input");
        if (inputFilePath == null) {
            return;
        }

        Path outputFilePath = safePathOf(args[1], "output");
        if (outputFilePath == null) {
            return;
        }

        // :NOTE: getParent()
        Path parent = outputFilePath.getParent();
        if (!Objects.isNull(parent)) {
            try {
                Files.createDirectories(parent);
            } catch (IOException e) {
                System.err.printf("Error: cannot create directories to output file (%s)", e.getLocalizedMessage());
                return;
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(inputFilePath, StandardCharsets.UTF_8)) {
            try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8)) {
                String file;
                while ((file = reader.readLine()) != null) {
                    writer.write(processFile(file, digest) + " " + file);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.printf("Error: cannot open output file (%s)", e.getLocalizedMessage());
            }
        } catch (IOException e) {
            System.err.printf("Error: cannot open input file (%s)", e.getLocalizedMessage());
        }
    }
}
