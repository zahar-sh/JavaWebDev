package edu.epam.jwd.service;

public interface ServiceFactory<S> {
    S getService();
}
