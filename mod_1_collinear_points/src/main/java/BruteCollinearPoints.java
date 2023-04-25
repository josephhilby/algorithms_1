package main.java;

// import java.util.Comparator;
import java.util.Arrays;
// import javax.sound.sampled.Line;
// import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.StdOut;

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
    Arrays.sort(points);
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N - 3; j++) {
        double ij = points[i].slopeTo(points[j]);
        for (int k = j + 1; k < N - 2; k++) {
          double ik = points[i].slopeTo(points[k]);
          for (int l = k + 1; l < N - 1; l++) {
            double il = points[i].slopeTo(points[l]);
            if (ij == ik && ik == il) {
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

  public static void main (String[] args) {
  }
}
