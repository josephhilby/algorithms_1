package main.java;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
  private int lineSegmentsN = 0;
  private Point lastP;
  private Point lastL;
  private double lastSlope;
  private LineSegment[] lineSegments;

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

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    lineSegments = new LineSegment[1];
    int N = points.length;
    Arrays.sort(points);
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N - 2; j++) {
        double ij = points[i].slopeTo(points[j]);
        for (int k = j + 1; k < N - 1; k++) {
          double jk = points[j].slopeTo(points[k]);
          for (int l = k + 1; l < N; l++) {
            double kl = points[i].slopeTo(points[l]);
            if (ij == jk && jk == kl) {
              double il = points[i].slopeTo(points[l]);
              if (lineSegmentsN > 0 && isPreviousLineSegment(points[i], points[l], il)) {
                lineSegmentsN--;
              }
              push(new LineSegment(points[i], points[l]));
              lastP = points[i];
              lastL = points[l];
              lastSlope = il;
            }
          }
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
    // Point[] points = new Point[10];
    // points[0] = new Point(0, 0);
    // points[1] = new Point(1, 3);
    // points[2] = new Point(1, 1);
    // points[3] = new Point(2, 2);
    // points[4] = new Point(4, 4);
    // points[5] = new Point(5, 5);
    // points[6] = new Point(3, 1);
    // points[7] = new Point(4, 0);
    // points[8] = new Point(5, 0);
    // points[9] = new Point(1, 5);

    // BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    // LineSegment[] lineSegments = bruteCollinearPoints.segments();
    // for (int i = 0; i < lineSegments.length; i++) {
    //   StdOut.println(lineSegments[i]);
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
