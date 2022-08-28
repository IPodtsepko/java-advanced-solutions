package info.kgeorgiy.ja.Podtsepko.hello.details;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractServer implements HelloServer {
    protected Utils.Executor responders;
    protected Utils.Executor loop;

    protected static String responseBy(final String data) {
        return String.format("Hello, %s", data);
    }

    protected void updateExecutors(final int threads) {
        responders = new Utils.Executor(threads);
        loop = new Utils.Executor(this::main);
    }

    protected void start(String[] args) {
        Arguments arguments = new Arguments(args);
        if (!arguments.isValid()) {
            System.err.println("Expected not null arguments");
            return;
        }
        start(arguments.port(), arguments.threads());
    }

    private record Arguments(String[] args) {
        enum Name {
            PORT, THREADS
        }

        public String get(final Arguments.Name name) {
            return args[name.ordinal()];
        }

        public int getInt(final Arguments.Name name) {
            if (name.ordinal() < args.length) {
                return Integer.parseInt(args[name.ordinal()]);
            }
            return 1;
        }

        public int port() {
            return getInt(Name.PORT);
        }

        public int threads() {
            return getInt(Name.THREADS);
        }

        public boolean isValid() {
            return args != null && args.length == 2 && Arrays.stream(args).allMatch(Objects::nonNull);
        }
    }

    protected abstract void main();

    protected void shutdown() {
        responders.shutdown();
        loop.shutdown();
    }
}
