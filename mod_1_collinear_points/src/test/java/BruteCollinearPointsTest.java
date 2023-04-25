package test.java;

import org.junit.Test;

import main.java.Point;
import main.java.BruteCollinearPoints;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;

import java.util.Comparator;
import java.util.Arrays;

public class BruteCollinearPointsTest {
  @Test
  public void createBruteCollinearPoints() {
    Point[] points = new Point[4];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    MatcherAssert.assertThat(bruteCollinearPoints, IsInstanceOf.instanceOf(BruteCollinearPoints.class));
  }

  @Test
  public void findSegmentsN4() {
    Point[] points = new Point[4];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    MatcherAssert.assertThat(bruteCollinearPoints.numberOfSegments(), IsEqual.equalTo(1));
  }

  @Test
  public void findSegmentsN11() {
    Point[] points = new Point[10];
    points[0] = new Point(0, 0);
    points[1] = new Point(1, 1);
    points[2] = new Point(2, 2);
    points[3] = new Point(4, 4);
    points[4] = new Point(5, 5);
    points[5] = new Point(1, 3);
    points[6] = new Point(3, 1);
    points[7] = new Point(4, 0);
    points[8] = new Point(5, 0);
    points[9] = new Point(1, 5);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    MatcherAssert.assertThat(bruteCollinearPoints.numberOfSegments(), IsEqual.equalTo(2));
  }
}
