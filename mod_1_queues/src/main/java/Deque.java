package main.java;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node first, last;
  private int N = 0;

  private class Node {
    Item item;
    Node next;
    Node previous;
  }

  // construct an empty deque
  public Deque() {
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size() == 0;
  }

  // return the number of items on the deque
  public int size() {
    return N;
  }

  // add the item to the front
  public void addFirst(Item item) {
    checkParams(item);
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    first.previous = null;
    if (isEmpty()) {
      last = first;
    } else {
      oldFirst.previous = first;
    }
    N++;
  }

  // add the item to the back (enqueue)
  public void addLast(Item item) {
    checkParams(item);
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    last.previous = oldLast;
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
    N++;
  }

  // remove and return the item from the front (dequeue)
  public Item removeFirst() {
    validate();
    Item oldFirst = first.item;
    if (size() == 1) {
      nullSet();
    } else {
      first = first.next;
      first.previous = null;
    }
    N--;
    return oldFirst;
  }

  // remove and return the item from the back
  public Item removeLast() {
    validate();
    Item oldLast = last.item;
    if (size() == 1) {
      nullSet();
    } else {
      last = last.previous;
      last.next = null;
    }
    N--;
    return oldLast;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = first;
    public boolean hasNext() { return current != null; }
    public void remove() {
      throw new UnsupportedOperationException(
        ".remove() is not an accepted function. Please try .hasNext() or .next()."
      );
    }
    public Item next() {
      if (current == null) {
        throw new NoSuchElementException("At the end of the Deque List");
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  private void validate() {
    if (isEmpty()) {
      nullSet();
      emptyDeque();
    }
  }

  private void nullSet() {
    first = null;
    last = null;
  }

  private void emptyDeque() {
    throw new NoSuchElementException("List is empty.");
  }

  private void checkParams(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Incorrect object type.");
    }
  }

  // unit testing (required)
  public static void main(String[] args){
    Deque<String> deque = new Deque<String>();
    String stringFirst = "first";
    String stringSecond = "second";
    String stringSecondLast = "second to last";
    String stringLast = "last";

    StdOut.println("Deque List Created");
    StdOut.println("Initial Size: " + deque.size());
    StdOut.println("");

    deque.addFirst(stringSecond);
    StdOut.println("String (" + stringSecond + ") added to the beginning of Deque List");
    deque.addFirst(stringFirst);
    StdOut.println("String (" + stringFirst + ") added to the beginning of Deque List");
    StdOut.println("Deque List Size: " + deque.size());
    StdOut.println("");

    deque.addLast(stringSecondLast);
    StdOut.println("String (" + stringSecondLast + ") added to the end of Deque List");
    deque.addLast(stringLast);
    StdOut.println("String (" + stringLast + ") added to the end of Deque List");
    StdOut.println("Deque List Size: " + deque.size());
    StdOut.println("");

    Iterator<String> listDeque = deque.iterator();
    StdOut.println("Iterator created for Deque.");
    if (listDeque.hasNext()) {
      StdOut.println("Iterator can read deque AND there is information to read.");
    } else {
      StdOut.println("Iterator can't read deque OR there is no information to read.");
    }
    StdOut.println("");

    StdOut.println("First string in deque: " + listDeque.next());
    StdOut.println("Second string in deque: " + listDeque.next());
    StdOut.println("Third string in deque: " + listDeque.next());
    StdOut.println("Fourth string in deque: " + listDeque.next());
    StdOut.println("");

    if (listDeque.hasNext()) {
      StdOut.println("Somehow the Deque List is still going.");
    } else {
      StdOut.println("Iterator is at the end of the Deque List.");
    }
    StdOut.println("");

    StdOut.println("First string (" + deque.removeFirst() + ") removed from Deque List");
    StdOut.println("Last string (" + deque.removeLast() + ") removed from Deque List");
    StdOut.println("Deque List Size: " + deque.size());
    StdOut.println("");

    Iterator<String> listDequeTwo = deque.iterator();
    StdOut.println("New Iterator created for Deque.");
    if (listDequeTwo.hasNext()) {
      StdOut.println("New Iterator can read deque AND there is information to read.");
    } else {
      StdOut.println("New Iterator can't read deque OR there is no information to read.");
    }
    StdOut.println("");

    StdOut.println("First string in deque: " + listDequeTwo.next());
    StdOut.println("Second string in deque: " + listDequeTwo.next());
    StdOut.println("");

    if (listDequeTwo.hasNext()) {
      StdOut.println("Somehow the Deque List is still going.");
    } else {
      StdOut.println("Iterator is at the end of the New Deque List.");
    }
  }
}
