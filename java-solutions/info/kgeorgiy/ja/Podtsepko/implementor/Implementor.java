package info.kgeorgiy.ja.Podtsepko.implementor;

import info.kgeorgiy.java.advanced.implementor.Impler;
import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.JarImpler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

/**
 * Implementation of interfaces {@link Impler} and {@link JarImpler},
 * providing tools for automating the implementation of interfaces,
 * compiling the default implementation and generating <var>.jar</var>
 * files with corresponding classes.
 *
 * @author Igor Podtsepko (i.podtsepko2002@gmail.com)
 */
public class Implementor implements Impler, JarImpler {
    /**
     * Working directory of Implementor program.
     */
    private static final Path WORKING_DIRECTORY = Path.of("");
    /**
     * Format of string for encode char in Unicode
     */
    private static final String UNICODE_FORMAT = "\\u%04X";

    /**
     * Entry point of {@link Implementor}.
     * <p>
     * Accepts one argument - the name of the interface to implement, or three
     * arguments - {@code -jar}, the name of the interface to implement, and
     * the name of the jar file.
     *
     * @param args Implementor's arguments
     */
    public static void main(String[] args) {
        if (!isValidImplementorArguments(args)) {
            return;
        }
        try {
            final Implementor implementor = new Implementor();
            if (args.length == 1) {
                implementor.implement(getClassLoader(WORKING_DIRECTORY).loadClass(args[0]), WORKING_DIRECTORY);
            } else {
                implementor.implement(getClassLoader(WORKING_DIRECTORY).loadClass(args[1]), Path.of(args[2]));
            }
        } catch (ClassNotFoundException e) {
            System.err.printf("Invalid class name: %s", e.getLocalizedMessage());
        } catch (ImplerException e) {
            System.err.printf("Cannot implement interface:%n%s", e.getLocalizedMessage());
        } catch (MalformedURLException e) {
            System.err.printf("Invalid working directory:%n%s", WORKING_DIRECTORY);
        }
    }

    /**
     * Returns string in unicode.
     *
     * @param string string for encoding
     * @return string in Unicode
     */
    private static String encode(final String string) {
        return string.chars().mapToObj(c -> String.format(UNICODE_FORMAT, c)).collect(Collectors.joining());
    }

    /**
     * Checks that the Implementor's arguments are not null and that they satisfy some mode.
     *
     * @param args Implementor's arguments
     * @return {@code true} if arguments is valid, {@code false} otherwise
     */
    private static boolean isValidImplementorArguments(String[] args) {
        if (Objects.isNull(args)) {
            System.err.println("Invalid arguments for Implementor (null args)");
            return false;
        }
        if (Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.err.println("Invalid arguments for Implementor (some arguments is null)");
            return false;
        }
        if (args.length == 1 || args.length == 3 && Objects.equals(args[0], "-jar")) {
            return true;
        }
        System.err.println("Invalid arguments for Implementor");
        System.err.println("Use \"<class name>\" or \"-jar <class name> <jar file name>\"");
        return false;
    }

    /**
     * Checks that the {@code token} is public interface and {@code root} is not null.
     *
     * @param token type token to create implementation for
     * @param root  root directory
     * @throws ImplerException If some argument is invalid.
     */
    private static void check(final Class<?> token, final Path root) throws ImplerException {
        if (Objects.isNull(token)) {
            throw new ImplerException("Expected not null token");
        }
        if (!token.isInterface()) {
            throw new ImplerException(
                    String.format("Token is not interface:%s%n", token)
            );
        }
        if (Modifier.isPrivate(token.getModifiers())) {
            throw new ImplerException(
                    String.format("Token is private:%s%n", token)
            );
        }
        if (Objects.isNull(root)) {
            throw new ImplerException("Expected not null path");
        }
    }

    /**
     * Produces code implementing class or interface specified by provided {@code token}.
     * <p>
     * The suffix {@code Impl} is added to the interface name.
     * The generated source code is placed in the specified directory {@code root}.
     * For example, the implementation of the interface {@link java.util.List list}
     * will be placed in {@code $root/java/util/ListImpl.java}.
     *
     * @param token type token to create implementation for
     * @param root  root directory
     * @throws info.kgeorgiy.java.advanced.implementor.ImplerException when implementation cannot be
     *                                                                 generated.
     */
    @Override
    public void implement(final Class<?> token, final Path root) throws ImplerException {
        check(token, root);
        Path classFile = getClassFilePath(token, root);
        try (BufferedWriter writer = Files.newBufferedWriter(classFile, StandardCharsets.UTF_8)) {
            writer.write(encode(ClassGenerator.generate(token)));
        } catch (IOException e) {
            throw new ImplerException(String.format("Output file invalid:%n%s", e.getLocalizedMessage()));
        }
    }

