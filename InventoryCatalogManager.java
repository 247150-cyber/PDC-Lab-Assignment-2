import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryCatalogManager 
{

    // Configure benchmark settings.
    private static final int THREADS = 10;
    private static final int OPERATIONS = 50000;
    private static final int PRODUCTS = 1000;

    // Create and initialize the product catalog.
    private static Map<String, Integer> createCatalog(
            Map<String, Integer> catalog) {

        for (int i = 0; i < PRODUCTS; i++) {
            catalog.put("P" + i, 100);
        }

        return catalog;
    }

    // Benchmark a synchronized HashMap.
    public static void benchmarkSynchronizedMap()
            throws InterruptedException {

        Map<String, Integer> catalog =
                Collections.synchronizedMap(
                        createCatalog(new HashMap<>()));

        runBenchmark("SynchronizedMap", catalog);
    }

    // Benchmark a ConcurrentHashMap.
    public static void benchmarkConcurrentMap()
            throws InterruptedException {

        Map<String, Integer> catalog =
                createCatalog(new ConcurrentHashMap<>());

        runBenchmark("ConcurrentHashMap", catalog);
    }

    // Run concurrent read and update operations.
    private static void runBenchmark(
            String name, Map<String, Integer> catalog)
            throws InterruptedException {

        ExecutorService executor =
                Executors.newFixedThreadPool(THREADS);

        CountDownLatch ready = new CountDownLatch(THREADS);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(THREADS);

        AtomicInteger completed = new AtomicInteger(0);

        try {
            // Submit worker tasks to the executor.
            for (int t = 0; t < THREADS; t++) {
                final int workerId = t;

                executor.submit(() -> {
                    ready.countDown();

                    try {
                        // Wait until all workers are ready.
                        start.await();

                        for (int i = 0; i < OPERATIONS; i++) {
                            String productId =
                                    "P" + ((i + workerId) % PRODUCTS);

                            if (i % 2 == 0) {
                                catalog.get(productId);
                            } else {
                                catalog.compute(productId,
                                        (key, stock) ->
                                                stock == null ? 1 : stock + 1);
                            }

                            completed.incrementAndGet();
                        }

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();

                    } finally {
                        done.countDown();
                    }
                });
            }

            // Wait until every worker is ready.
            ready.await();

            long startTime = System.nanoTime();
            start.countDown();

            // Wait for all workers to finish.
            done.await();

            long endTime = System.nanoTime();

            double seconds =
                    (endTime - startTime) / 1_000_000_000.0;

            double throughput = completed.get() / seconds;

            // Display benchmark results.
            System.out.println("\nBenchmark=  " + name);
            System.out.println(" Worker Threads: " + THREADS);
            System.out.println(" Total Operations: " + completed.get());

            System.out.printf("Execution Time: %.3f ms%n",
                    seconds * 1000);

            System.out.printf("Throughput: %.2f operations/second%n",
                    throughput);

        } finally {
            // Stop the executor and release its resources.
            executor.shutdown();

            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println(" Inventory Concurrency Benchmark");
        System.out.println(" Operations per worker: " + OPERATIONS);

        benchmarkSynchronizedMap();
        benchmarkConcurrentMap();
    }
}