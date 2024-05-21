import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

  /*
   * Complete the 'rotLeft' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts following parameters:
   * 1. INTEGER_ARRAY a
   * 2. INTEGER d
   */

  public static List<Integer> rotLeft(List<Integer> a, int d) {
    if (d == 0 || a.size() == 0)
      return a;

    int rotationNumber = d % a.size();
    if (rotationNumber == 0)
      return a;

    List<Integer> b = new ArrayList<Integer>();

    for (int i = 0; i < a.size(); i++) {
      int rotatedIndex = getRotatedIndex(i + rotationNumber, a.size());
      b.add(i, a.get(rotatedIndex));
    }

    return b;
  }

  public static int getRotatedIndex(int index, int length) {
    if (index >= length) {
      return index - length;
    }

    return index;
  }

}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int d = Integer.parseInt(firstMultipleInput[1]);

    List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    List<Integer> result = Result.rotLeft(a, d);

    bufferedWriter.write(
        result.stream()
            .map(Object::toString)
            .collect(joining(" "))
            + "\n");

    bufferedReader.close();
    bufferedWriter.close();
  }
}
