package com.cortaxi.patterns.behavioral.command;

import com.cortaxi.patterns.behavioral.memento.RideState;

public class CancelRideCommand implements ICommand {

    private final RideState state;

    public CancelRideCommand(RideState state) {
        this.state = state;
    }

    @Override
    public void execute() {

        System.out.println("Cancelling ride...");

        // rollback la ultima stare
        state.undo();
    }
}