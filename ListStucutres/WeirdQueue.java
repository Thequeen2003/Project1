public class WeirdQueue {
    private Stack s1; // Stack for enqueuing
    private Stack s2; // Stack for dequeuing

    // Constructor
    public WeirdQueue() {
        this.s1 = new Stack();
        this.s2 = new Stack();
    }

    // Enqueue an item onto the queue
    public void enqueue(Object o) {
        s1.push(o); // Use the Stack's push, which is a constant time operation
    }

    // Dequeue an item from the queue
    public Object dequeue() {
        // If s2 is empty, transfer all items from s1 to s2
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        if (s2.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return s2.pop();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    // Get the size of the queue
    public int size() {
        return s1.size() + s2.size();
    }

    public static void main(String[] args) {
        WeirdQueue queue = new WeirdQueue();

        // Test basic enqueue and dequeue
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assert queue.dequeue().equals("A");
        assert queue.dequeue().equals("B");

        // Test FIFO behavior with multiple operations
        queue.enqueue("X");
        queue.enqueue("Y");
        assert queue.dequeue().equals("X");
        queue.enqueue("Z");
        assert queue.dequeue().equals("Y");
        assert queue.dequeue().equals("Z");

        // Test with a large number of operations
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 1000; i++) {
            assert queue.dequeue().equals(i);
        }
        assert queue.isEmpty();

        System.out.println("All tests passed!");
    }
}

