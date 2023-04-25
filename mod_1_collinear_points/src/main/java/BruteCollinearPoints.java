package main.java;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
  private int lineSegmentsN = 0;
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

  private boolean isPreviousLineSegment(Point p, double slope) {
    int N = lineSegmentsN - 1;
    if (lineSegments[N].getSlope() == slope && lineSegments[N].getP().compareTo(p) == -1) {
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
              if (lineSegmentsN > 0 && isPreviousLineSegment(points[i], il)) {
                lineSegmentsN--;
              }
              push(new LineSegment(points[i], points[l]));
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
