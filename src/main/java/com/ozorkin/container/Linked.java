package com.ozorkin.container;

import com.ozorkin.model.Car;

public interface Linked <E> {
    void addLastElement (E element);
    void addFirstElement (E element);
    int indexOfElement (E element);
    void insertElement (int index, E element);
    void deleteElement (int index);
    int size();

}
