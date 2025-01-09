public class HelloJava {
    public static void main(String[] args) {
        int num = 1000000000, x; // 4
        long l = 2_000_985_486_726_898_236L;// 8

        float f = 20.5f; // 8
        double d = 20.5; // 8/16
        String myString = "hello world"; // varies 48
        char c = 'a'; // 2
        x = (int) (num + l + f + d); // Casting is necessary for this line.

        System.out.println("Hello, Java!" + num + l + f + d);
        System.out.println("The result is: " + x + (num - 4) + (num + 4));
        System.out.println("String: " + myString + " " + c + " " + num + l + f + d);
        System.out.println("Global population" + l);
    }
}

// Cheat Sheet
// This cheat sheet contains the most important takeaways that lead up to
// section one.

// 1. Getting Started
// class: contains all of your code.

// main(): entry point of your app.

// javac <file-name>.java: compiles your code.

// java <file-name>: runs the compiled code.

// Coding Pitfalls
// None yet.

// Good coding habits
// The name of a class must be CamelCase.

// Tips and tricks
// Use the up key to run previous terminal commands.

// Use the tab key to autocomplete.