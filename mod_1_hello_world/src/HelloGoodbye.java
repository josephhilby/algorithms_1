public class HelloGoodbye {

    public static void main(String[] args) {

        // Prints:
        // "Hello {args[0]} and {args[1]}"
        // "Goodbye {args[0]} and {args[1]}"
        // in the terminal window.
        System.out.println("Hello " + args[0] + " and " + args[1] + ".");
        System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
    }

}