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

  private static Map<Integer, Integer> ladderPositions;
  private static Map<Integer, Integer> snakePositions;
  private static Map<Integer, Integer> visited;

  public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
    parseLadders(ladders);
    parseSnakes(snakes);

    return getNumberDice();

  }

  private static int getNumberDice() {
    Queue<Integer> queue = new LinkedList<>();

    // key: cell's position
    // value: number of move
    visited = new HashMap<>();

    visited.put(1, 0);
    queue.add(1);

    while (!queue.isEmpty()) {
      // backtrack when over 100
      Integer currPosition = queue.remove();
      int currMoveNumber = visited.get(currPosition);
      int newMoveNumber = currMoveNumber + 1;

      // return the number of move when the goal is reached
      if (currPosition == 100) {
        return visited.get(100);
      }

      // find the optimum dice roll
      for (Integer diceNumber = 1; diceNumber <= 6; diceNumber++) {
        Integer newPosition = diceNumber + currPosition;

        // if encounter the ladder's bottom position of a ladder, then get the ladder's
        // top position
        if (ladderPositions.containsKey(newPosition)) {
          newPosition = ladderPositions.get(newPosition);

        }

        // if encounter the snake's head position of a snake, then get the snake's tail
        // position
        if (snakePositions.containsKey(newPosition)) {
          newPosition = snakePositions.get(newPosition);
        }

        if (visited.get(newPosition) == null) {
          // save the number of move for that position
          visited.put(newPosition, newMoveNumber);
          // add the new position
          queue.add(newPosition);

          // When the position has already been visited, update its number of move
        } else if (newMoveNumber < visited.get(newPosition)) {
          visited.put(newMoveNumber, visited.get(currPosition + 1));
        }
      }
    }

    return -1;
  }

  private static void parseLadders(List<List<Integer>> ladders) {
    int bottom;
    int top;
    ladderPositions = new HashMap<Integer, Integer>(ladders.size());

    for (int i = 0; i < ladders.size(); i++) {
      bottom = ladders.get(i).get(0);
      top = ladders.get(i).get(1);

      ladderPositions.put(bottom, top);
    }

  }

  private static void parseSnakes(List<List<Integer>> snakes) {
    int head;
    int tail;
    snakePositions = new HashMap<Integer, Integer>(snakes.size());

    for (int i = 0; i < snakes.size(); i++) {
      head = snakes.get(i).get(0);
      tail = snakes.get(i).get(1);

      snakePositions.put(head, tail);
    }

  }

}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int t = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, t).forEach(tItr -> {
      try {
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> ladders = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
          try {
            ladders.add(
                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList()));
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> snakes = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
          try {
            snakes.add(
                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList()));
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

        int result = Result.quickestWayUp(ladders, snakes);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
