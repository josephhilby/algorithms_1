package test.java;

import org.junit.Test;

import main.java.Board;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardTest {
  @Test
  public void createBoard() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    MatcherAssert.assertThat(board, IsInstanceOf.instanceOf(Board.class));
    MatcherAssert.assertThat(board.dimension(), IsEqual.equalTo(3));
    MatcherAssert.assertThat(board.isGoal(), IsEqual.equalTo(true));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createBoardWithNullTiles() {
    new Board(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createBoardWithDifferentNumberOfRowsAndColumns() {
    new Board(new int[][]{{1, 2, 3}, {4, 5, 6}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void createBoardWithLessThanTwoRowsAndColumns() {
    new Board(new int[][]{{1}, {2}});
  }

  @Test
  public void toStringTest() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    MatcherAssert.assertThat(board.toString(), IsEqual.equalTo("3\n 1 2 3\n 4 5 6\n 7 8 0\n"));
  }

  @Test
  public void dimensionTest() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    MatcherAssert.assertThat(board.dimension(), IsEqual.equalTo(3));
  }

  @Test
  public void hammerTest() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    MatcherAssert.assertThat(board.hamming(), IsEqual.equalTo(0));

    Board badBoardOne = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 5}});
    MatcherAssert.assertThat(badBoardOne.hamming(), IsEqual.equalTo(1));

    Board badBoardTwo = new Board(new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
    MatcherAssert.assertThat(badBoardTwo.hamming(), IsEqual.equalTo(5));
  }

  @Test
  public void manhattanTest() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    MatcherAssert.assertThat(board.manhattan(), IsEqual.equalTo(0));

    Board badBoardOne = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 5}});
    MatcherAssert.assertThat(badBoardOne.manhattan(), IsEqual.equalTo(2));

    Board badBoardTwo = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
    MatcherAssert.assertThat(badBoardTwo.manhattan(), IsEqual.equalTo(10));
  }

  @Test
  public void equalsTest() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    MatcherAssert.assertThat(board.equals(board), IsEqual.equalTo(true));

    Board boardTwo = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    MatcherAssert.assertThat(board.equals(boardTwo), IsEqual.equalTo(true));

    Board boardThree = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
    MatcherAssert.assertThat(board.equals(boardThree), IsEqual.equalTo(false));

    Board boardFour = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
    MatcherAssert.assertThat(boardThree.equals(boardFour), IsEqual.equalTo(true));
  }

  @Test
  public void neighborsTest() {
    Board board = new Board(new int[][]{{1, 2, 3},
                                        {4, 0, 6},
                                        {7, 8, 5}});
    Iterator<Board> neighbors = board.neighbors().iterator();
    MatcherAssert.assertThat(neighbors.next(), IsEqual.equalTo(new Board(new int[][]{{1, 0, 3},
                                                                                     {4, 2, 6},
                                                                                     {7, 8, 5}})));

    MatcherAssert.assertThat(neighbors.next(), IsEqual.equalTo(new Board(new int[][]{{1, 2, 3},
                                                                                     {4, 8, 6},
                                                                                     {7, 0, 5}})));

    MatcherAssert.assertThat(neighbors.next(), IsEqual.equalTo(new Board(new int[][]{{1, 2, 3},
                                                                                     {0, 4, 6},
                                                                                     {7, 8, 5}})));

    MatcherAssert.assertThat(neighbors.next(), IsEqual.equalTo(new Board(new int[][]{{1, 2, 3},
                                                                                     {4, 6, 0},
                                                                                     {7, 8, 5}})));
    MatcherAssert.assertThat(neighbors.hasNext(), IsEqual.equalTo(false));
  }
}
