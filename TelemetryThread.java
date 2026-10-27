
import java.time.LocalTime;

public class TelemetryThread extends Thread {

    private static final int TOTAL_UPDATES = 10;
    private static final int DELAY = 800;

    @Override
    public void run() {

        System.out.println("================================");
        System.out.println(" TELEMETRY MONITORING STARTED");
        System.out.println("Thread Name: " + getName());
        System.out.println("Thread Priority: " + getPriority());
        System.out.println("================================");

        for (int i = 1; i <= TOTAL_UPDATES; i++) {

            int cpuUsage = 20 + i * 5;
            int memoryUsage = 40 + i * 3;
            int diskUsage = 30 + i * 2;

            double progress = (i * 100.0) / TOTAL_UPDATES;

            System.out.println("\nTelemetry Update: " + i);

            System.out.println("Time: " + LocalTime.now());
            System.out.println("CPU Usage: " + cpuUsage + "%");
            System.out.println("Memory Usage: " + memoryUsage + "%");
            System.out.println("Disk Usage: " + diskUsage + "%");

            System.out.println("Progress: "
                    + String.format("%.1f", progress) + "%");

            System.out.println("Thread: "
                    + Thread.currentThread().getName());

            try {
                Thread.sleep(DELAY);

            } catch (InterruptedException e) {

                System.out.println("Telemetry Monitoring Interrupted.");

                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("\nTelemetry Monitoring Completed.");
    }
}

