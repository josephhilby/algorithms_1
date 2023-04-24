package main.java;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Arrays;

public class Point implements Comparable<Point> {
  private int x;
  private int y;

  // constructs the point (x, y)
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // draws this point
  public void draw() {
    StdDraw.point(x, y);
  }

  // draws the line segment from this point to that point
  public void drawTo(Point that) {
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  // string representation
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  // compare two points by y-coordinates, breaking ties by x-coordinates
  public int compareTo(Point that) {
    if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
      return 1;
    } else {
      return -1;
    }
  }

  // the slope between this point and that point
  public double slopeTo(Point that) {
    double slope = (double) (that.y - this.y) / (that.x - this.x);
    if (slope == -0.0) {
      slope = 0.0;
    }
    if (slope == Double.NEGATIVE_INFINITY) {
      slope = Double.POSITIVE_INFINITY;
    }
    if (this == that) {
      slope = Double.NEGATIVE_INFINITY;
    }
    return slope;
  }

  // compare two points by slopes they make with this point
  public Comparator<Point> slopeOrder() {
    return (p1, p2) -> {
      double slope1 = this.slopeTo(p1);
      double slope2 = this.slopeTo(p2);

      if (slope1 < slope2) {
        return 1;
      } else if (slope1 > slope2) {
        return -1;
      } else {
        return 0;
      }
    };
  }

  public static void main (String[] args) {
    Point point1 = new Point(1, 1);

    Point point2 = new Point(2, 2);
    Point point3 = new Point(2, 1);
    Point point4 = new Point(1, 2);
    Point point5 = new Point(0, 0);
    Point[] points = { point2, point3, point4, point5 };
    Point[] expected = { point4, point2, point5, point3 };

    Comparator<Point> point1Comparator = point1.slopeOrder();
    Arrays.sort(points, point1Comparator);

    StdOut.println(Arrays.toString(points));
    StdOut.println(Arrays.toString(expected));
  }
}
