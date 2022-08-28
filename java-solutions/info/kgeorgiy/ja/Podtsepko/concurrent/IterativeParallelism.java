package info.kgeorgiy.ja.Podtsepko.concurrent;

import info.kgeorgiy.java.advanced.concurrent.ScalarIP;
import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IterativeParallelism implements ScalarIP {

    private final ParallelMapper mapper;

    /**
     * Creates IterativeParallelism that does not use ParallelMapper.
     * The constructed object creates additional threads.
     */
    public IterativeParallelism() {
        mapper = null;
    }

    /**
     * Creates IterativeParallelism that does use ParallelMapper and
     * delegate tasks. The constructed object does not create
     * additional threads.
     *
     * @param mapper ParallelMapper to delegate tasks
     */
    public IterativeParallelism(ParallelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T maximum(
            final int threads,
            final List<? extends T> values,
            final Comparator<? super T> comparator
    ) throws InterruptedException {
        return minimum(threads, values, comparator.reversed());
    }

    @Override
    public <T> T minimum(
            final int threads, final List<? extends T> values, final Comparator<? super T> comparator) throws InterruptedException {
        return parallel(threads, values,
                stream -> stream.min(comparator).orElse(null),
                stream -> stream.min(comparator).orElse(null));
    }

    private <T, U> U parallel(
            final int threads,
            final List<? extends T> values,
            final Function<Stream<? extends T>, ? extends U> function,
            final Function<Stream<? extends U>, ? extends U> collector
    ) throws InterruptedException {
        if (threads < 1) {
            throw new IllegalArgumentException("threads should be greater than 1");
        }
        final List<Stream<? extends T>> ranges = split(threads, values);
        if (Objects.nonNull(mapper)) {
            return collector.apply(mapper.map(function, ranges).stream());
        }
        final List<U> results = new ArrayList<>(Collections.nCopies(ranges.size(), null));
        final List<Thread> threadList = IntStream.range(0, ranges.size()).mapToObj(
                i -> {
                    final Thread thread = new Thread(
                            () -> results.set(i, function.apply(ranges.get(i))));
                    // :NOTE: Stream.peak
                    // :FIX: move thread.start() to .peek(Thread::start)
                    return thread;
                }).peek(Thread::start).toList();
        for (final Thread thread : threadList) {
            thread.join();
        }
        return collector.apply(results.stream());
    }

    @Override
    public <T> boolean all(
            final int threads, final List<? extends T> values, final Predicate<? super T> predicate) throws InterruptedException {
        return parallel(threads, values, stream -> stream.allMatch(predicate), stream -> stream.allMatch(all -> all));
    }

    @Override
    public <T> boolean any(
            final int threads, final List<? extends T> values, final Predicate<? super T> predicate) throws InterruptedException {
        return !all(threads, values, predicate.negate());
    }

    private static int ceilDivide(final int x, final int y) {
        return (x + y - 1) / y;
    }

    private <T> List<Stream<? extends T>> split(int threads, final List<? extends T> values) {
        // :NOTE: Не ранвомерно
        // :FIX: Add dynamic size recalculation
        final List<Stream<? extends T>> streams = new ArrayList<>();
        for (int leftBound = 0, rightBound = 0; leftBound < values.size(); leftBound = rightBound) {
            rightBound += ceilDivide(values.size() - leftBound, threads--);
            streams.add(values.subList(leftBound, rightBound).stream());
        }
        return streams;
    }
}
