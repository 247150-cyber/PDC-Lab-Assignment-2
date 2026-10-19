public class CustomStack 
{
    private int[] arr;
    private int top;

    public CustomStack(int capacity) 
    {
        arr = new int[capacity];
        top = -1;
    }

    public boolean isEmpty() 
    {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full!");
            return;
        }

        arr[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }

        return arr[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }

        return arr[top];
    }
}
