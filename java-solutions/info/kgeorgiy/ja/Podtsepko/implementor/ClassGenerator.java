package info.kgeorgiy.ja.Podtsepko.implementor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * An auxiliary class that provides tools for generating an interface implementation.
 *
 * @author Igor Podtsepko (i.podtsepko2002@gmail.com)
 */
final class ClassGenerator {
    /**
     * Empty string.
     */
    private static final String EMPTY = "";
    /**
     * Contains empty body of class, interface or method.
     */
    private static final String EMPTY_BODY = String.format("{}%n");
    /**
     * System-dependent line separator string.
     */
    private static final String EOL = System.lineSeparator();
    /**
     * A separator for enumerating function parameters containing a comma and a space.
     */
    private static final String COMMA_SEPARATOR = ", ";
    /**
     * Contains string representation of {@code false} boolean value.
     */
    private static final String FALSE = "false";
    /**
     * Contains string representation of {@code 0}.
     */
    private static final String ZERO = "0";
    /**
     * Contains string representation of {@code null}.
     */
    private static final String NULL = "null";

    /**
     * Auxiliary field for generating unique names of method parameters.
     */
    private static int parameterCounter;

    /**
     * Generates a class implementing the interface.
     *
     * @param token type token to create implementation for
     * @return string representation of class
     */
    public static String generate(final Class<?> token) {
        return String.format("%s%s {%n%s}%n", classPackage(token), classDeclaration(token), methods(token));
    }

    /**
     * Generates package declaration.
     *
     * @param token type token to create implementation for
     * @return package declaration
     */
    private static String classPackage(final Class<?> token) {
        if (Objects.isNull(token.getPackage())) {
            return EMPTY;
        }
        return String.format("%s;%n%n", token.getPackage());
    }

    /**
     * Generates a declaration of a class implementing the interface.
     *
     * @param token type token to create implementation for
     * @return class declaration
     */
    private static String classDeclaration(final Class<?> token) {
        return String.format("class %sImpl implements %s", token.getSimpleName(), token.getCanonicalName());
    }

    /**
     * Generates default implementation the all methods of interface separated empty lines.
     *
     * @param token type token to create implementation for
     * @return default implementation the all methods
     */
    private static String methods(final Class<?> token) {
        // :NOTE: filter abstract
        return String.join(EOL, Arrays.stream(token.getMethods()).map(ClassGenerator::method).toList());
    }

    /**
     * Generates default implementation of method.
     *
     * @param method method for implementation
     * @return default implementation of method
     */
    private static String method(final Method method) {
        return String.format(
                "\tpublic %s %s(%s) %s",
                method.getReturnType().getCanonicalName(),
                method.getName(),
                parameters(method.getParameterTypes()),
                methodBody(method.getReturnType())
        );
    }

    /**
     * Generates list of parameters for method signature.
     *
     * @param types type tokens of method parameters
     * @return parameters declaration
     */
    private static String parameters(final Class<?>[] types) {
        parameterCounter = 0;
        return String.join(COMMA_SEPARATOR, Arrays.stream(types).map(ClassGenerator::parameter).toList());
    }

    /**
     * Generates parameter declaration for method signature.
     *
     * @param type type token of parameter
     * @return parameter declaration for method signature
     */
    private static String parameter(final Class<?> type) {
        return String.format("%s parameter%d", type.getCanonicalName(), ++parameterCounter);
    }

    /**
     * Generates default method's body with return statement.
     *
     * @param returnType type token of return value type
     * @return generated method body
     */
    private static String methodBody(final Class<?> returnType) {
        if (returnType.equals(void.class)) {
            return EMPTY_BODY;
        }
        return String.format("{%n\t\treturn %s;%n\t}%n", defaultValue(returnType));
    }

    /**
     * Generates string representation of default value the appropriate type.
     *
     * @param type type to generate default value for
     * @return string representation of default value
     */
    private static String defaultValue(final Class<?> type) {
        if (type.equals(boolean.class)) {
            return FALSE;
        }
        if (type.isPrimitive()) {
            return ZERO;
        }
        return NULL;
    }
}
