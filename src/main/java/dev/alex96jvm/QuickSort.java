package dev.alex96jvm;

import java.util.Comparator;

/**
 * Служебный класс для реализации алгоритма быстрой сортировки (QuickSort).
 * <p>
 * Этот класс предназначен для внутреннего использования и не является частью публичного API.
 * Он реализует алгоритм быстрой сортировки для массивов элементов обобщенного типа {@code T}.
 * Сортировка может осуществляться как по натуральному порядку элементов (если они реализуют
 * интерфейс {@code Comparable}), так и с использованием пользовательского компаратора.
 */
class QuickSort {

    /**
     * Статический метод сортировки, использующий алгоритм быстрой сортировки.
     * Принимает массив и опциональный компаратор для сортировки.
     *
     * @param array Массив, который необходимо отсортировать.
     * @param size Количество элементов в массиве, которые нужно сортировать.
     * @param comparator Компаратор, задающий порядок сортировки (может быть null для использования натурального порядка).
     */
    public static <T> void sort(T[] array, int size, Comparator<? super T> comparator) {
        quickSort(array, 0, size - 1, comparator);
    }

    private static <T> void quickSort(T[] array, int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quickSort(array, low, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<? super T> comparator) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(array[j], pivot, comparator) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    @SuppressWarnings("unchecked")
    private static <T> int compare(T o1, T o2, Comparator<? super T> comparator) {
        if (comparator != null) {
            return comparator.compare(o1, o2);
        } else {
            return ((Comparable<? super T>) o1).compareTo(o2);
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
