package edu.epam.jwd.observer;

import edu.epam.jwd.event.Event;

public interface EventListener<E extends Event> {
    void accept(E event);
}
