package test.java;

import org.junit.Test;

import main.java.Deque;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class DequeTest {
  @Test
  public void createDeque() {
    String string = 'first';
    Deque deque = new Deque<Item>(string);

  }
}
