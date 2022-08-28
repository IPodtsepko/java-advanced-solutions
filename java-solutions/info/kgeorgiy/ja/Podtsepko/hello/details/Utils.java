package info.kgeorgiy.ja.Podtsepko.hello.details;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Some convenient utils for working with UDP.
 */
public class Utils {
    /**
     * Wrapper over the {@link java.util.concurrent.Executors}.
     */
    public static class Executor {
        private final ExecutorService jobs;

        /**
         * Run task in single thread.
         *
         * @param task the task to submit
         */
        public Executor(final Runnable task) {
            jobs = Executors.newSingleThreadExecutor();
            jobs.submit(task);
        }

        /**
         * Creates fixed thread pool.
         *
         * @param threads thread pool size
         */
        public Executor(final int threads) {
            jobs = Executors.newFixedThreadPool(threads);
        }

        /**
         * Submits a Runnable task for execution.
         *
         * @param task the task to submit
         */
        public void submit(final Runnable task) {
            jobs.submit(task);
        }

        /**
         * Shutdown thread pool and wait {@link Long#MAX_VALUE} seconds or when tasks completed.
         */
        public void shutdown() {
            shutdown(Long.MAX_VALUE);
        }

        /**
         * Shutdown thread pool and wait timeout or when tasks completed.
         *
         * @param seconds the maximum time to wait in seconds
         */
        public void shutdown(final long seconds) {
            shutdown(seconds, TimeUnit.SECONDS);
        }

        /**
         * Shutdown thread pool and wait timeout or when tasks completed.
         *
         * @param timeout the maximum time to wait
         * @param unit    the time unit of the timeout argument
         */
        public void shutdown(final long timeout, final TimeUnit unit) {
            jobs.shutdown(); // Disable new tasks from being submitted
            try {
                // Wait a while for existing tasks to terminate
                if (!jobs.awaitTermination(60, TimeUnit.SECONDS)) {
                    jobs.shutdownNow(); // Cancel currently executing tasks
                    // Wait a while for tasks to respond to being cancelled
                    if (!jobs.awaitTermination(60, TimeUnit.SECONDS))
                        System.err.println("Pool did not terminate");
                }
            } catch (InterruptedException ex) {
                // (Re-)Cancel if current thread also interrupted
                jobs.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Run task in single thread.
     *
     * @param task the task to submit
     */
    public static void execute(final Runnable task) {
        new Executor(task);
    }

    /**
     * Returns string representation of packet data.
     *
     * @param packet packet with data
     * @return string representation of packet data
     */
    public static String getString(final DatagramPacket packet) {
        return getString(packet.getData(), packet.getOffset(), packet.getLength());
    }

    /**
     * Creates string in UTF-8 charset ({@link StandardCharsets#UTF_8}).
     *
     * @param data   the bytes to be decoded into characters
     * @param offset the index of the first byte to decode
     * @param length the number of bytes to decode
     * @return string in UTF-8
     */
    public static String getString(final byte[] data, final int offset, final int length) {
        return new String(data, offset, length, StandardCharsets.UTF_8);
    }

    /**
     * Converts {@link String} to {@link ByteBuffer}.
     *
     * @param data string for convert
     * @return buffer with provided data
     */
    public static ByteBuffer asBuffer(final String data) {
        return ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(final ByteBuffer buffer) {
        return StandardCharsets.UTF_8.decode(buffer.flip()).toString();
    }
}
