package main.java;

import java.util.Comparator;
import java.util.Arrays;
// import javax.sound.sampled.Line;
// import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

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

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    lineSegments = new LineSegment[1];
    int N = points.length;
    for (int i = 0; i < N - 1; i++) {
      int count = 0;
      Comparator<Point> comparator = points[i].slopeOrder();
      Point[] pointsCopy = Arrays.copyOfRange(points, i + 1, N);
      Arrays.sort(pointsCopy, comparator);
      for (int j = 0; j < pointsCopy.length; j++) {
        if (j == pointsCopy.length - 1) {
          if (count >=2) {
            push(new LineSegment(points[i], pointsCopy[j]));
          }
          count = 0;
        } else if (count >=2 && points[i].slopeTo(pointsCopy[j]) != points[i].slopeTo(pointsCopy[j + 1])) {
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

  public static void main (String[] args) {
    Point[] points = new Point[4];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    StdOut.println(bruteCollinearPoints.numberOfSegments());
  }
}
