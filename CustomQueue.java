public class CustomQueue {
    private int[] arr;
    private int front;
    private int rear;
    private int size;

    public CustomQueue(int capacity) {
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }

    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full!");
        }

        rear = (rear + 1) % arr.length;
        arr[rear] = value;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }

        int value = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return value;
    }
}
