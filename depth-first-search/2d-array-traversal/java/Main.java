import java.util.HashSet;
import java.util.Set;

public class Main {
  private static int rows;
  private static int cols;
  private static int[][] matrix;
  private static Set<String> visited = new HashSet<String>();

  public static int countGroups(int[][] inputMatrix) {
    matrix = inputMatrix;
    rows = inputMatrix.length;
    cols = inputMatrix[0].length;
    int groupCount = 0;
    visited = new HashSet<String>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == 1 && !visited.contains(i + "-" + j)) {
          groupCount++;
          dfs(i, j);
        }
      }
    }

    return groupCount;
  }

  private static void dfs(int x, int y) {
    if (x < 0 || x >= rows || y < 0 || y >= cols || matrix[x][y] == 0 ||
        visited.contains(x + "-" + y)) {
      return;
    }

    visited.add(x + "-" + y);
    dfs(x - 1, y); // up
    dfs(x + 1, y); // down
    dfs(x, y - 1); // left
    dfs(x, y + 1); // right

  }

  public static void main(String[] args) {

    int[][] matrix1 = {
        { 1, 1, 0, 0 },
        { 1, 1, 1, 0 },
        { 0, 1, 1, 0 },
        { 0, 0, 0, 1 }
    };

    int[][] matrix2 = {
        { 1, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 0 },
        { 0, 0, 1, 0, 0 },
        { 0, 0, 0, 1, 0 },
        { 0, 0, 0, 0, 1 },
    };

    int[][] matrix3 = {
        { 1, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 0 },
        { 0, 0, 1, 1, 0 },
        { 0, 0, 1, 1, 1 },
        { 0, 0, 0, 1, 1 },
    };

    System.out.println("Number of groups (Matrix 1):" + countGroups(matrix1)); // Output: 2
    System.out.println("Number of groups (Matrix 2):" + countGroups(matrix2)); // Output: 5
    System.out.println("Number of groups (Matrix 2):" + countGroups(matrix3)); // Output: 3
  }

}