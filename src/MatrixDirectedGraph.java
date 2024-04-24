import java.util.Scanner;

public class MatrixDirectedGraph {

    public static boolean isDirectedGraph(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            if (matrix[i].length != n) {
                return false;
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < 0) {
                    return false;
                }
                if (i == j && matrix[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter matrix size (n): ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];
        System.out.println("Enter the matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        boolean isDirectedGraph = isDirectedGraph(matrix);
        if (isDirectedGraph) {
            System.out.println("The matrix represents a directed graph.");
        } else {
            System.out.println("The matrix does not represent a directed graph.");
        }

        scanner.close();
    }
}
