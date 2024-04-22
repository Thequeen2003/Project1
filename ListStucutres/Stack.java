public class Stack {
    // Use a doubly linked list as the underlying data structure
    private DLinkedList head;

    // Constructor
    public Stack() {
        this.head = new DLinkedList();
    }

    // Push an item onto the stack
    public void push(Object o) {
        head.append(o); // Add to the end of the list
    }

    // Pop an item from the stack
    public Object pop() {
        if (head.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Object topItem = head.get(head.size() - 1);
        head.remove(head.size() - 1); // Remove from the end of the list
        return topItem;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return head.isEmpty();
    }

    // Get the size of the stack
    public int size() {
        return head.size();
    }

    // Unit tests for the Stack class
    public static void main(String[] args) {
        Stack stack = new Stack();

        // 1. Test push and pop methods
        System.out.println("Test 1: Push and Pop");
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assert stack.pop().equals("C");
        assert stack.pop().equals("B");

        // 2. Test LIFO behavior
        System.out.println("Test 2: LIFO Behavior");
        stack.push("X");
        stack.push("Y");
        assert stack.pop().equals("Y");
        assert stack.pop().equals("X");

        // 3. Test isEmpty and size methods
        System.out.println("Test 3: isEmpty and Size");
        assert stack.isEmpty();
        stack.push("D");
        assert !stack.isEmpty();
        assert stack.size() == 1;

        // 4. Test exception on pop from an empty stack
        System.out.println("Test 4: Pop from Empty Stack");
        stack.pop(); // This will pop "D"
        try {
            stack.pop();
            assert false; // This should not be reached
        } catch (IllegalStateException e) {
            assert e.getMessage().equals("Stack is empty");
        }

        System.out.println("All tests passed!");
    }
}
