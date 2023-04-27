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
    Arrays.sort(pointsCopy);
    checkDupPoints(pointsCopy);

    lineSegments = new LineSegment[1];
    int N = pointsCopy.length;
    int count = 0;

    for (int i = 0; i < N; i++) {
      int pointsAfterI = i + 1;
      Comparator<Point> comparator = pointsCopy[i].slopeOrder();
      Point[] pointsCopySorted = Arrays.copyOfRange(points, pointsAfterI, N);
      Arrays.sort(pointsCopySorted, comparator);

      for (int j = 0; j < N - pointsAfterI; j++) {
        if (j == pointsCopySorted.length - 1) {
          if (count >= 2) {
            push(new LineSegment(points[i], pointsCopySorted[j]));
          }
          count = 0;
        } else if (count >= 2 && points[i].slopeTo(pointsCopySorted[j]) != points[i].slopeTo(pointsCopySorted[j + 1])) {
          push(new LineSegment(points[i], pointsCopySorted[j]));
          count = 0;
        } else if (points[i].slopeTo(pointsCopySorted[j]) == points[i].slopeTo(pointsCopySorted[j + 1])) {
          count++;
        } else {
          count = 0;
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
    // Point[] points = new Point[6];
    // points[0] = new Point(19000, 10000);
    // points[1] = new Point(18000, 10000);
    // points[2] = new Point(32000, 10000);
    // points[3] = new Point(21000, 10000);
    // points[4] = new Point(1234, 5678);
    // points[5] = new Point(14000, 10000);

    // FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
