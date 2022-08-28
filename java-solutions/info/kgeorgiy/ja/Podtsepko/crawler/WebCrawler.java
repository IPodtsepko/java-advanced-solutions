package info.kgeorgiy.ja.Podtsepko.crawler;

import info.kgeorgiy.java.advanced.crawler.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class WebCrawler implements Crawler {
    /**
     * Entry point to the <code>WebCrawler</code>.
     * <p>
     * Usage: <code>WebCrawler url [depth [downloads [extractors [perHost]]]]</code>
     * Where:
     * <code>url</code> - the first page of the crawl;
     * <code>depth</code> - crawl depth (1 by default);
     * <code>downloads</code> - maximum number of simultaneously loaded pages (1 by default);
     * <code>extractors</code> - the maximum number of pages from which links are extracted at the same time (1 by default);
     * <code>perHost</code> - maximum number of pages loaded simultaneously from one host (1 by default).
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {
        Arguments arguments = new Arguments(args);
        if (!arguments.isValid()) {
            System.err.println("Invalid arguments");
            return;
        }
        try (Crawler crawler = new WebCrawler(
                new CachingDownloader(), arguments.downloaders(), arguments.extractors(), arguments.perHost())
        ) {
            crawler.download(arguments.url(), arguments.depth());
        } catch (IOException e) {
            System.err.printf("Cannot create CachingDownloader %s", e.getLocalizedMessage());
        }
    }

    /**
     * Creates an WebCrawler with the specified parameters.
     *
     * @param downloader  the downloader that will be used to download pages
     * @param downloaders maximum number of simultaneously loaded pages
     * @param extractors  the maximum number of pages from which links are extracted at the same time
     * @param perHost     maximum number of pages loaded simultaneously from one host
     */
    @SuppressWarnings("unused")
    public WebCrawler(Downloader downloader, int downloaders, int extractors, int perHost) {
        if (perHost < downloaders) {
            throw new IllegalArgumentException("The 'perHost' restriction is not supported.");
        }
        this.downloader = downloader;
        this.downloadThreadPool = Executors.newFixedThreadPool(downloaders);
        this.extractionThreadPool = Executors.newFixedThreadPool(extractors);
    }

    private record Arguments(String[] args) {
        enum Name {
            URL, DEPTH, DOWNLOADERS, EXTRACTORS, PER_HOST
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

        public String url() {
            return get(Name.URL);
        }

        public int depth() {
            return getInt(Name.DEPTH);
        }

        public int downloaders() {
            return getInt(Name.DOWNLOADERS);
        }

        public int extractors() {
            return getInt(Name.EXTRACTORS);
        }

        public int perHost() {
            return getInt(Name.PER_HOST);
        }

        public boolean isValid() {
            return args != null && args.length >= 2 && Arrays.stream(args).allMatch(Objects::nonNull);
        }
    }

    /**
     * Downloads website up to specified depth.
     *
     * @param url   start <a href="http://tools.ietf.org/html/rfc3986">URL</a>
     * @param depth download depth
     * @return download result
     */
    @Override
    public Result download(final String url, final int depth) {
        final Set<String> successfully = ConcurrentHashMap.newKeySet();
        final Map<String, IOException> exceptions = new ConcurrentHashMap<>();

        final Set<String> visited = ConcurrentHashMap.newKeySet();
        visited.add(url);
        
        final Queue<String> bfsQueue = new ConcurrentLinkedDeque<>();
        bfsQueue.add(url);

        final Phaser commonPhaser = new Phaser(1); // "1" to register self
        for (int i = 0; i < depth; ++i) {
            final Queue<String> nextDepth = new ConcurrentLinkedDeque<>();
            for (final String anotherUrl : bfsQueue) {
                final int remained = depth - i;
                commonPhaser.register();
                downloadThreadPool.submit(() -> {
                    try {
                        final Document document = downloader.download(anotherUrl);
                        successfully.add(anotherUrl);
                        if (remained == 1) {
                            return;
                        }
                        commonPhaser.register();
                        extractionThreadPool.submit(() -> {
                            try {
                                for (String link : document.extractLinks()) {
                                    if (/*is not visited*/ visited.add(link)) {
                                        nextDepth.add(link);
                                    }
                                }
                            } catch (IOException e) {
                                System.err.printf("Cannot extract links from %s:%n%s", anotherUrl, e.getLocalizedMessage());
                            } finally {
                                commonPhaser.arriveAndDeregister();
                            }
                        });
                    } catch (IOException e) {
                        exceptions.put(anotherUrl, e);
                    } finally {
                        commonPhaser.arriveAndDeregister();
                    }
                });
            }
            commonPhaser.arriveAndAwaitAdvance();
            bfsQueue.clear();
            bfsQueue.addAll(nextDepth);
        }
        return new Result(new ArrayList<>(successfully), exceptions);
    }

    @Override
    public void close() {
        downloadThreadPool.shutdown();
        if (!downloadThreadPool.isShutdown()) {
            System.err.println("Cannot shutdown downloaders pool");
        }
        extractionThreadPool.shutdown();
        if (!extractionThreadPool.isShutdown()) {
            System.err.println("Cannot shutdown extractors pool");
        }
    }

    private final Downloader downloader;
    private final ExecutorService downloadThreadPool;
    private final ExecutorService extractionThreadPool;
}
