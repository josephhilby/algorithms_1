package main.java;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] itemArray = (Item[]) new Object[2];
  private int N = 0;
  private int head = 0;
  private int tail = 0;

  // construct an empty randomized queue
  public RandomizedQueue() {
  }

  private int randomIntOnArray() {
    return StdRandom.uniformInt(head, tail);
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return N == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return N;
  }

  // resize the array
  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    int offset = 0;
    for (int i = 0; i < N; i++) {
      while (itemArray[i + offset] == null) {
        offset++;
      }
      copy[i] = itemArray[i + offset];
    }
    itemArray = copy;
    head = 0;
    tail = N;
  }

  // add the item at tail
  public void enqueue(Item item) {
    if (tail == itemArray.length) {
      resize(2 * N);
    }
    itemArray[tail] = item;
    tail++;
    N++;
  }

  // // remove item and return item at head
  // public Item dequeue() {
  //   Item item = itemArray[head];
  //   itemArray[head] = null;
  //   if (N > 0 && N == itemArray.length/4) {
  //     resize(itemArray.length/2);
  //   }
  //   head++;
  //   N--;
  //   return item;
  // }

  // remove and return a random item
  public Item dequeue() {
    if (N == 0) {
      throw new NoSuchElementException("Stack is empty.");
    }
    N--;
    int randomInt = randomIntOnArray();
    Item item = itemArray[randomInt];
    while (item == null) {
      randomInt = randomIntOnArray();
      item = itemArray[randomInt];
    }
    itemArray[randomInt] = null;
    if (N > 0 && N == itemArray.length/4) {
      resize(itemArray.length/2);
    }
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    return itemArray[randomIntOnArray()];
  }

  // // return an independent iterator over items from tail to head
  // public Iterator<Item> iterator() {
  //   return new StackIterator();
  // }

  // private class StackIterator implements Iterator<Item> {
  //   private int i = tail;
  //   private int n = N;

  //   public boolean hasNext(){ return n > 0; }
  //   public void remove() {
  //     throw new UnsupportedOperationException(
  //       ".remove() is not an accepted function. Please try .hasNext() or .next()."
  //     );
  //   }
  //   public Item next() {
  //     if (i == 0) {
  //       throw new NoSuchElementException("Stack is empty");
  //     }
  //     i--;
  //     n--;
  //     return itemArray[i];
  //   }
  // }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new StackIterator();
  }

  private class StackIterator implements Iterator<Item> {
    private int h = head;
    private int t = tail;
    private int n = N;

    public boolean hasNext(){ return n > 0; }
    public void remove() {
      throw new UnsupportedOperationException(
        ".remove() is not an accepted function. Please try .hasNext() or .next()."
      );
    }
    public Item next() {
      if (t == h) {
        throw new NoSuchElementException("At the end of the stack.");
      }
      t--;
      n--;
      return itemArray[t];
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String first = "first";
    String second = "second";
    String third = "third";
    String fourth = "fourth";
    String fifth = "fifth";

    StdOut.println("Randomized Queue Stack Created");
    StdOut.println("Initial Size: " + queue.size());
    StdOut.println("");

    queue.enqueue(fifth);
    StdOut.println("String (" + fifth + ") added to the head of the Queue Stack");
    queue.enqueue(fourth);
    StdOut.println("String (" + fourth + ") is the new tail of the Queue Stack");
    queue.enqueue(third);
    StdOut.println("String (" + third + ") is the new tail of the Queue Stack");
    queue.enqueue(second);
    StdOut.println("String (" + third + ") is the new tail of the Queue Stack");
    queue.enqueue(first);
    StdOut.println("String (" + first + ") is the new tail of the Queue Stack");
    StdOut.println("");

    Iterator<String> stack = queue.iterator();
    StdOut.println("Iterator created for Queue Stack.");
    if (stack.hasNext()) {
      StdOut.println("Iterator can read stack AND there is information to read.");
    } else {
      StdOut.println("Iterator can't read stack OR there is no information to read.");
    }
    StdOut.println("");

    StdOut.println("First string in stack: " + stack.next());
    StdOut.println("Second string in stack: " + stack.next());
    StdOut.println("Third string in stack: " + stack.next());
    StdOut.println("Fourth string in stack: " + stack.next());
    StdOut.println("Fifth string in stack: " + stack.next());
    StdOut.println("");

    if (stack.hasNext()) {
      StdOut.println("Somehow the stack is still going.");
    } else {
      StdOut.println("Iterator is at the end of the stack.");
    }
    StdOut.println("");

    StdOut.println("Randomly dequeued: " + queue.dequeue());
    StdOut.println("Randomly dequeued: " + queue.dequeue());
    StdOut.println("Randomly dequeued: " + queue.dequeue());
    StdOut.println("Randomly dequeued: " + queue.dequeue());
    StdOut.println("Randomly dequeued: " + queue.dequeue());
  }
}
