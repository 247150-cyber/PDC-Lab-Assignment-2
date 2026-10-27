
import java.util.concurrent.*;

public class SensorGridMonitorApp {

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        // Define the total number of sensor readings and worker threads
        final int SIZE = 50000;
        final int WORKERS = 4;

        // Create an array to store sensor readings
        double[] sensorData = new double[SIZE];

        // Generate sample sensor telemetry data
        for (int i = 0; i < SIZE; i++) {
            sensorData[i] = 20 + (i % 100) * 0.5;
        }

        // Add sample critical sensor readings
        sensorData[1234] = -10.5;  // Minimum temperature
        sensorData[45678] = 250.0; // Maximum vibration

        // Create a thread pool to execute parallel tasks
        ExecutorService executor =
                Executors.newFixedThreadPool(WORKERS);

        // Record the starting time of execution
        long startTime = System.nanoTime();

        try {
            // Store the results returned by minimum and maximum search tasks
            Future<Double>[] minResults = new Future[WORKERS];
            Future<Double>[] maxResults = new Future[WORKERS];

            // Divide sensor data into equal-sized chunks
            int chunkSize = (SIZE + WORKERS - 1) / WORKERS;

            // Submit minimum and maximum searches for each chunk
            for (int worker = 0; worker < WORKERS; worker++) {

                // Calculate the starting and ending index for each worker
                final int start = worker * chunkSize;
                final int end = Math.min(start + chunkSize, SIZE);

                // Submit a task to find the minimum value
                minResults[worker] = executor.submit(
                    () -> new ParallelMinSearch(sensorData, start, end).processGrid()
                );

                // Submit a task to find the maximum value
                maxResults[worker] = executor.submit(
                    () -> new ParallelMaxSearch(sensorData, start, end).processGrid()
                );
            }

            // Initialize variables to store the global minimum and maximum
            double globalMin = Double.MAX_VALUE;
            double globalMax = -Double.MAX_VALUE;

            // Collect results from all worker threads
            for (int worker = 0; worker < WORKERS; worker++) {

                // Update the global minimum value
                globalMin = Math.min(
                    globalMin, minResults[worker].get()
                );

                // Update the global maximum value
                globalMax = Math.max(
                    globalMax, maxResults[worker].get()
                );
            }

            // Record the ending time of execution
            long endTime = System.nanoTime();

            // Display the final sensor monitoring results
            System.out.println("Total Sensor Nodes: " + SIZE);
            System.out.println("Worker Threads: " + WORKERS);
            System.out.println("Global Minimum Temperature: " + globalMin);
            System.out.println("Global Maximum Vibration: " + globalMax);

            // Display total execution time in milliseconds
            System.out.println("Execution Time: "
                    + (endTime - startTime) / 1_000_000.0 + " ms");

        } finally {
            // Shut down the executor after all tasks are completed
            executor.shutdown();
        }
    }
}
