public class TelemetryThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                "System Health Update " + i
                + " | CPU: " + (20 + i * 5) + "%"
                + " | Memory: " + (40 + i * 3) + "%"
                + " | Thread: " + Thread.currentThread().getName()
            );

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
