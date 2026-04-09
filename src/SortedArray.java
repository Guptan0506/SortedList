// src/SortedArray.java
public class SortedArray {
    private String[] array;
    private int size;

    public SortedArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        array = new String[capacity];
        size = 0;
    }

    public void add(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("String cannot be empty.");
        }
        if (size == array.length) {
            throw new IllegalStateException("Array is full");
        }

        value = value.trim();
        int pos = binarySearch(value);

        // pos >= 0 means found. Insert after duplicates to keep order stable.
        if (pos >= 0) {
            while (pos < size && array[pos].compareTo(value) == 0) {
                pos++;
            }
        } else {
            pos = -pos - 1; // insertion point
        }

        for (int i = size; i > pos; i--) {
            array[i] = array[i - 1];
        }
        array[pos] = value;
        size++;
    }

    /**
     * Returns index if found.
     * Returns -(insertionPoint + 1) if not found.
     */
    public int search(String target) {
        if (target == null) {
            throw new IllegalArgumentException("Search string cannot be null.");
        }
        return binarySearch(target.trim());
    }

    // Manual binary search (assignment requirement)
    private int binarySearch(String target) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = array[mid].compareTo(target);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -(low + 1);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
