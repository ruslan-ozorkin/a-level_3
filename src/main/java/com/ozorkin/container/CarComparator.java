package com.ozorkin.container;

import com.ozorkin.model.Car;

import java.util.Comparator;

 class CarComparator <E> implements Comparator<Car>  {


     @Override
     public int compare(Car a, Car b) {
         return Integer.compare(a.getCount(), b.getCount());
     }
 }

