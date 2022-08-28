package info.kgeorgiy.ja.Podtsepko.concurrent;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ParallelMapperImpl implements ParallelMapper {
    private final List<Thread> threads;
    private final Queue<Runnable> jobs;
    final static int MAX_SIZE = 10_000;

    private Thread newThread() {
        Thread thread = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    final Runnable job;
                    synchronized (jobs) {
                        while (jobs.isEmpty()) {
                            jobs.wait();
                        }
                        job = jobs.poll();
                        jobs.notify();
                    }
                    job.run();
                }
            } catch (final InterruptedException ignored) {
            } finally {
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        return thread;
    }

    /**
     * Creates Parallel Mapper instance.
     *
     * @param threads count of threads
     *
     * @throws IllegalArgumentException if threads less than 1.
     */
    public ParallelMapperImpl(final int threads) {
        if (threads < 1) {
            throw new IllegalArgumentException("threads should be greater than 1");
        }
        jobs = new ArrayDeque<>();
        this.threads = IntStream.range(0, threads).mapToObj(i -> newThread()).toList();
    }

    private static class JobResult<R> {
        final List<R> values;
        int completed;

        public JobResult(final int size) {
            this.values = new ArrayList<>(Collections.nCopies(size, null));
            completed = 0;
        }

        public void set(final int index, final R value) {
            values.set(index, value);
            synchronized (this) {
                if (++completed == values.size()) {
                    notify();
                }
            }
        }

        public synchronized List<R> get() throws InterruptedException {
            while (completed != values.size()) {
                wait();
            }
            return values;
        }
    }

    private boolean full() {
        return jobs.size() == MAX_SIZE;
    }

    @Override
    public <T, R> List<R> map(
            final Function<? super T, ? extends R> f,
            final List<? extends T> args) throws InterruptedException {
        final JobResult<R> result = new JobResult<>(args.size());
        for (int i = 0; i < args.size(); ++i) {
            synchronized (jobs) {
                while (full()) {
                    jobs.wait();
                }
                final int position = i;
                jobs.add(() -> result.set(position, f.apply(args.get(position))));
                jobs.notify();
            }
        }
        return result.get();
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (final InterruptedException e) {
                System.err.printf("Error: %s", e.getLocalizedMessage());
            }
        }
    }
}
