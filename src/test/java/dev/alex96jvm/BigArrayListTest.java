package dev.alex96jvm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BigArrayListTest {
    private BigArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new BigArrayList<>();
    }

    @Test
    void testAddElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testAddStringElements() {
        BigArrayList<String> stringList = new BigArrayList<>();
        stringList.add("Aston Martin");
        stringList.add("BMW");
        stringList.add("Citroen");

        assertEquals(3, stringList.size());
        assertEquals("Aston Martin", stringList.get(0));
        assertEquals("BMW", stringList.get(1));
        assertEquals("Citroen", stringList.get(2));
    }

    @Test
    void testAddElementAtIndex() {
        list.add(1);
        list.add(2);
        list.add(1, 300);
        assertEquals(1, list.get(0));
        assertEquals(300, list.get(1));
    }

    @Test
    void testGetElement() {
        list.add(121);
        list.add(212);
        assertEquals(121, list.get(0));
        assertEquals(212, list.get(1));
    }

    @Test
    void testRemoveElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testClearList() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testSortNaturalOrder() {
        list.add(315);
        list.add(12100);
        list.add(2);
        list.sort();
        assertEquals(2, list.get(0));
        assertEquals(315, list.get(1));
        assertEquals(12100, list.get(2));
    }

    @Test
    void testSortStringsNaturalOrder() {
        BigArrayList<String> stringList = new BigArrayList<>();
        stringList.add("Citroen");
        stringList.add("Aston Martin");
        stringList.add("BMW");
        stringList.add("Audi");

        stringList.sort();
        assertEquals("Aston Martin", stringList.get(0));
        assertEquals("Audi", stringList.get(1));
        assertEquals("BMW", stringList.get(2));
        assertEquals("Citroen", stringList.get(3));
    }

    @Test
    void testSortWithComparator() {
        list.add(3000);
        list.add(1000);
        list.add(2000);
        list.sort((a, b) -> b - a);
        assertEquals(3000, list.get(0));
        assertEquals(2000, list.get(1));
        assertEquals(1000, list.get(2));
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    void testToString() {
        list.add(18);
        list.add(2);
        list.add(333);
        assertEquals("[18, 2, 333]", list.toString());
    }

    @Test
    void testIncreaseCapacity() {
        BigArrayList<Integer> bigList = new BigArrayList<>(1);
        bigList.add(1);
        bigList.add(2);
        assertEquals(2, bigList.size());
    }

    @Test
    void testCheckIndexOutOfBounds() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
    }

    @Test
    void testConstructorWithInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new BigArrayList<>(-1));
    }

    @Test
    void testIterator() {
        BigArrayList<String> stringList = new BigArrayList<>();
        stringList.add("Aston Martin");
        stringList.add("BMW");
        stringList.add("Citroen");

        Iterator<String> iterator = stringList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Aston Martin", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("BMW", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Citroen", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testForEach() {
        BigArrayList<String> stringList = new BigArrayList<>();
        stringList.add("Aston Martin");
        stringList.add("Audi");
        stringList.add("BMW");
        stringList.add("Citroen");

        List<String> result = new ArrayList<>();
        stringList.forEach(result::add);
        assertEquals(4, result.size());
        assertEquals("Aston Martin", result.get(0));
        assertEquals("Audi", result.get(1));
        assertEquals("BMW", result.get(2));
        assertEquals("Citroen", result.get(3));
    }
}
