package main.java;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
  private int lineSegmentsN = 0;
  private LineSegment[] lineSegments;
  private Point lastP;
  private Point lastL;
  private double lastSlope;

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

  private boolean isPreviousLineSegment(Point argP, Point argL, double slope) {
    if (lastSlope == slope && (lastP.compareTo(argP) == 0 || lastL.compareTo(argL) == 0)) {
      return true;
    } else {
      return false;
    }
  }

  private Point[] sortPoints(Point i, Point j, Point k, Point l) {
    Point[] pointsSorted = new Point[4];
    pointsSorted[0] = i;
    pointsSorted[1] = j;
    pointsSorted[2] = k;
    pointsSorted[3] = l;
    Arrays.sort(pointsSorted);
    return pointsSorted;
  }

  private void removeNulls(LineSegment[] finalSegments) {
    LineSegment[] lineSegmentsCopy = new LineSegment[lineSegmentsN];
    for (int i = 0; i < lineSegmentsN; i++) {
      lineSegmentsCopy[i] = lineSegments[i];
    }
    lineSegments = lineSegmentsCopy;
  }

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    checkNullPoints(points);
    Point[] pointsCopy = points.clone();
    Arrays.sort(pointsCopy);
    checkDupPoints(pointsCopy);
    lineSegments = new LineSegment[1];
    int N = pointsCopy.length;
    for (int i = 0; i < N - 3; i++) {
      for (int j = i + 1; j < N - 2; j++) {
        double ij = pointsCopy[i].slopeTo(pointsCopy[j]);
        for (int k = j + 1; k < N - 1; k++) {
          double jk = pointsCopy[j].slopeTo(pointsCopy[k]);
          for (int l = k + 1; l < N; l++) {
            double kl = pointsCopy[k].slopeTo(pointsCopy[l]);
            if (ij == jk && jk == kl) {
              double il = pointsCopy[i].slopeTo(pointsCopy[l]);
              if (lineSegmentsN > 0 && isPreviousLineSegment(pointsCopy[i], pointsCopy[l], il)) {
                lineSegmentsN--;
              }
              Point[] pointsSorted = sortPoints(pointsCopy[i], pointsCopy[j], pointsCopy[k], pointsCopy[l]);
              push(new LineSegment(pointsSorted[0], pointsSorted[3]));
              lastP = pointsSorted[0];
              lastL = pointsSorted[3];
              lastSlope = pointsSorted[0].slopeTo(pointsSorted[3]);
            }
          }
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
    // Point[] points = null;

    // BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    // LineSegment[] lineSegments = bruteCollinearPoints.segments();
    // for (int i = 0; i < lineSegments.length; i++) {
    //   StdOut.println(lineSegments[i].toString());
    // }
    // StdOut.println(bruteCollinearPoints.numberOfSegments());

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
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
