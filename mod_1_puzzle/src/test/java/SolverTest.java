package test.java;

import org.junit.Test;

import main.java.Board;
import main.java.Solver;
import main.java.Solver.SearchNode;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;

import java.util.Iterator;
import edu.princeton.cs.algs4.MinPQ;
import java.util.NoSuchElementException;

public class SolverTest {
  @Test
  public void createSolver() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    Solver solver = new Solver(board);
    MatcherAssert.assertThat(solver, IsInstanceOf.instanceOf(Solver.class));
  }

  @Test
  public void createSearchNode() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    Solver solver = new Solver(board);
    SearchNode searchNode = solver.new SearchNode(board, null);
    MatcherAssert.assertThat(searchNode, IsInstanceOf.instanceOf(SearchNode.class));
    MatcherAssert.assertThat(searchNode.board, IsEqual.equalTo(board));
    MatcherAssert.assertThat(searchNode.previous, IsEqual.equalTo(null));
    MatcherAssert.assertThat(searchNode.moves, IsEqual.equalTo(0));
  }

  @Test
  public void insertNodeOnPQ() {
    Board initial = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});
    Board boardOne = new Board(new int[][]{{4, 1, 3}, {0, 2, 5}, {7, 8, 6}});
    Board boardTwo = new Board(new int[][]{{1, 0, 3}, {4, 2, 5}, {7, 8, 6}});
    Solver solver = new Solver(initial);

    SearchNode initialNode = solver.new SearchNode(initial, null);
    SearchNode nodeOne = solver.new SearchNode(boardOne, initialNode);
    SearchNode nodeTwo = solver.new SearchNode(boardTwo, initialNode);

    MinPQ<SearchNode> boardPQ = new MinPQ<>(4);
    boardPQ.insert(nodeOne);
    MatcherAssert.assertThat(boardPQ.size(), IsEqual.equalTo(1));
    MatcherAssert.assertThat(boardPQ.min(), IsEqual.equalTo(nodeOne));
    MatcherAssert.assertThat(boardPQ.min().board.tiles, IsEqual.equalTo(
        new int[][] { { 4, 1, 3 }, { 0, 2, 5 }, { 7, 8, 6 } }));

    MatcherAssert.assertThat(nodeTwo.board.tiles, IsEqual.equalTo(
        new int[][] { { 1, 0, 3 }, { 4, 2, 5 }, { 7, 8, 6 } }));
    boardPQ.insert(nodeTwo);
    MatcherAssert.assertThat(boardPQ.size(), IsEqual.equalTo(2));
    MatcherAssert.assertThat(boardPQ.min(), IsEqual.equalTo(nodeTwo));
    MatcherAssert.assertThat(boardPQ.min().board.tiles, IsEqual.equalTo(
        new int[][] { { 1, 0, 3 }, { 4, 2, 5 }, { 7, 8, 6 } }));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createSolverWithNullBoard() {
    new Solver(null);
  }

  @Test
  public void isSolvableTest() {
    Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    Solver solver = new Solver(board);
    MatcherAssert.assertThat(solver.isSolvable(), IsEqual.equalTo(true));

    Board badBoardOne = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {8, 7, 0}});
    Solver badSolverOne = new Solver(badBoardOne);
    MatcherAssert.assertThat(badSolverOne.isSolvable(), IsEqual.equalTo(false));

    Board badBoardTwo = new Board(new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
    Solver badSolverTwo = new Solver(badBoardTwo);
    MatcherAssert.assertThat(badSolverTwo.isSolvable(), IsEqual.equalTo(true));
  }
}
