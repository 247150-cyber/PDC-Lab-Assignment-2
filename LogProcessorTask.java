public class LogProcessorTask implements Runnable 
{

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                "Log Metrics - Iteration: " + i
                + " | Thread: "
                + Thread.currentThread().getName()
            );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Log processor interrupted.");
                return;
            }
        }
    }
}