    /**
     * Creates and returns ClassLoader for the root directory
     *
     * @param root root directory
     * @return ClassLoader for the root directory
     */
    public static URLClassLoader getClassLoader(final Path root) throws MalformedURLException {
        return new URLClassLoader(new URL[]{root.toUri().toURL()});
    }

    /**
     * Creates the path to the file where the implementation should be placed
     * and creates directories from this path.
     *
     * @param token type token to create implementation for
     * @param root  root directory
     * @return the path to the file where the implementation should be placed
     * @throws ImplerException if it is not possible to create directories from the file path.
     */
    private static Path getClassFilePath(final Class<?> token, final Path root) throws ImplerException {
        Path classFilePath = root.resolve(getPathToImpl(token));
        Path parent = classFilePath.getParent();
        if (!Objects.isNull(parent)) {
            try {
                Files.createDirectories(parent);
            } catch (IOException e) {
                throw new ImplerException(String.format("Cannot create directories:%n%s", e.getLocalizedMessage()));
            }
        }
        return classFilePath;
    }

    /**
     * Returns relative path to file where the implementation should be placed.
     *
     * @param token type token to create implementation for
     * @return relative path to file where the implementation should be placed
     */
    private static Path getPathToImpl(final Class<?> token) {
        return Path.of(
                token.getPackageName().replace('.', File.separatorChar),
                String.format("%sImpl.java", token.getSimpleName())
        );
    }

    /**
     * Produces <var>.jar</var> file implementing class or interface specified by provided <var>token</var>.
     * <p>
     * The suffix {@code Impl} is added to the interface name.
     *
     * @param token   type token to create implementation for
     * @param jarFile target <var>.jar</var> file
     * @throws ImplerException when implementation cannot be generated.
     */
    public void implementJar(final Class<?> token, final Path jarFile) throws ImplerException {
        check(token, jarFile);
        try {
            Files.createDirectories(jarFile.getParent());
        } catch (IOException e) {
            throw new ImplerException(
                    String.format("Can not create directories to JAR-file:%n%s", jarFile)
            );
        }
        final Path build;
        try {
            build = Files.createTempDirectory(jarFile.toAbsolutePath().getParent(), "out");
        } catch (IOException e) {
            throw new ImplerException(
                    String.format("Can not create temporary dir:%n%s", e.getLocalizedMessage())
            );
        }
        implement(token, build);
        compile(token, build);
        export(token, build, jarFile);
    }


    /**
     * Compiles implemented interface by interface token (the interface should already be implemented).
     *
     * @param token  type token to create implementation for
     * @param output root directory for compiler output
     * @throws ImplerException if missing Java compiler or implementation cannot be compiled.
     */
    private void compile(final Class<?> token, final Path output) throws ImplerException {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (Objects.isNull(compiler)) {
            throw new ImplerException("Missing Java compiler");
        }
        try {
            final String implementation = output.resolve(getPathToImpl(token)).toString();
            final String classPath = Path.of(
                    token.getProtectionDomain().getCodeSource().getLocation().toURI()
            ).toString();
            final int exitCode = compiler.run(
                    null, null, null,
                    implementation, "--class-path", classPath);
            if (exitCode != 0) {
                throw new ImplerException("Compilation error");
            }
        } catch (URISyntaxException e) {
            throw new ImplerException(
                    String.format("Invalid URI:%n%s", e.getLocalizedMessage())
            );
        }
    }

    /**
     * Generate <var>.jar</var> with interface implementation.
     *
     * @param token   type token to create implementation for
     * @param build   root directory of compiled interface implementation
     * @param jarFile target <var>.jar</var> file
     * @throws ImplerException If there was some kind of {@link IOException}
     *                         while working with the <var>.jar</var> file
     */
    private void export(final Class<?> token, final Path build, final Path jarFile) throws ImplerException {
        try (final JarOutputStream jar = new JarOutputStream(Files.newOutputStream(jarFile))) {
            final String classFile = String.format(
                    "%s/%sImpl.class", token.getPackageName().replace('.', '/'), token.getSimpleName()
            );
            jar.putNextEntry(new ZipEntry(classFile));
            Files.copy(build.resolve(classFile), jar);
        } catch (IOException e) {
            throw new ImplerException(
                    String.format("Can't process JAR-file %s", e.getLocalizedMessage())
            );
        }
    }
}
