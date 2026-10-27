
public class CustomQueue {

    // Array to store queue elements
    private int[] arr;

    // Index of the front element
    private int front;

    // Index of the rear element
    private int rear;

    // Current number of elements in the queue
    private int size;

    // Constructor to initialize queue with given capacity
    public CustomQueue(int capacity) {
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Checks whether the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Checks whether the queue is full
    public boolean isFull() {
        return size == arr.length;
    }

    // Adds a new element at the rear of the queue
    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full!!!");
        }

        // Circular increment of rear index
        rear = (rear + 1) % arr.length;

        arr[rear] = value;
        size++;
    }

    // Removes and returns the front element
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!!!");
        }

        int value = arr[front];

        // Circular increment of front index
        front = (front + 1) % arr.length;

        size--;
        return value;
    }
}
