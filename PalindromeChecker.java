
public class PalindromeChecker {

    // Checks whether the given text is a palindrome
    public static boolean isPalindrome(String text) {

        // Convert text to lowercase and remove non-alphanumeric characters
        text = text.toLowerCase().replaceAll("[^a-z0-9]", " ");

        // Create a stack and queue to compare characters
        CustomStack stack = new CustomStack(text.length());
        CustomQueue queue = new CustomQueue(text.length());

        // Insert each character into both stack and queue
        for (int i = 0; i < text.length(); i++) {
            int value = text.charAt(i);
            stack.push(value);
            queue.enqueue(value);
        }

        // Compare characters in reverse order using stack
        // and in original order using queue
        while (!stack.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                return false; // Characters do not match
            }
        }

        // All characters matched, so the text is a palindrome
        return true;
    }

    // Main method to test the palindrome checker
    public static void main(String[] args) {

        // Sample word for testing
        String word = "hello";

        // Display whether the word is a palindrome
        if (isPalindrome(word)) {
            System.out.println(word + " is a palindrome.");
        } else {
            System.out.println(word + " is not a palindrome.");
        }
    }
}
