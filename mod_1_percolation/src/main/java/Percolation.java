package main.java;

// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
// import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean[][] grid;
  private WeightedQuickUnionUF quWithVPoints;

  private int gridN;
  private int gridVTop;
  private int gridVBottom;
  private int openCells;

  // NEED TO UPDATE GRID AND FLATTEN TO BE 1 - N not 0 - N

  // constructor to create N x N grid, with all sites blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("n must be greater than 0");
    }
    grid = new boolean[n][n];
    gridN = n;

    quWithVPoints = new WeightedQuickUnionUF((n * n) + 2);
    gridVTop = (n * n);
    gridVBottom = (n * n) + 1;

    openCells = 0;
  }

  // check valid placement and union with adjacent point
  private void checkAndUnion(int row, int col, int site) {
    if (isValid(row, col) && isOpen(row, col)) {
      int newSite = gridFlatten(row, col);
      quWithVPoints.union(newSite, site);
    }
  }

  // opens the site (row, col) if it is not already
  public void open(int row, int col) {
    if (isValid(row, col) && !isOpen(row, col)) {
      grid[row][col] = true;
      openCells ++;
      int site = gridFlatten(row, col);

      // union top
      if (row == 0) {
        quWithVPoints.union(site, gridVTop);
      }
      // union bottom
      if (row == (gridN - 1)) {
        quWithVPoints.union(site, gridVBottom);
      }
      // union below
      checkAndUnion(row - 1, col, site);
      // union above
      checkAndUnion(row + 1, col, site);
      // union left
      checkAndUnion(row, col - 1, site);
      // union right
      checkAndUnion(row, col + 1, site);
    } else if (!isValid(row, col)) {
      throw new IllegalArgumentException("Selected coordinate is outside the matrix");
    }
  }

  // checks if selected point is contained on the grid
  private boolean isValid(int row, int col) {
    return ((row >= 0 && row < gridN) && (col >= 0 && col < gridN));
  }

  // is site open?
  public boolean isOpen(int row, int col) {
    if (isValid(row, col)) {
      return grid[row][col];
    } else {
      throw new IllegalArgumentException("Selected coordinate is outside the matrix");
    }
  }

  // convert grid position to weighted quick union data structure (array) position
  private int gridFlatten(int row, int col) {
    return (gridN * row) + col;
  }

  // is site filled?
  public boolean isFull(int row, int col) {
    if (isValid(row, col)) {
      return quWithVPoints.find(gridFlatten(row, col)) == quWithVPoints.find(gridVTop);
    } else {
      throw new IllegalArgumentException("Selected coordinate is outside the matrix");
    }
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return openCells;
  }

  // does the grid percolate?
  public boolean percolates() {
    return quWithVPoints.find(gridVTop) == quWithVPoints.find(gridVBottom);
  }

  public static void main(String[] args) {
    // int n = StdIn.readInt();
    // int i = 1;
    // StdOut.println("Created a " + n + " by " + n + " Matrix");

    // Percolation percolation = new Percolation(n);

    // percolation.open(0, 0);
    // while (!percolation.percolates()) {
    //   i++;
    //   StdOut.println("The system does not percolate.");

    //   int randomPointOne = StdRandom.uniformInt(0, n);
    //   int randomPointTwo = StdRandom.uniformInt(0, n);

    //   StdOut.println("Open Matrix position (" + randomPointOne + ", " + randomPointTwo + ").");
    //   percolation.open(StdIn.readInt(), StdIn.readInt());
    // }

    // float percolationThreshold = (float) percolation.openCells / (n * n);

    // StdOut.println("The system now percolates!");
    // StdOut.println("It took: " + percolation.openCells + " out of " + n * n + " cells and " + i + " rounds.");
    // StdOut.println("This gives us a percolation threshold of " + percolationThreshold);
  }
}
