public class ParallelMinSearch extends SensorSearchEngine {

    // Initialize the minimum search task.
    public ParallelMinSearch(double[] sensorData,
                             int startIdx, int endIdx) {
        super(sensorData, startIdx, endIdx);
    }

    @Override
    public double processGrid() {

        // Start with the largest possible double value.
        double min = Double.MAX_VALUE;

        // Find the minimum value in the assigned range.
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] < min) {
                min = sensorData[i];
            }
        }

        return min;
    }
}