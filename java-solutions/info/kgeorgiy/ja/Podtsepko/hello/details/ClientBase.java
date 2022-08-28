package info.kgeorgiy.ja.Podtsepko.hello.details;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.util.Arrays;
import java.util.Objects;

/**
 * Provides a tool to simplify the implementation of UDP clients.
 */
public abstract class ClientBase implements HelloClient {
    /**
     * @param prefix  message prefix
     * @param thread  number of the thread from which the message was sent
     * @param request number of the request
     * @return the message for {@link info.kgeorgiy.java.advanced.hello.HelloServer}.
     */
    public static String getMessage(final String prefix, final int thread, final int request) {
        return String.format("%s%d_%d", prefix, thread, request);
    }

    protected void run(String[] args) {
        Arguments arguments = new Arguments(args);
        if (!arguments.isValid()) {
            System.err.println("Expected not null arguments");
            return;
        }
        run(arguments.host(), arguments.port(), arguments.prefix(), arguments.threads(), arguments.requests());
    }

    private record Arguments(String[] args) {
        enum Name {
            HOST, PORT, PREFIX, THREADS, REQUESTS_PER_THREAD
        }

        public String get(final Name name) {
            return args[name.ordinal()];
        }

        public int getInt(final Name name) {
            if (name.ordinal() < args.length) {
                return Integer.parseInt(args[name.ordinal()]);
            }
            return 1;
        }

        public String host() {
            return get(Name.HOST);
        }

        public int port() {
            return getInt(Name.PORT);
        }

        public String prefix() {
            return get(Name.PREFIX);
        }

        public int threads() {
            return getInt(Name.THREADS);
        }

        public int requests() {
            return getInt(Name.REQUESTS_PER_THREAD);
        }

        public boolean isValid() {
            return args != null && 3 <= args.length && args.length <= 5 && Arrays.stream(args).allMatch(Objects::nonNull);
        }
    }
}
