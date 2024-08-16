package edu.epam.jwd.entity;

import edu.epam.jwd.event.Event;
import edu.epam.jwd.observer.Observable;

public interface ObservableEntity<Id, E extends Event> extends Entity<Id>, Observable<E> {
}
