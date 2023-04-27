package main.java;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
  private int lineSegmentsN = 0;
  private LineSegment[] lineSegments;

  private void checkNullPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("Null points array.");
    }
    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) {
        throw new IllegalArgumentException("Null point found.");
      }
    }
  }

  private void checkDupPoints(Point[] points) {
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0) {
        throw new IllegalArgumentException("Duplicate points found.");
      }
    }
  }

  private void resize(int capacity) {
    LineSegment[] copy = new LineSegment[capacity];
    for (int i = 0; i < lineSegments.length; i++) {
      copy[i] = lineSegments[i];
    }
    lineSegments = copy;
  }

  private void push(LineSegment lineSegment) {
    if (lineSegmentsN == lineSegments.length) {
      resize(2 * lineSegments.length);
    }
    lineSegments[lineSegmentsN] = lineSegment;
    lineSegmentsN++;
  }

  private void removeNulls(LineSegment[] finalSegments) {
    LineSegment[] lineSegmentsCopy = new LineSegment[lineSegmentsN];
    for (int i = 0; i < lineSegmentsN; i++) {
      lineSegmentsCopy[i] = lineSegments[i];
    }
    lineSegments = lineSegmentsCopy;
  }

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    checkNullPoints(points);
    Point[] pointsCopy = points.clone();
    int N = pointsCopy.length;
    Arrays.sort(pointsCopy);
    checkDupPoints(pointsCopy);

    lineSegments = new LineSegment[1];

    for (int i = 0; i < N; i++) {
      int count = 0;
      int pointsAfterI = i + 1;
      Comparator<Point> comparator = pointsCopy[i].slopeOrder();
      Point[] pointsCopySorted = Arrays.copyOfRange(pointsCopy, pointsAfterI, N);
      int M = pointsCopySorted.length;
      Arrays.sort(pointsCopySorted, comparator);

      for (int j = 1; j < M; j++) {
        double j1 = pointsCopy[i].slopeTo(pointsCopySorted[j - 1]);
        double ij = pointsCopy[i].slopeTo(pointsCopySorted[j]);
        if (j == M - 1) {
          if (count >= 1 && ij == j1) {
            push(new LineSegment(pointsCopy[i], pointsCopySorted[j]));
          } else if (count >= 2 && ij != j1) {
            push(new LineSegment(pointsCopy[i], pointsCopySorted[j - 1]));
          }
          count = 0;
        } else if (ij != j1) {
          if (count >= 2) {
            push(new LineSegment(pointsCopy[i], pointsCopySorted[j - 1]));
          }
          count = 0;
        } else {
          count++;
        }
      }
    }
    removeNulls(lineSegments);
  }

  // the number of line segments
  public int numberOfSegments() {
    return lineSegmentsN;
  }

  // the line segments
  public LineSegment[] segments() {
    return lineSegments;
  }

  public static void main(String[] args) {
    Point[] points = new Point[8];
    points[0] = new Point(10000, 0);
    points[1] = new Point(0, 10000);
    points[2] = new Point(3000, 7000);
    points[3] = new Point(7000, 3000);
    points[4] = new Point(20000, 21000);
    points[5] = new Point(3000, 4000);
    points[6] = new Point(14000, 15000);
    points[7] = new Point(6000, 7000);

    FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
    LineSegment[] lineSegments = fastCollinearPoints.segments();
    for (int i = 0; i < lineSegments.length; i++) {
      StdOut.println(lineSegments[i]);
    }

    // // read the n points from a file
    // In in = new In(args[0]);
    // int n = in.readInt();
    // Point[] points = new Point[n];
    // for (int i = 0; i < n; i++) {
    //   int x = in.readInt();
    //   int y = in.readInt();
    //   points[i] = new Point(x, y);
    // }

    // // draw the points
    // StdDraw.enableDoubleBuffering();
    // StdDraw.setXscale(0, 32768);
    // StdDraw.setYscale(0, 32768);
    // for (Point p : points) {
    //   p.draw();
    // }
    // StdDraw.show();

    // // print and draw the line segments
    // FastCollinearPoints collinear = new FastCollinearPoints(points);
    // for (LineSegment segment : collinear.segments()) {
    //   StdOut.println(segment);
    //   segment.draw();
    // }
    // StdDraw.show();
  }
}
