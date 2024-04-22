import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree implements Tree {

    // Node class representing each element in the binary search tree
    private class Node {
        int value;          // Value held by the node
        Node left;          // Reference to the left child
        Node right;         // Reference to the right child

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Root of the binary search tree
    private Node root;

    // Convert a sorted array into a balanced binary search tree
    @Override
    public void ArrayToTree(int[] sorted_array) {
        this.root = constructTree(sorted_array, 0, sorted_array.length - 1);
    }

    // Recursively construct the tree using a divide-and-conquer approach
    private Node constructTree(int[] sorted_array, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node node = new Node(sorted_array[mid]);
        node.left = constructTree(sorted_array, start, mid - 1);
        node.right = constructTree(sorted_array, mid + 1, end);
        return node;
    }

    // Print the tree in in-order traversal
    public void printInOrder() {
        inOrderTraversal(this.root);
    }

    // Recursive helper for in-order traversal
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.value + " ");
            inOrderTraversal(node.right);
        }
    }

    // Convert the tree back to an array using in-order traversal
    public int[] toArray() {
        List<Integer> resultList = new ArrayList<>();
        inOrderToList(this.root, resultList);
        return resultList.stream().mapToInt(i -> i).toArray();
    }

    // Recursive helper to fill a list in in-order sequence
    private void inOrderToList(Node node, List<Integer> list) {
        if (node != null) {
            inOrderToList(node.left, list);
            list.add(node.value);
            inOrderToList(node.right, list);
        }
    }

    // Main method to test the functionality of the BinarySearchTree class
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Helper function to print arrays
        java.util.function.Consumer<int[]> printArray = arr -> {
            System.out.print("Original Array: ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        };

        // Test 1: Array with multiple elements
        int[] sortedArray1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        printArray.accept(sortedArray1);
        bst.ArrayToTree(sortedArray1);
        assert Arrays.equals(bst.toArray(), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}) : "Test 1 failed";

        // Test 2: Empty array
        int[] sortedArray2 = {};
        printArray.accept(sortedArray2);
        bst.ArrayToTree(sortedArray2);
        assert Arrays.equals(bst.toArray(), new int[]{}) : "Test 2 failed";

        // Test 3: Array with a single element
        int[] sortedArray3 = {5};
        printArray.accept(sortedArray3);
        bst.ArrayToTree(sortedArray3);
        assert Arrays.equals(bst.toArray(), new int[]{5}) : "Test 3 failed";

        // Test 4: Sorted array of even length
        int[] sortedArray4 = {8, 7, 6, 5, 4, 3, 2, 1};
        printArray.accept(sortedArray4);
        bst.ArrayToTree(sortedArray4);
        assert Arrays.equals(bst.toArray(), new int[]{1, 2, 3, 4, 5, 6, 7, 8}) : "Test 4 failed";

        // Test 5: Non-contiguous sorted array
        int[] sortedArray5 = {15, 12, 10, 7, 5, 3, 1};
        printArray.accept(sortedArray5);
        bst.ArrayToTree(sortedArray5);
        assert Arrays.equals(bst.toArray(), new int[]{1, 3, 5, 7, 10, 12, 15}) : "Test 5 failed";

        System.out.println("All tests passed!");
    }


}
