package edu.epam.jwd.observer;

import edu.epam.jwd.event.Event;

public interface Observable<E extends Event> {
    void addListener(EventListener<E> listener) throws Exception;

    void removeListener(EventListener<E> listener) throws Exception;
}
