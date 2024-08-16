package edu.epam.jwd.event;

import edu.epam.jwd.exception.CustomException;

public class Event {
    private final Object source;

    public Event(Object source) throws CustomException {
        if (source == null) {
            throw new CustomException("source");
        }
        this.source = source;
    }

    public Object getSource() {
        return source;
    }
}
