package test.java;

import org.junit.Test;

import main.java.Point;
import main.java.BruteCollinearPoints;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;

// import java.util.Comparator;
// import java.util.Arrays;

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
    points[1] = new Point(1, 3);
    points[2] = new Point(1, 1);
    points[3] = new Point(2, 2);
    points[4] = new Point(4, 4);
    points[5] = new Point(5, 5);
    points[6] = new Point(3, 1);
    points[7] = new Point(4, 0);
    points[8] = new Point(5, 0);
    points[9] = new Point(1, 5);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    MatcherAssert.assertThat(bruteCollinearPoints.numberOfSegments(), IsEqual.equalTo(2));
  }

  @Test
  public void testSegmentsN6() {
    Point[] points = new Point[6];
    points[0] = new Point(19000, 10000);
    points[1] = new Point(18000, 10000);
    points[2] = new Point(32000, 10000);
    points[3] = new Point(21000, 10000);
    points[4] = new Point(1234, 5678);
    points[5] = new Point(14000, 10000);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    MatcherAssert.assertThat(bruteCollinearPoints.numberOfSegments(), IsEqual.equalTo(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSegmentsWithNull() {
    Point[] points = new Point[6];
    points[0] = new Point(19000, 10000);
    points[1] = new Point(18000, 10000);
    points[2] = new Point(32000, 10000);
    points[3] = new Point(21000, 10000);
    points[4] = null;
    points[5] = new Point(14000, 10000);

    new BruteCollinearPoints(points);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    Point[] points = null;

    new BruteCollinearPoints(points);
  }

  @Test
  public void testSegmentsN8() {
    Point[] points = new Point[8];
    points[0] = new Point(10000, 0);
    points[1] = new Point(0, 10000);
    points[2] = new Point(3000, 7000);
    points[3] = new Point(7000, 3000);
    points[4] = new Point(20000, 21000);
    points[5] = new Point(3000, 4000);
    points[6] = new Point(14000, 15000);
    points[7] = new Point(6000, 7000);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    MatcherAssert.assertThat(bruteCollinearPoints.numberOfSegments(), IsEqual.equalTo(2));
  }
}
