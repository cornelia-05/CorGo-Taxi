package com.cortaxi.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class FareBundle implements IFareComponent {
    private final String name;
    private final List<IFareComponent> parts = new ArrayList<>();

    public FareBundle(String name) { this.name = name; }

    @Override public String getName() { return name; }

    @Override
    public double getPrice() {
        return parts.stream().mapToDouble(IFareComponent::getPrice).sum();
    }

    @Override
    public void add(IFareComponent child) { parts.add(child); }

    @Override
    public void remove(IFareComponent child) { parts.remove(child); }

    @Override
    public List<IFareComponent> getChildren() { return List.copyOf(parts); }

    @Override
    public void print(String indent) {
        System.out.printf("%s[%s] total: %.2f%n", indent, name, getPrice());
        parts.forEach(p -> p.print(indent + "  "));
    }
}
