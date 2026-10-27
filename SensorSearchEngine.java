
public abstract class SensorSearchEngine {

    protected double[] sensorData;
    protected int startIdx;
    protected int endIdx;

    // Initialize sensor data and validate the search range.
    public SensorSearchEngine(double[] sensorData,
                              int startIdx, int endIdx) {

        if (sensorData == null) {
            throw new IllegalArgumentException("Sensor data cannot be null.");
        }

        if (startIdx < 0 || endIdx > sensorData.length
                || startIdx > endIdx) {
            throw new IllegalArgumentException("Invalid search range.");
        }

        this.sensorData = sensorData;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    // Each child class must implement its own grid processing logic.
    public abstract double processGrid();
}
