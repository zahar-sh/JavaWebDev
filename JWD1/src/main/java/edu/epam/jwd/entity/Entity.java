package edu.epam.jwd.entity;

public interface Entity<Id> {
    Id getId();

    void setId(Id id);
}
