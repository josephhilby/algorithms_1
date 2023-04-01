package main.java;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
  private int gridN;
  private int trialN;
  private double[] results;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("n AND trials must be greater than 0");
    }
    gridN = n;
    trialN = trials;
    results = new double[trialN];

    for (int i = 0; i < trialN - 1; i++) {
      Percolation percolation = new Percolation(gridN);
      while (!percolation.percolates()) {
        int positionInputRow = StdRandom.uniformInt(0, gridN);
        int positionInputCol = StdRandom.uniformInt(0, gridN);

        percolation.open(positionInputRow, positionInputCol);
      }
      int openCells = percolation.numberOfOpenSites();
      double result = openCells / (gridN * gridN);
      results[i] = result;
    }
  }

    // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(results);
  }

    // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(results);
  }

    // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return (mean() - ((1.95 * stddev())) / Math.sqrt(trialN));
  }

    // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return (mean() + ((1.95 * stddev())) / Math.sqrt(trialN));
  }

   // test client (see below)
  public static void main(String[] args) {
    PercolationStats perStats = new PercolationStats(StdIn.readInt(), StdIn.readInt());

    StdOut.println("mean                    = " + perStats.mean());
    StdOut.println("stddev                  = " + perStats.stddev());
    StdOut.println("95% confidence interval = " + perStats.confidenceHi() + ", " + perStats.confidenceLo());
  }

}