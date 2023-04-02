// package main.java;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean[][] grid;
  private WeightedQuickUnionUF quWithVPoints;

  private int gridN;
  private int gridVTop;
  private int gridVBottom;
  private int openCells;

  // constructor to create N x N grid, with all sites blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("n must be greater than 0");
    }
    grid = new boolean[n][n];
    gridN = n;

    quWithVPoints = new WeightedQuickUnionUF((n * n) + 2);
    gridVTop = 0;
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
  // computer will count row and col from 0
  // must shift rows as human input will count from 1
  // to keep this from being confusing
  // I will not cary the shift into new methods
  public void open(int row, int col) {
    int shiftRow = row - 1;
    int shiftCol = col -1;

    if (isValid(row, col) && !isOpen(row, col)) {
      grid[shiftRow][shiftCol] = true;
      openCells++;
      int site = gridFlatten(row, col);

      // union top
      if (shiftRow == 0) {
        quWithVPoints.union(site, gridVTop);
      }
      // union bottom
      if (shiftRow == (gridN - 1)) {
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
    int shiftRow = row - 1;
    int shiftCol = col - 1;

    return ((shiftRow >= 0 && shiftRow < gridN) && (shiftCol >= 0 && shiftCol < gridN));
  }

  // is site open?
  public boolean isOpen(int row, int col) {
    int shiftRow = row - 1;
    int shiftCol = col - 1;

    if (isValid(row, col)) {
      return grid[shiftRow][shiftCol];
    } else {
      throw new IllegalArgumentException("Selected coordinate is outside the matrix");
    }
  }

  // convert grid position to weighted quick union data structure (array) position
  private int gridFlatten(int row, int col) {
    int shiftRow = row - 1;
    int shiftCol = col - 1;

    return (gridN * shiftRow) + shiftCol + 1;
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
    // this class is used by PercolationStats
  }
}
