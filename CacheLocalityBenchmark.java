public class CacheLocalityBenchmark {

    static int N = 2000;
    static double[][] matrix = new double[N][N];

    static double traverseRowMajor() {
        double sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += matrix[i][j];
            }
        }

        return sum;
    }

    static double traverseColumnMajor() {
        double sum = 0;

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                sum += matrix[i][j];
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = i + j;
            }
        }

        long start = System.nanoTime();

        for (int i = 0; i < 10; i++) {
            traverseRowMajor();
        }

        long rowTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 0; i < 10; i++) {
            traverseColumnMajor();
        }

        long colTime = System.nanoTime() - start;

        double rowMs = rowTime / 1000000.0;
        double colMs = colTime / 1000000.0;

        System.out.println("Matrix Size: 2000 x 2000");
        System.out.println("Iterations: 10");
        System.out.println("Row-Major Time: " + rowMs + " ms");
        System.out.println("Column-Major Time: " + colMs + " ms");
        System.out.println("Row-Major Speedup: "+ (colMs / rowMs) + "x");
    }
}
