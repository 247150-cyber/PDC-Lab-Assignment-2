public class LogProcessorTask implements Runnable {

    // Execute the log processing task.
    @Override
    public void run() {

        // Process five log metric iterations.
        for (int i = 1; i <= 5; i++) {

            System.out.println(
                "Log Metrics - Iteration: " + i
                + " | Thread: "
                + Thread.currentThread().getName()
            );

            try {
                // Pause the current thread for one second.
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                // Restore the interrupted status of the thread.
                Thread.currentThread().interrupt();

                System.out.println("Log processor interrupted.");
                return;
            }
        }
    }
}