package dev.alex96jvm;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Реализация списка на основе динамического массива с возможностью расширения.
 * <p>
 * Данный класс представляет список с элементами обобщенного типа `T`. При достижении
 * максимальной вместимости массив автоматически увеличивается вдвое, что позволяет
 * добавлять новые элементы без ограничения по размеру.
 *
 * @param <T> Тип элементов, которые будут храниться в списке.
 */
public class BigArrayList<T> implements BigList<T>, Iterable<T> {

    /**
     * Внутренний массив для хранения элементов списка.
     */
    private T[] array;

    /**
     * Текущее количество элементов в списке.
     */
    private int size;

    /**
     * Конструктор по умолчанию, создающий список с начальной вместимостью 1000 элементов.
     */
    @SuppressWarnings("unchecked")
    public BigArrayList() {
        array = (T[]) new Object[1000];
    }

    /**
     * Конструктор, создающий список с заданной вместимостью.
     *
     * @param capacity начальная вместимость списка.
     * @throws IllegalArgumentException если вместимость отрицательна.
     */
    @SuppressWarnings("unchecked")
    public BigArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Недопустимая вместимость " + capacity);
        }
        array = (T[]) new Object[capacity];
    }

    /**
     * Добавляет элемент в конец списка.
     * <p>
     * Если массив достиг максимальной вместимости, его размер увеличивается вдвое.
     *
     * @param element элемент, который необходимо добавить.
     */
    @Override
    public void add(T element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
    }

    /**
     * Добавляет элемент на указанную позицию в списке.
     *
     * @param index индекс, на который нужно добавить элемент.
     * @param element элемент, который необходимо добавить.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
     */
    @Override
    public void add(int index, T element) {
        checkIndex(index);
        array[index] = element;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента, который необходимо вернуть.
     * @return элемент, находящийся на заданной позиции.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    /**
     * Удаляет элемент по указанному индексу и сдвигает все последующие элементы на одну позицию влево.
     *
     * @param index индекс элемента, который необходимо удалить.
     * @return удаленный элемент.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        array = (T[]) new Object[size];
        size = 0;
    }

    /**
     * Сортирует элементы списка по умолчанию (натуральный порядок).
     */
    @Override
    public void sort() {
        QuickSort.sort(array, size, null);
    }

    /**
     * Сортирует элементы списка с использованием предоставленного компаратора.
     *
     * @param comparator компаратор для определения порядка сортировки.
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        QuickSort.sort(array, size, comparator);
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return текущее количество элементов в списке.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return строковое представление списка в формате "[элемент1, элемент2, ...]".
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Возвращает итератор для перебора элементов списка.
     * <p>
     * Итератор позволяет поочередно получать доступ к элементам списка без необходимости
     * работать напрямую с индексами. Этот метод реализует интерфейс {@link Iterator}.
     *
     * @return итератор для перебора элементов списка.
     */
    @Override
    public Iterator<T> iterator() {
        return new BigArrayListIterator();
    }

    /**
     * Выполняет заданное действие для каждого элемента списка.
     * <p>
     * Этот метод применяет переданное действие к каждому элементу списка по порядку.
     *
     * @param action действие, которое нужно выполнить для каждого элемента.
     * @throws NullPointerException если переданное действие {@code null}.
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < size; i++) {
            action.accept(array[i]);
        }
    }

    /**
     * Увеличивает вместимость внутреннего массива вдвое.
     */
    private void increaseCapacity() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Проверяет, что индекс находится в пределах допустимого диапазона (0 <= index < size).
     *
     * @param index индекс, который нужно проверить.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами допустимого диапазона");
        }
    }

    /**
     * Внутренний класс для итерации по элементам {@code BigArrayList}.
     * <p>
     * Этот класс реализует интерфейс {@link Iterator}, что позволяет поочередно
     * перебирать элементы списка, используя стандартный цикл `for-each` или напрямую
     * через методы итератора.
     */
    private class BigArrayListIterator implements Iterator<T> {

        /**
         * Текущий индекс итератора.
         */
        private int cursor = 0;

        /**
         * Проверяет, есть ли следующий элемент в списке.
         *
         * @return {@code true}, если есть еще элементы для перебора, иначе {@code false}.
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * Возвращает следующий элемент в списке.
         *
         * @return следующий элемент в списке.
         * @throws NoSuchElementException если больше нет элементов для возврата.
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("Элементов больше нет");
            }
            return array[cursor++];
        }
    }
}
