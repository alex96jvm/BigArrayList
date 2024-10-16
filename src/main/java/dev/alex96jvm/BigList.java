package dev.alex96jvm;
import java.util.Comparator;

public interface BigList<T> {
    void add(T element);

    void add(int index, T element);

    T get(int index);

    T remove(int index);

    void clear();

    void sort();

    void sort(Comparator<? super T> comparator);

    int size();
}
