public class AsyncWebEngineApp 
{
    public static void main(String[] args) {

        System.out.println(
            "Default JVM Thread: "
            + Thread.currentThread().getName()
        );

        TelemetryThread telemetry =
                new TelemetryThread();

        telemetry.setName("Telemetry-Worker");

        LogProcessorTask logTask =
                new LogProcessorTask();

        Thread logThread =
                new Thread(logTask, "Log-Worker");

        System.out.println("Starting both worker threads...");

        telemetry.start();
        logThread.start();

        System.out.println(
            "Telemetry thread active: " + telemetry.isAlive()
        );

        System.out.println(
            "Log thread active: " + logThread.isAlive()
        );
    }
}
