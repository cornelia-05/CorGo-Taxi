package com.cortaxi.patterns.behavioral.memento;

import java.util.Stack;

public class RideHistoryManager {

    private final Stack<RideState.RideMemento> history = new Stack<>();

    public void save(RideState state) {
        history.push(state.save());
    }

    public void undo(RideState state) {
        if (!history.isEmpty()) {
            state.restore(history.pop());
        }
    }
}