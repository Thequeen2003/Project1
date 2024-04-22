public class DLinkedList implements MyList {
    private static class Node {
        Object data;
        Node next;
        Node prev;

        Node(Object data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int count;

    @Override
    public void insert(int index, Object item) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == count) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node current = getNodeAtIndex(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        count++;
    }

    @Override
    public void append(Object item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        count++;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void replace(int index, Object item) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        Node current = getNodeAtIndex(index);
        current.data = item;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        if (count == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == count - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node current = getNodeAtIndex(index);
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        count--;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeAtIndex(index).data;
    }

    private Node getNodeAtIndex(int index) {
        Node current;
        if (index < count / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = count - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    public static void main(String[] args) {
        DLinkedList list = new DLinkedList();

        // Test append and size methods
        list.append("A");
        list.append("B");
        list.append("C");
        System.out.println(list.size()); // Expected: 3

        // Test get method
        System.out.println(list.get(1)); // Expected: B

        // Test insert method
        list.insert(1, "X");
        System.out.println(list.get(1)); // Expected: X

        // Test replace method
        list.replace(2, "Y");
        System.out.println(list.get(2)); // Expected: Y

        // Test remove method
        list.remove(2);
        System.out.println(list.get(2)); // Expected: B

        // Test clear and isEmpty methods
        list.clear();
        System.out.println(list.isEmpty()); // Expected: true
    }
}


