package com.cortaxi.patterns.structural.composite;

import java.util.List;

public interface IFareComponent {
    String getName();
    double getPrice();
    void print(String indent);

    default void add(IFareComponent child) { throw new UnsupportedOperationException(); }
    default void remove(IFareComponent child) { throw new UnsupportedOperationException(); }
    default List<IFareComponent> getChildren() { throw new UnsupportedOperationException(); }
}
