import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {

    public static void main(String[] args) {
        double[] assets = new double[10000];

        for (int i = 0; i < assets.length; i++) {
            assets[i] = 500 + (i % 2000);
        }

        // Serial calculation
        long serialStart = System.nanoTime();

        double serialRisk = 0;
        for (int i = 0; i < assets.length; i++) {
            serialRisk += assets[i];
        }

        long serialEnd = System.nanoTime();

        // Parallel calculation
        ForkJoinPool pool = new ForkJoinPool();

        long parallelStart = System.nanoTime();

        double totalRisk = pool.invoke(
            new ParallelRiskCalculator(assets, 0, assets.length)
        );

        long parallelEnd = System.nanoTime();

        // Apply 5% penalty to assets above risk cap
        pool.invoke(
            new PortfolioThresholdTask(
                assets, 0, assets.length, 1500
            )
        );

        System.out.println("Total Assets: " + assets.length);
        System.out.println("Serial Risk Score: " + serialRisk);
        System.out.println("Parallel Risk Score: " + totalRisk);
        System.out.println("Risk Cap: 1500");
        System.out.println("First Adjusted Asset: " + assets[0]);

        System.out.println("Serial Processing Time: "
            + (serialEnd - serialStart) / 1_000_000.0 + " ms");

        System.out.println("Parallel Processing Time: "
            + (parallelEnd - parallelStart) / 1_000_000.0 + " ms");

        pool.shutdown();
    }
}
