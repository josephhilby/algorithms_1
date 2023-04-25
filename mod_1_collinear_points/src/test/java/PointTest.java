package test.java;

import org.junit.Test;

import main.java.Point;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;

import java.util.Comparator;
import java.util.Arrays;

public class PointTest {
  @Test
  public void createPoint() {
    Point point = new Point(1, 1);
    MatcherAssert.assertThat(point, IsInstanceOf.instanceOf(Point.class));

    MatcherAssert.assertThat(point.toString(), IsEqual.equalTo("(1, 1)"));
  }

  @Test
  public void compareTo() {
    Point point1 = new Point(1, 1);
    Point point2 = new Point(2, 2);
    Point point3 = new Point(2, 1);

    MatcherAssert.assertThat(point1.compareTo(point2), IsEqual.equalTo(1));
    MatcherAssert.assertThat(point2.compareTo(point1), IsEqual.equalTo(-1));

    MatcherAssert.assertThat(point1.compareTo(point3), IsEqual.equalTo(1));
    MatcherAssert.assertThat(point3.compareTo(point1), IsEqual.equalTo(-1));
  }

  @Test
  public void slopeTo() {
    Point point1 = new Point(1, 1);
    Point point2 = new Point(2, 2);

    Point point3 = new Point(2, 1);
    Point point4 = new Point(1, 2);

    MatcherAssert.assertThat(point1.slopeTo(point2), IsEqual.equalTo(1.0));
    MatcherAssert.assertThat(point2.slopeTo(point1), IsEqual.equalTo(1.0));

    MatcherAssert.assertThat(point1.slopeTo(point3), IsEqual.equalTo(0.0));
    MatcherAssert.assertThat(point3.slopeTo(point1), IsEqual.equalTo(0.0));

    MatcherAssert.assertThat(point1.slopeTo(point4), IsEqual.equalTo(Double.POSITIVE_INFINITY));
    MatcherAssert.assertThat(point4.slopeTo(point1), IsEqual.equalTo(Double.POSITIVE_INFINITY));

    MatcherAssert.assertThat(point1.slopeTo(point1), IsEqual.equalTo(Double.NEGATIVE_INFINITY));
  }

  @Test
  public void slopeOrder() {
    Point point1 = new Point(1, 1);

    Point point2 = new Point(2, 2);
    Point point3 = new Point(2, 1);
    Point point4 = new Point(1, 2);
    Point point5 = new Point(0 , 0);

    Point[] points = { point2, point3, point4, point5 };
    Point[] expected = { point4, point2, point5, point3 };

    Comparator<Point> point1Comparator = point1.slopeOrder();
    Arrays.sort(points, point1Comparator);

    MatcherAssert.assertThat(Arrays.toString(points), IsEqual.equalTo(Arrays.toString(expected)));
  }
}
