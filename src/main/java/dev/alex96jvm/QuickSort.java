package dev.alex96jvm;

import java.util.Comparator;

class QuickSort <T> {
    private Comparator<? super T> comparator;

    public QuickSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public QuickSort() {
    }

    public void sort(T[] array, int size) {
        quickSort(array, 0, size-1);
    }

    private void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    @SuppressWarnings("unchecked")
    private int compare(T o1, T o2) {
        if (comparator != null) {
            return comparator.compare(o1, o2);
        } else {
            return ((Comparable<? super T>) o1).compareTo(o2);
        }
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
