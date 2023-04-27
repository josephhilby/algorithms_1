/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

package main.java;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
  // x-coordinate of this point
  private final int x;

  // y-coordinate of this point
  private final int y;

  /**
   * Initializes a new point.
   *
   * @param x the <em>x</em>-coordinate of the point
   * @param y the <em>y</em>-coordinate of the point
   */
  public Point(int x, int y) {
    /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
  }

  /**
   * Draws this point to standard draw.
   */
  public void draw() {
    /* DO NOT MODIFY */
    StdDraw.point(x, y);
  }

  /**
   * Draws the line segment between this point and the specified point
   * to standard draw.
   *
   * @param that the other point
   */
  public void drawTo(Point that) {
    /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  /**
   * Returns a string representation of this point.
   * This method is provide for debugging;
   * your program should not rely on the format of the string representation.
   *
   * @return a string representation of this point
   */
  public String toString() {
    /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }

  /*
   * The compareTo() method should compare the invoking point and argument point by their y-coordinates, breaking ties by their x-coordinates.
   */
  public int compareTo(Point that) {
    if (this.x == that.x && this.y == that.y) {
      return 0;
    } else if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
      return -1;
    } else {
      return +1;
    }
  }

  /*
   * The slopeTo() method should return the slope between the invoking point (x0, y0) and the argument point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0).
   */
  public double slopeTo(Point that) {
    double slope = (double) (that.y - this.y) / (that.x - this.x);
    if (slope == -0.0) {
      slope = 0.0;
    }
    if (slope == Double.NEGATIVE_INFINITY) {
      slope = Double.POSITIVE_INFINITY;
    }
    if (this.x == that.x && this.y == that.y) {
      slope = Double.NEGATIVE_INFINITY;
    }
    return slope;
  }

  /*
   * The slopeOrder() method should return a comparator that compares its two argument points by the slopes they make with the invoking point (x0, y0).
   */
  public Comparator<Point> slopeOrder() {
    return (p1, p2) -> {
      double slope1 = this.slopeTo(p1);
      double slope2 = this.slopeTo(p2);

      if (slope1 < slope2) {
        return -1;
      } else if (slope1 > slope2) {
        return +1;
      } else {
        return 0;
      }
    };
  }

  public static void main (String[] args) {
  }
}
