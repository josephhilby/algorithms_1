package test.java;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import main.java.Percolation;

public class PercolationTest {

  @Test
  public void createPercolation() {
    Percolation percolation = new Percolation(10);

    MatcherAssert.assertThat(percolation, IsInstanceOf.instanceOf(Percolation.class));
    MatcherAssert.assertThat(percolation.numberOfOpenSites(), IsEqual.equalTo(0));
  }

  @Test
  public void checkSiteOnTop() {
    Percolation percolation = new Percolation(10);

    MatcherAssert.assertThat(percolation.isOpen(1, 1), IsEqual.equalTo(false));
    MatcherAssert.assertThat(percolation.isFull(1, 1), IsEqual.equalTo(false));

    MatcherAssert.assertThat(percolation.isOpen(2, 1), IsEqual.equalTo(false));
    MatcherAssert.assertThat(percolation.isFull(2, 1), IsEqual.equalTo(false));

    MatcherAssert.assertThat(percolation.isOpen(1, 2), IsEqual.equalTo(false));
    MatcherAssert.assertThat(percolation.isFull(1, 2), IsEqual.equalTo(false));
  }

  @Test
  public void fillSiteOnTop() {
    Percolation percolation = new Percolation(10);
    percolation.open(1, 1);

    // site (0, 0) should open and fill.
    MatcherAssert.assertThat(percolation.isOpen(1, 1), IsEqual.equalTo(true));
    MatcherAssert.assertThat(percolation.isFull(1, 1), IsEqual.equalTo(true));
    MatcherAssert.assertThat(percolation.numberOfOpenSites(), IsEqual.equalTo(1));

    // sites around it should not open or fill.
    MatcherAssert.assertThat(percolation.isOpen(2, 1), IsEqual.equalTo(false));
    MatcherAssert.assertThat(percolation.isFull(2, 1), IsEqual.equalTo(false));

    MatcherAssert.assertThat(percolation.isOpen(1, 2), IsEqual.equalTo(false));
    MatcherAssert.assertThat(percolation.isFull(1, 2), IsEqual.equalTo(false));
  }

  @Test
  public void percolateToBottom() {
    Percolation percolation = new Percolation(3);
    MatcherAssert.assertThat(percolation.percolates(), IsEqual.equalTo(false));

    percolation.open(1, 1);
    MatcherAssert.assertThat(percolation.percolates(), IsEqual.equalTo(false));

    percolation.open(2, 1);
    MatcherAssert.assertThat(percolation.percolates(), IsEqual.equalTo(false));

    percolation.open(3, 1);
    MatcherAssert.assertThat(percolation.percolates(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(percolation.numberOfOpenSites(), IsEqual.equalTo(3));
  }
}
