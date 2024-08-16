package edu.epam.jwd.event;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;

public class ArrayChangeEvent extends Event {
    private final int number;
    private final int index;

    public ArrayChangeEvent(IntArray source, int number, int index) throws CustomException {
        super(source);
        this.number = number;
        this.index = index;
    }

    public int getNumber() {
        return number;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public IntArray getSource() {
        return (IntArray) super.getSource();
    }
}
