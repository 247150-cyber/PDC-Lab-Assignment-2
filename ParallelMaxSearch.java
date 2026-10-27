public class ParallelMaxSearch extends SensorSearchEngine {

    // Initialize the maximum search task for a specific data range.
    public ParallelMaxSearch(double[] sensorData,
                             int startIdx, int endIdx) {
        super(sensorData, startIdx, endIdx);
    }

    @Override
    public double processGrid() {

        // Start with the smallest possible double value.
        double max = -Double.MAX_VALUE;

        // Find the maximum value in the assigned range.
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] > max) {
                max = sensorData[i];
            }
        }

        return max;
    }
}