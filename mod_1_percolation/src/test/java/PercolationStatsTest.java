
import main.java.PercolationStats;

public class PercolationStatsTest {
  @Test
  public void createPercolationStats() {
    PercolationStats percolationStats = new PercolationStats(10, 2);

    MatcherAssert.assertThat(percolationStats, IsInstanceOf.instanceOf(PercolationStats.class));
  }
}