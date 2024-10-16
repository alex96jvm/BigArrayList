package dev.alex96jvm;

import java.util.Comparator;

public class BigArrayList<T> implements BigList<T> {
    T[] array;
    int size;

    @SuppressWarnings("unchecked")
    public BigArrayList() {
        array = (T[]) new Object[1000];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public BigArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Недопустимая вместимость " + capacity);
        }
        array = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(T element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        array[index] = element;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(Comparator<? super T> comparator) {

    }

    @Override
    public int size() {
        return size;
    }

    private void increaseCapacity() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " за пределами допустимого диапазона");
        }
    }
}
