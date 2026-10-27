
public class CustomStack {

    // Array used to store stack elements
    private int[] arr;

    // Keeps track of the top element's index
    private int top;

    // Constructor initializes stack with given capacity
    public CustomStack(int capacity) {
        arr = new int[capacity];
        top = -1; // Stack is initially empty
    }

    // Checks whether the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Checks whether the stack has reached its maximum capacity
    public boolean isFull() {
        return top == arr.length - 1;
    }

    // Pushes a new element onto the top of the stack
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full!");
            return;
        }

        // Increment top and insert the new value
        arr[++top] = value;
    }

    // Removes and returns the top element from the stack
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }

        // Return the top element and decrease the top index
        return arr[top--];
    }

    // Returns the top element without removing it
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }

        return arr[top];
    }
}
