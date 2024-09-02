//Colin Gibbons-Fly
//Project 1
// March 26, 2024

public enum Delimiter {
    // Enum constants representing different types of delimiters
    LEFT_SQUARE_BRACKET('['),
    RIGHT_SQUARE_BRACKET(']'),
    LEFT_CURLY_BRACKET('{'),
    RIGHT_CURLY_BRACKET('}'),
    LEFT_PARENTHESES('('),
    RIGHT_PARENTHESES(')');

    private final char value; // Character value associated with each delimiter

    // Constructor for enum, associates a character value with each constant
    Delimiter(char value) {
        this.value = value;
    }

    // Getter method for the character value of the delimiter
    public char getValue() {
        return value;
    }

    // Static method to get the corresponding Delimiter enum constant for a given character
    public static Delimiter getDelimiter(char delimiter) {
        // Switch expression based on the input character
        return switch (delimiter) {
            case '[' -> LEFT_SQUARE_BRACKET; // Returns corresponding enum constant
            case ']' -> RIGHT_SQUARE_BRACKET;
            case '{' -> LEFT_CURLY_BRACKET;
            case '}' -> RIGHT_CURLY_BRACKET;
            case '(' -> LEFT_PARENTHESES;
            case ')' -> RIGHT_PARENTHESES;
            default -> null; // Returns null if no matching delimiter is found
        };
    }
}
