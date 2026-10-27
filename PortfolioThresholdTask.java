import java.util.concurrent.RecursiveAction;

public class PortfolioThresholdTask extends RecursiveAction {

    // Minimum number of assets processed by one task.
    static final int THRESHOLD = 1000;

    double[] assets;
    int start, end;
    double riskCap;

    // Initialize asset data and validate the input range.
    public PortfolioThresholdTask(double[] assets, int start,
                                  int end, double riskCap) {

        if (assets == null) {
            throw new IllegalArgumentException("Assets cannot be null.");
        }

        if (start < 0 || end > assets.length || start > end) {
            throw new IllegalArgumentException("Invalid asset range.");
        }

        if (Double.isNaN(riskCap)) {
            throw new IllegalArgumentException("Risk cap cannot be NaN.");
        }

        this.assets = assets;
        this.start = start;
        this.end = end;
        this.riskCap = riskCap;
    }

    @Override
    protected void compute() {

        // Process the assigned range if it is small enough.
        if (end - start <= THRESHOLD) {

            for (int i = start; i < end; i++) {

                // Reduce assets that exceed the risk cap by 5%.
                if (assets[i] > riskCap) {
                    assets[i] = assets[i] * 0.95;
                }
            }

        } else {

            // Divide the task into two smaller ranges.
            int mid = start + (end - start) / 2;

            PortfolioThresholdTask left =
                    new PortfolioThresholdTask(
                            assets, start, mid, riskCap);

            PortfolioThresholdTask right =
                    new PortfolioThresholdTask(
                            assets, mid, end, riskCap);

            // Execute both subtasks in parallel.
            invokeAll(left, right);
        }
    }
}