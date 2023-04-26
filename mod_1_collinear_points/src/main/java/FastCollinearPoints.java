package main.java;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
  private int lineSegmentsN = 0;
  private LineSegment[] lineSegments;

  private void checkPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("Null points array.");
    }
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0) {
        throw new IllegalArgumentException("Duplicate points found.");
      }
      if (points[i] == null) {
        throw new IllegalArgumentException("Null point found.");
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

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    checkPoints(points);
    lineSegments = new LineSegment[1];
    int N = points.length;
    int count = 0;
    for (int i = 0; i < N; i++) {
      Comparator<Point> comparator = points[i].slopeOrder();
      int pointsAfterI = i + 1;
      Point[] pointsCopy = Arrays.copyOfRange(points, pointsAfterI, N);
      Arrays.sort(pointsCopy, comparator);
      for (int j = 0; j < N - pointsAfterI; j++) {
        if (j == pointsCopy.length - 1) {
          if (count >= 2) {
            push(new LineSegment(points[i], pointsCopy[j]));
          }
          count = 0;
        } else if (count >= 2 && points[i].slopeTo(pointsCopy[j]) != points[i].slopeTo(pointsCopy[j + 1])) {
          push(new LineSegment(points[i], pointsCopy[j]));
          count = 0;
        } else if (points[i].slopeTo(pointsCopy[j]) == points[i].slopeTo(pointsCopy[j + 1])) {
          count++;
        } else {
          count = 0;
        }
      }
    }
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
