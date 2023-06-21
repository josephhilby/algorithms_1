package main.java;

import java.util.Comparator;
import java.util.Iterator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
  private Board initial;
  private SearchNode current;
  private Stack<Board> solutionSteps;

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException("initial board cannot be null");
    }
    this.initial = initial;

    MinPQ<SearchNode> boardPQ = new MinPQ<>(4);
    current = new SearchNode(initial, null);

    if (isSolvable()) {
      while (!current.board.isGoal()) {
        Iterator<Board> possibleMoves = current.board.neighbors().iterator();
        while (possibleMoves.hasNext()) {
          Board move = possibleMoves.next();
          SearchNode node = new SearchNode(move, current);
          boardPQ.insert(node);
        }
        current = boardPQ.delMin();
      }
    }
  }

  // counts the number of inversions for a given number on a given board
  private int inversionCheck(int num, int row, int col) {
    int count = 0;
    for (int i = row; i >= 0; i--) {
      for (int j = col; j >= 0; j--) {
        int check = initial.tiles[i][j];
        if (check != 0 && check > num) {
          count++;
        }
      }
    }
    return count;
  }

  // counts the number of inversions for the initial board
  private int inversionsCount() {
    int count = 0;
    int N = initial.dimension() - 1;
    for (int row = N; row >= 0; row--) {
      for (int col = N; col >= 0; col--) {
        int num = initial.tiles[row][col];
        if (num != 0) {
          count += inversionCheck(num, row, col);
        }
      }
    }
    return count;
  }

  // is the initial board solvable? (see below)
  // If N is odd, then puzzle instance is solvable iff number of inversions is even in the input state
  // If N is even, puzzle instance is solvable iff the number of inversions + the row of the blank square is odd
  public boolean isSolvable() {
    int N = initial.dimension();
    int zeroRow = initial.findZero()[0];
    int inversions = inversionsCount();
    if (N % 2 != 0) {
      if (inversions % 2 == 0) {
        return true;
      } else {
        return false;
      }
    } else {
      if (inversions + zeroRow % 2 == 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (!isSolvable()) {
      return -1;
    } else {
      return current.moves;
    }
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable()) {
      return null;
    }

    solutionSteps = new Stack<Board>();
    while (current != null) {
      solutionSteps.push(current.board);
      current = current.previous;
    }
    return solutionSteps;
  }

  public class SearchNode implements Comparable<SearchNode> {
    public final Board board;
    public final SearchNode previous;
    public int moves;

    public SearchNode(Board board, SearchNode previous) {
      this.board = board;
      this.previous = previous;
      if (previous == null) {
        this.moves = 0;
      } else {
        this.moves = previous.moves + 1;
      }
    }

    @Override
    public int compareTo(SearchNode that) {
        return (this.board.manhattan() + this.board.hamming()) - (that.board.manhattan() + that.board.hamming());
    }
  }
  // test client (see below)
  public static void main(String[] args) {
    // create initial board from file
    // In in = new In(args[0]);
    // int n = in.readInt();
    // int[][] tiles = new int[n][n];
    // for (int i = 0; i < n; i++)
    //   for (int j = 0; j < n; j++)
    //     tiles[i][j] = in.readInt();
    // int[][] tiles = { { 0, 1, 3 },
    //                   { 4, 2, 5 },
    //                   { 7, 8, 6 }};
    int[][] tiles = { {1, 2, 3}, {4, 5, 6}, {8, 7, 0} };
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      int moves = solver.moves();
      StdOut.println("Minimum number of moves = " + moves);
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}
