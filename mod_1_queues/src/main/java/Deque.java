package main.java;

import java.util.Iterator;

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
    return first == null;
  }

  // return the number of items on the deque
  public int size() {
    return N;
  }

  // add the item to the front
  public void addFirst(Item item) {
    Node oldFirst = first;
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
    Node oldLast = last;
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
    Item oldFirst = first.item;
    first = first.next;
    if (isEmpty()) {
      last = null;
    }
    N--;
    return oldFirst;
  }

  // remove and return the item from the back
  public Item removeLast() {
    Item oldLast = last.item;
    last = last.previous;
    if (isEmpty()) {
      last = null;
    }
    N--;
    return oldLast;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new ItemIterator();
  }

  private class ItemIterator implements Iterator<Item> {
    private Node current = first;
    public boolean hasNext() { return current != null; }
    public void remove() { /* not supported */ }
    public Item next() {
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  // unit testing (required)
  public static void main(String[] args){

  }
}
