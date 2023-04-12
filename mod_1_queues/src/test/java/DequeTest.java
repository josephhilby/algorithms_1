package test.java;

import org.junit.Test;

import main.java.Deque;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsEqual;
import java.util.Iterator;
// import org.junit.Test;

public class DequeTest {
  @Test
  public void createDeque() {
    Deque<String> deque = new Deque<String>();
    MatcherAssert.assertThat(deque, IsInstanceOf.instanceOf(Deque.class));
    MatcherAssert.assertThat(deque.isEmpty(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(0));
  }

  @Test
  public void addFirstString() {
    Deque<String> deque = new Deque<String>();
    String stringFirst = "first";
    String stringSecond = "second";

    deque.addFirst(stringFirst);
    MatcherAssert.assertThat(deque.isEmpty(), IsEqual.equalTo(false));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(1));

    deque.addFirst(stringSecond);
    MatcherAssert.assertThat(deque.isEmpty(), IsEqual.equalTo(false));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(2));
  }

  @Test
  public void addLastString() {
    Deque<String> deque = new Deque<String>();
    String stringLast = "last";
    String stringSecondLast = "second last";

    deque.addLast(stringLast);
    MatcherAssert.assertThat(deque.isEmpty(), IsEqual.equalTo(false));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(1));

    deque.addLast(stringSecondLast);
    MatcherAssert.assertThat(deque.isEmpty(), IsEqual.equalTo(false));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(2));
  }

  @Test
  public void removeFirstString() {
    Deque<String> deque = new Deque<String>();
    String stringFirst = "first";
    String stringSecond = "second";
    deque.addLast(stringFirst);
    deque.addLast(stringSecond);

    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(2));

    String first = deque.removeFirst();
    MatcherAssert.assertThat(first, IsEqual.equalTo("first"));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(1));

    String second = deque.removeFirst();
    MatcherAssert.assertThat(second, IsEqual.equalTo("second"));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(0));
  }

  @Test
  public void removeLastString() {
    Deque<String> deque = new Deque<String>();
    String stringSecondLast = "second last";
    String stringLast = "last";
    deque.addLast(stringSecondLast);
    deque.addLast(stringLast);

    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(2));

    String last = deque.removeLast();
    MatcherAssert.assertThat(last, IsEqual.equalTo("last"));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(1));

    String secondLast = deque.removeLast();
    MatcherAssert.assertThat(secondLast, IsEqual.equalTo("second last"));
    MatcherAssert.assertThat(deque.size(), IsEqual.equalTo(0));
  }

  @Test
  public void iteratorTest() {
    Deque<String> deque = new Deque<String>();
    String stringFirst = "first";
    String stringSecond = "second";
    deque.addLast(stringFirst);
    deque.addLast(stringSecond);

    Iterator<String> list = deque.iterator();

    MatcherAssert.assertThat(list.hasNext(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(list.next(), IsEqual.equalTo("first"));
    MatcherAssert.assertThat(list.hasNext(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(list.next(), IsEqual.equalTo("second"));
    MatcherAssert.assertThat(list.hasNext(), IsEqual.equalTo(false));
  }
}
