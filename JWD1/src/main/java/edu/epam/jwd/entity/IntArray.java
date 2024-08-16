package edu.epam.jwd.entity;

import edu.epam.jwd.event.ArrayChangeEvent;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.observer.EventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntArray implements ObservableEntity<Integer, ArrayChangeEvent> {

    private Integer id;
    private final int[] array;
    private final List<EventListener<ArrayChangeEvent>> listeners;

    public IntArray(int[] array) throws CustomException {
        if (array == null) {
            throw new CustomException("array should be not null");
        }
        this.array = array.clone();
        this.listeners = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int length() {
        return array.length;
    }

    private void checkRange(int index, int length) throws CustomException {
        if (index < 0 || index >= length) {
            Throwable cause = new IndexOutOfBoundsException(String.format("Index: %d, length: %d", index, length));
            throw new CustomException(cause);
        }
    }

    public int get(int index) throws CustomException {
        checkRange(index, length());
        return array[index];
    }

    public void set(int number, int index) throws CustomException {
        checkRange(index, length());
        array[index] = number;
        onElementChanged(number, index);
    }

    private void onElementChanged(int number, int index) throws CustomException {
        if (!listeners.isEmpty()) {
            ArrayChangeEvent event = new ArrayChangeEvent(this, number, index);
            listeners.forEach(listener -> listener.accept(event));
        }
    }

    public int[] asJavaArray() {
        return array.clone();
    }

    @Override
    public void addListener(EventListener<ArrayChangeEvent> listener) throws CustomException {
        if (listener == null) {
            throw new CustomException("listener");
        }
        listeners.add(listener);
    }

    @Override
    public void removeListener(EventListener<ArrayChangeEvent> listener) throws CustomException {
        if (listener == null) {
            throw new CustomException("listener");
        }
        listeners.remove(listener);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArray that = (IntArray) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("NumberArray{")
                .append("array=").append(Arrays.toString(array))
                .append('}')
                .toString();
    }
}
