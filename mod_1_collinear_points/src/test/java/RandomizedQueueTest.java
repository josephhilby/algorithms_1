package test.java;

import org.junit.Test;

import main.java.RandomizedQueue;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueueTest {
  @Test
  public void createDeque() {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    MatcherAssert.assertThat(queue, IsInstanceOf.instanceOf(RandomizedQueue.class));
    MatcherAssert.assertThat(queue.isEmpty(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(queue.size(), IsEqual.equalTo(0));
  }

  @Test
  public void enqueueItem() {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String first = "first";
    String second = "second";

    queue.enqueue(first);
    MatcherAssert.assertThat(queue.isEmpty(), IsEqual.equalTo(false));
    MatcherAssert.assertThat(queue.size(), IsEqual.equalTo(1));

    queue.enqueue(second);
    MatcherAssert.assertThat(queue.size(), IsEqual.equalTo(2));
  }

  @Test
  public void dequeueRandomItem() {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String first = "first";
    String second = "second";
    String[] list = new String[] { first, second };
    queue.enqueue(first);
    queue.enqueue(second);

    String randomValue = queue.dequeue();
    boolean contains = Arrays.stream(list).anyMatch(randomValue::equals);
    MatcherAssert.assertThat(contains, IsEqual.equalTo(true));
    MatcherAssert.assertThat(queue.isEmpty(), IsEqual.equalTo(false));
    MatcherAssert.assertThat(queue.size(), IsEqual.equalTo(1));

    String remainingString;
    if (randomValue == first) {
      remainingString = second;
    } else {
      remainingString = first;
    }

    MatcherAssert.assertThat(queue.dequeue(), IsEqual.equalTo(remainingString));
    MatcherAssert.assertThat(queue.isEmpty(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(queue.size(), IsEqual.equalTo(0));
  }

  @Test
  public void randomSample() {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String first = "first";
    String second = "second";

    queue.enqueue(first);
    MatcherAssert.assertThat(queue.sample(), IsEqual.equalTo(first));
    MatcherAssert.assertThat(queue.isEmpty(), IsEqual.equalTo(false));
    MatcherAssert.assertThat(queue.size(), IsEqual.equalTo(1));

    queue.enqueue(second);
    String[] list = new String[]{first, second};
    String randomValue = queue.sample();
    boolean contains = Arrays.stream(list).anyMatch(randomValue::equals);

    MatcherAssert.assertThat(contains, IsEqual.equalTo(true));
    MatcherAssert.assertThat(queue.isEmpty(), IsEqual.equalTo(false));
    MatcherAssert.assertThat(queue.size(), IsEqual.equalTo(2));
  }

  @Test
  public void iteratorTest() {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String first = "first";
    String second = "second";
    queue.enqueue(second);
    queue.enqueue(first);

    Iterator<String> stack = queue.iterator();

    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(stack.next(), IsEqual.equalTo("first"));
    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(stack.next(), IsEqual.equalTo("second"));
    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(false));
  }

  @Test(expected = NoSuchElementException.class)
  public void iteratorNextAtEnd() {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String first = "first";
    queue.enqueue(first);

    Iterator<String> stack = queue.iterator();

    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(true));
    MatcherAssert.assertThat(stack.next(), IsEqual.equalTo("first"));
    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(false));
    stack.next();
  }

  @Test
  public void iteratorNextWithNullInSet() {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String first = "first";
    String second = "second";
    String third = "third";
    String fourth = "fourth";
    String fifth = "fifth";
    queue.enqueue(fifth);
    queue.enqueue(fourth);
    queue.enqueue(third);
    queue.enqueue(second);
    queue.enqueue(first);

    Iterator<String> stack = queue.iterator();

    queue.dequeue();
    queue.dequeue();

    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(true));
    stack.next();
    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(true));
    stack.next();
    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(true));
    stack.next();
    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(true));
    stack.next();
    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(true));
    stack.next();
    MatcherAssert.assertThat(stack.hasNext(), IsEqual.equalTo(false));
  }

  @Test(expected = NoSuchElementException.class)
  public void dequeueWithEmptyStack() {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String first = "first";
    String second = "second";
    queue.enqueue(second);
    queue.enqueue(first);

    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
  }
}
