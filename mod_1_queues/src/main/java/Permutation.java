package main.java;

// import main.java.RandomizedQueue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  public static void main(String[] args) {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    int n = Integer.parseInt(args[0]);

    while (!StdIn.isEmpty()) {
      queue.enqueue(StdIn.readString());
    }

    for (int i = 0; i < n; i++) {
      StdOut.print(queue.dequeue());
    }
  }
}
