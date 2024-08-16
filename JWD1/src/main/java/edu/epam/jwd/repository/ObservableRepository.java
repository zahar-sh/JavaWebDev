package edu.epam.jwd.repository;

import edu.epam.jwd.entity.Entity;
import edu.epam.jwd.event.RepositoryChangeEvent;
import edu.epam.jwd.observer.Observable;

public interface ObservableRepository<Id, E extends Entity<Id>> extends Repository<Id, E>, Observable<RepositoryChangeEvent> {
}
