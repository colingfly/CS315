//Colin Gibbons-Fly
//Project 1
// March 26, 2024

import java.io.File; // Import the File class for file handling
import java.io.IOException; // Import IOException for handling IO errors
import java.util.List; // Import the List interface for storing lists of objects
import java.util.Scanner; // Import the Scanner class for reading input
import java.util.Stack; // Import the Stack class for using stacks

public class MatchingDelimiterChecker {
    public static void main(String[] args) throws IOException {
        MatchingDelimiter matchingDelimiter; // Declare a MatchingDelimiter object
        Stack<Delimiter> leftDelimiterStack = new Stack<>(); // Create a stack for storing left delimiters
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for reading user input
        System.out.println("Please enter the file name: "); // Prompt user to enter file name
        String filename = scanner.next(); // Read file name from user

        while (true) { // Loop until a valid filename is entered
            if (!new File(filename).exists()) { // Check if file does not exist
                System.out.println("Please enter the file name: "); // Prompt again
                filename = scanner.next(); // Read next input

            } else
                break; // Break loop if file exists
        }
        matchingDelimiter = new MatchingDelimiter(filename); // Instantiate MatchingDelimiter with the file name
        List<Delimiter> delimiterList; // Declare a list to hold delimiters
        while ((delimiterList = matchingDelimiter.readCharDelimiter()) != null) { // Read delimiters until EOF
            if (!delimiterList.isEmpty()) { // Check if there are delimiters in the line
                for (Delimiter value : delimiterList) { // Iterate through all delimiters

                    if (value.name().startsWith("LEFT")) { // Check if it's a left delimiter

                        leftDelimiterStack.push(value); // Push it onto the stack
                    } else { // If it's a right delimiter
                        if (leftDelimiterStack.isEmpty()) { // Check if there's no corresponding left delimiter
                            System.out.println(value.name() + " Mismatched at line " + matchingDelimiter.getLineCount()); // Print mismatch message
                            return; // Exit program
                        }
                        String popValue = leftDelimiterStack.pop().name(); // Pop the last left delimiter
                        if (!popValue.substring(popValue.indexOf("_")).equals(value.name().substring(value.name().indexOf("_")))) { // Check if delimiters match
                            System.out.println(value.name() + " Mismatched at line " + matchingDelimiter.getLineCount()); // Print mismatch message
                            return; // Exit program
                        }
                    }
                }
            }

        }
        if (leftDelimiterStack.isEmpty()) // Check if there are no unmatched left delimiters
            System.out.println("Everything is alright, file is well formatted"); // Print success message
        else System.out.println("Curly Bracket Mismatched at line " + matchingDelimiter.getLineCount()); // Print mismatch message for remaining delimiters
    }
}
