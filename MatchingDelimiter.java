//Colin Gibbons-Fly
//Project 1
// March 26, 2024

import java.io.*; // Import necessary IO classes
import java.util.ArrayList; // Import the ArrayList class for dynamic arrays
import java.util.List; // Import the List interface to use with ArrayList

public class MatchingDelimiter {
    private BufferedReader br; // Reader to read from a file
    private String line; // String to hold each line read from the file
    private int delimiterCount; // Counter for the number of delimiters found
    private int lineIndex; // Index to track the current position within a line
    private int lineCount; // Counter for the number of lines read
    private List<Delimiter> delimiterList; // List to store found delimiters

    // Getter method for the total line count
    public int getLineCount() {
        return lineCount;
    }

    // Method to read characters from a line and identify delimiters
    public List<Delimiter> readCharDelimiter() throws IOException {
        delimiterList = new ArrayList<>(); // Initialize the list of delimiters
        if ((line = br.readLine()) != null) { // Read a line; if not null, process it
            skipComment(); // Skip over any comments in the line
            delimiterCount = 0; // Reset delimiter count for the new line
            for (lineIndex = 0; lineIndex < line.length(); lineIndex++) { // Iterate through characters in the line
                skipStringLiteral(); // Skip over string literals to avoid false positives
                skipInlineComments(); // Skip over inline comments
                Delimiter delimiter = Delimiter.getDelimiter(line.charAt(lineIndex)); // Check if current char is a delimiter
                if (delimiter != null) { // If a delimiter is found,
                    delimiterCount++; // increment count
                    delimiterList.add(delimiter); // and add to the list

                }

            }

            lineCount++; // Increment the line count after processing
            return delimiterList; // Return the list of delimiters found
        }
        lineCount--; // Decrement line count if line is null (end of file)
        return null; // Return null to indicate the end of file or no delimiters found
    }

    // Constructor to initialize the BufferedReader with a given filename
    public MatchingDelimiter(String filename) {
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename))); // Open the file
        } catch (FileNotFoundException e) { // Catch exception if file is not found
            throw new RuntimeException(e); // Rethrow as unchecked exception
        }
    }

    // Method to skip string literals in a line
    private void skipStringLiteral() {
        char stChar = line.charAt(lineIndex); // Get the current character
        if (stChar == '"' || stChar == '\'') { // Check if it starts a string literal
            while (lineIndex < line.length() - 1) { // Iterate until the end of the literal
                lineIndex++; // Move to the next character
                stChar = line.charAt(lineIndex); // Update current character

                if (stChar == '"' || stChar == '\'') { // Check if the literal ends
                    break; // Exit the loop
                }
            }
        }
    }

    // Method to skip inline comments
    public void skipInlineComments() {
        if (line.contains("//")) { // Check if line contains an inline comment
            line = line.split("//")[0]; // Split the line and keep only the code part
        }
    }

    // Method to skip entire line or block comments
    private void skipComment() throws IOException {
        if (line.startsWith("//")) { // Check for line comments
            while ((line = br.readLine()) != null) { // Read new lines until the comment ends
                lineCount++; // Increment line count
                if (!line.startsWith("//")) { // Break out if the next line is not a comment
                    break;
                }
            }
        } else if (line.startsWith("/*")) { // Check for block comments
            while ((line = br.readLine()) != null) { // Read new lines until the end of the block comment
                lineCount++; // Increment line count
                if (line.endsWith("*/")) { // Check if the block comment ends
                    line = br.readLine(); // Read the next line after the comment ends
                    break; // Exit the loop
                }
            }
        }

    }

    // Getter method for the delimiter count
    public int getDelimiterCount() {
        return delimiterCount;
    }
}
