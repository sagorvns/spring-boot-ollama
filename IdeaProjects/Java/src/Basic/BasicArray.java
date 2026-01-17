package Basic;

import java.util.Arrays;

public class BasicArray {
    public static void main(String[] args) {
        System.out.println("Basic Array!!!");

        int capacity = 10, currentSize = 4;
        int[] arr = new int[capacity];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 4;
        arr[3] = 5;

        int elementToInsert = 3;
        int positionToInsert = 2;

        currentSize = insertArray(arr, elementToInsert, currentSize, capacity, positionToInsert);

        int[] arr1 = Arrays.copyOf(arr, currentSize); // Copy updated array
        int elementToDelete = 2;
        currentSize = deleteArray(arr1, elementToDelete, currentSize);

        System.out.print("After Deletion: ");
        Arrays.stream(Arrays.copyOf(arr1, currentSize)).forEach(l -> System.out.print(l + " "));
        bubbleSort(arr1, currentSize);
        binarySearch(arr1, 5);

    }

    private static int insertArray(int[] arr, int element, int currentSize, int capacity, int position) {
        if (currentSize < capacity && position >= 0 && position <= currentSize) {
            for (int i = currentSize; i > position; i--) {
                arr[i] = arr[i - 1];
            }
            arr[position] = element;
            currentSize++;
            System.out.println("After Insertion: " + Arrays.toString(Arrays.copyOf(arr, currentSize)));
        } else {
            System.out.println("Cannot insert. Invalid position or capacity full.");
        }
        return currentSize;
    }

    private static int deleteArray(int[] arr, int element, int currentSize) {
        int position = findElement(arr, element, currentSize);
        if (position == -1) {
            System.out.println("No element found to delete!");
            return currentSize;
        }
        for (int i = position; i < currentSize - 1; i++) {
            arr[i] = arr[i + 1];
        }
        currentSize--;
        return currentSize;
    }

    private static int findElement(int[] arr, int element, int currentSize) {
        for (int i = 0; i < currentSize; i++) {
            if (arr[i] == element) {
                return i; // ✅ Return index, not value
            }
        }
        return -1;
    }

    private static void bubbleSort(int[] arr, int currentSize) {
        for (int i = 0; i < currentSize - 1; i++) {
            for (int j = 0; j < currentSize - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid overflow
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // Not found
    }


}
