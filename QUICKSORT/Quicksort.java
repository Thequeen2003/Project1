public class Quicksort {

    public void quicksort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        // Median-of-3 for pivot choice
        int pivot = medianOfThree(array, low, high);

        int i = low - 1;
        for (int j = low; j <= high; j++) {
            // Sort in decreasing order
            if (array[j] > pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private int medianOfThree(int[] array, int low, int high) {
        int mid = (low + high) / 2;
        if (array[low] < array[mid]) {
            swap(array, low, mid);
        }
        if (array[low] < array[high]) {
            swap(array, low, high);
        }
        if (array[mid] < array[high]) {
            swap(array, mid, high);
        }
        swap(array, mid, high);
        return array[high];
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
        // Test Case 1: Regular case with multiple distinct numbers
        int[] array1 = {10, 3, 8, 1, 9, 2, 4, 7, 6, 5};
        new Quicksort().quicksort(array1);
        printArray(array1);  // Expected: 10, 9, 8, 7, 6, 5, 4, 3, 2, 1

        // Test Case 2: An already sorted array (decreasing order)
        int[] array2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        new Quicksort().quicksort(array2);
        printArray(array2);  // Expected: 10, 9, 8, 7, 6, 5, 4, 3, 2, 1

        // Test Case 3: An array with some repeated numbers
        int[] array3 = {7, 3, 7, 1, 9, 2, 4, 7, 6, 5};
        new Quicksort().quicksort(array3);
        printArray(array3);  // Expected: 9, 7, 7, 7, 6, 5, 4, 3, 2, 1

        // Test Case 4: An array with a single number
        int[] array4 = {5};
        new Quicksort().quicksort(array4);
        printArray(array4);  // Expected: 5

        // Test Case 5: An empty array
        int[] array5 = {};
        new Quicksort().quicksort(array5);
        printArray(array5);  // Expected: (nothing)
    }

    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

