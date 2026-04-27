package com.cortaxi.patterns.behavioral.command;

public class RideInvoker {

    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}