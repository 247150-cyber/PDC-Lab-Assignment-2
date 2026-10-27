import java.util.concurrent.RecursiveTask;

public class ParallelRiskCalculator extends RecursiveTask<Double> {

    // Minimum number of assets processed by one task.
    static final int THRESHOLD = 1000;

    double[] assets;
    int start, end;

    // Initialize the asset data and validate the range.
    public ParallelRiskCalculator(double[] assets, int start, int end) {

        if (assets == null) {
            throw new IllegalArgumentException("Assets cannot be null.");
        }

        if (start < 0 || end > assets.length || start > end) {
            throw new IllegalArgumentException("Invalid asset range.");
        }

        this.assets = assets;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {

        // Process the assigned range directly if it is small enough.
        if (end - start <= THRESHOLD) {
            double sum = 0;

            for (int i = start; i < end; i++) {
                sum += assets[i];
            }

            return sum;
        }

        // Divide the task into two smaller tasks.
        int mid = start + (end - start) / 2;

        ParallelRiskCalculator left =
                new ParallelRiskCalculator(assets, start, mid);

        ParallelRiskCalculator right =
                new ParallelRiskCalculator(assets, mid, end);

        // Execute the left task asynchronously.
        left.fork();

        // Compute the right task in the current thread.
        double rightSum = right.compute();

        // Wait for the left task and combine both results.
        double leftSum = left.join();

        return leftSum + rightSum;
    }
}