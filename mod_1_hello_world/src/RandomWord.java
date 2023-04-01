import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// NOTES FOR SELF
// ` $ javac -classpath "../lib/algs4.jar" RandomWord.java`
// This will assign the classpath in zsh (currently the java project plugin in VS is working to accomplish this im compile time but not run time)
// More info at [https://algs4.cs.princeton.edu/code/] and [https://code.visualstudio.com/docs/java/java-project#_configure-classpath-for-unmanaged-folders]
// Currently the only way I can get this to work is to export the given file from /bin/ into a .jar file and run it with java -jar mod_1_hello_world.jar < animals.txt
// Other resources for getting java to work on VS code:
// https://stackoverflow.com/questions/17973970/how-can-i-solve-java-lang-noclassdeffounderror
// https://en.wikipedia.org/wiki/Classpath

// PROMPT
//Write a program RandomWord.java that reads a sequence of words from standard input
//and prints one of those words uniformly at random. Do not store the words in an
//array or list. Instead, use Knuth’s method: when reading the ith word, select it
//with probability 1/i to be the champion,replacing the previous champion.
// After reading all of the words, print the surviving champion.

// PLEASE NOTE
// This is my first time with Java. For this class RandomWord, I relied heavily on code in the github repo listed below.
// https://github.com/camilamaia/jornada-big-tech/blob/main/coursera-algorithms-part-1/hello/RandomWord.java
// While I did build this file slowly and worked through the problem. I used the above repo for line 29 and 35.

public class RandomWord {

    public static void main(String[] args) {
        String champion = "";
        double i = 1.0;

        while (!StdIn.isEmpty()) {
          String word = StdIn.readString();
          if (StdRandom.bernoulli(1 / i)) {
            champion = word;
          }
          i++;
        }
        StdOut.println(champion);
    }
}