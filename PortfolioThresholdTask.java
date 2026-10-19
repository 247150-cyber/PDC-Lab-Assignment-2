import java.util.concurrent.RecursiveAction;

public class PortfolioThresholdTask extends RecursiveAction {

    static final int THRESHOLD = 1000;

    double[] assets;
    int start, end;
    double riskCap;

    public PortfolioThresholdTask(double[] assets, int start,
                                  int end, double riskCap) {
        this.assets = assets;
        this.start = start;
        this.end = end;
        this.riskCap = riskCap;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                if (assets[i] > riskCap) {
                    assets[i] = assets[i] * 0.95;
                }
            }
        } else {
            int mid = (start + end) / 2;

            PortfolioThresholdTask left =
                    new PortfolioThresholdTask(
                            assets, start, mid, riskCap);

            PortfolioThresholdTask right =
                    new PortfolioThresholdTask(
                            assets, mid, end, riskCap);

            invokeAll(left, right);
        }
    }
}
