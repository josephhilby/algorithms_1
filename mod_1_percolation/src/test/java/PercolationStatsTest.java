package test.java;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import main.java.PercolationStats;

public class PercolationStatsTest {
  @Test
  public void createPercolationStats() {
    PercolationStats percolationStats = new PercolationStats(10, 2);

    MatcherAssert.assertThat(percolationStats, IsInstanceOf.instanceOf(PercolationStats.class));
  }
}