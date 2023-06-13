package main.java;
import java.util.Arrays;
import java.util.Stack;

public class Board {
  public int[][] tiles;
  private int[][] goal;
  private int N;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    if (tiles == null) {
      throw new IllegalArgumentException("tiles cannot be null");
    } else if (tiles.length != tiles[0].length) {
      throw new IllegalArgumentException("number of rows and columns must be equal");
    } else if (tiles.length < 2 || tiles.length >= 128) {
      throw new IllegalArgumentException("number of rows and columns must be between 2 and 127");
    } else {
      N = tiles.length;
      this.tiles = tiles;
      this.goal = new int[N][N];
      for (int row = 0; row < N; row++) {
        for (int col = 0; col < N; col++) {
          if (row == N - 1 && col == N - 1) {
            goal[row][col] = 0;
          } else {
            goal[row][col] = (row * N) + col + 1;
          }
        }
      }
    }
  }

  // string representation of this board
  public String toString() {
    String result = "";
    result += N + "\n";
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        result += " " + tiles[row][col];
      }
      result += "\n";
    }
    return result;
  }

  // board dimension n
  public int dimension() {
    return N;
  }

  private boolean check(int row, int col) {
    int tile = tiles[row][col];
    return tile != 0 && tile != (row * N) + col + 1;
  }

  // number of tiles out of place
  public int hamming() {
    int hammer = 0;
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (check(row, col)) {
          hammer++;
        }
      }
    }
    return hammer;
  }

  private int manhattanValue(int row, int col) {
    int tile = --tiles[row][col];
    int rowOffset = tile / N - row;
    int colOffset = tile % N - col;
    return Math.abs(rowOffset) + Math.abs(colOffset);
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    int manhattan = 0;
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (check(row, col)) {
          manhattan += manhattanValue(row, col);
        }
      }
    }
    return manhattan;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return Arrays.deepEquals(tiles, goal);
  }

  // does this board equal y?
  public boolean equals(Object y) {
    Board that;
    if (y.getClass() != this.getClass() || y == null) {
      return false;
    } else {
      that = (Board) y;
    }
    return (this.N == that.N && Arrays.deepEquals(this.tiles, that.tiles));
  }

  public int[] findZero() {
    int[] zero = new int[2];
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (tiles[row][col] == 0) {
          zero[0] = row;
          zero[1] = col;
          break;
        }
      }
    }
    return zero;
  }

  private int[][] copyTiles(int[][] tiles, int N) {
    int[][] tilesCopy = new int[N][N];
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        tilesCopy[row][col] = tiles[row][col];
      }
    }
    return tilesCopy;
  }

  private Board swap(int[] tileOne, int[] tileTwo) {
    int [][] tilesCopy = copyTiles(tiles, N);
    Board copy = new Board(tilesCopy);
    int temp = copy.tiles[tileOne[0]][tileOne[1]];
    copy.tiles[tileOne[0]][tileOne[1]] = copy.tiles[tileTwo[0]][tileTwo[1]];
    copy.tiles[tileTwo[0]][tileTwo[1]] = temp;
    return copy;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    Stack<Board> neighbors = new Stack<Board>();
    int[] zero = findZero();
    int row = zero[0];
    int col = zero[1];
    if (row > 0) {
      int[] tile = {row - 1, col};
      neighbors.push(swap(tile, zero));
    }
    if (row < N - 1) {
      int[] tile = {row + 1, col};
      neighbors.push(swap(tile, zero));
    }
    if (col > 0) {
      int[] tile = {row, col - 1};
      neighbors.push(swap(tile, zero));
    }
    if (col < N - 1) {
      int[] tile = {row, col + 1};
      neighbors.push(swap(tile, zero));
    }
    return neighbors;
  }

  // a board that is obtained by exchanging any pair of tiles
  // this is taken care of by copyTiles function
  public Board twin() {
    return null;
  }

  // unit testing (not graded)
  public static void main(String[] args) {
  }
}
