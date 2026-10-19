public class PalindromeChecker {

    public static boolean isPalindrome(String text) {
        text = text.toLowerCase().replaceAll("[^a-z0-9]", "");

        CustomStack stack = new CustomStack(text.length());
        CustomQueue queue = new CustomQueue(text.length());

        for (int i = 0; i < text.length(); i++) {
            int value = text.charAt(i);
            stack.push(value);
            queue.enqueue(value);
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String word = "hello";

        if (isPalindrome(word)) {
            System.out.println(word + " is a palindrome.");
        } else {
            System.out.println(word + " is not a palindrome.");
        }
    }
}
