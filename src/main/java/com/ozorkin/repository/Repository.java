package com.ozorkin.repository;

public interface Repository <T> {
    void save(T t);

    void delete(final String id);

    void insert( int index, final T t);

    T[] getAll();

    T getById(final String id);


}

