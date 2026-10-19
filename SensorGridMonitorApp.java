import java.util.concurrent.*;

public class SensorGridMonitorApp {

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        final int SIZE = 50000;
        final int WORKERS = 4;

        double[] sensorData = new double[SIZE];

        // Generate sample sensor telemetry
        for (int i = 0; i < SIZE; i++) {
            sensorData[i] = 20 + (i % 100) * 0.5;
        }

        // Add sample critical readings
        sensorData[1234] = -10.5;  // Minimum temperature
        sensorData[45678] = 250.0; // Maximum vibration

        ExecutorService executor =
                Executors.newFixedThreadPool(WORKERS);

        long startTime = System.nanoTime();

        try {
            Future<Double>[] minResults = new Future[WORKERS];
            Future<Double>[] maxResults = new Future[WORKERS];

            int chunkSize = (SIZE + WORKERS - 1) / WORKERS;

            // Submit minimum and maximum searches simultaneously
            for (int worker = 0; worker < WORKERS; worker++) 
                {
                final int start = worker * chunkSize;
                final int end = Math.min(start + chunkSize, SIZE);

                minResults[worker] = executor.submit(() -> new ParallelMinSearch(sensorData, start, en).processGrid());

                maxResults[worker] = executor.submit(() ->new ParallelMaxSearch(sensorData, start, end ).processGrid() );
            }

            double globalMin = Double.MAX_VALUE;
            double globalMax = -Double.MAX_VALUE;

            for (int worker = 0; worker < WORKERS; worker++) {
                globalMin = Math.min(
                    globalMin, minResults[worker].get()
                );

                globalMax = Math.max(
                    globalMax, maxResults[worker].get()
                );
            }

            long endTime = System.nanoTime();

            System.out.println("Total Sensor Nodes: " + SIZE);
            System.out.println("Worker Threads: " + WORKERS);
            System.out.println("Global Minimum Temperature: "+ globalMin);
            System.out.println("Global Maximum Vibration: " + globalMax);
            System.out.println("Execution Time: "+ (endTime - startTime) / 1_000_000.0+ " ms");

        } finally {
            executor.shutdown();
        }
    }
}
