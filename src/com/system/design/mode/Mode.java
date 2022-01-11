package com.system.design.mode;

import com.system.design.OutputPrinter;
import com.system.design.commands.CommandExecutor;
import com.system.design.commands.CommandExecutorFactory;
import com.system.design.exception.InvalidCommandException;
import com.system.design.model.Command;

import java.io.IOException;

public abstract class Mode {
    protected OutputPrinter outputPrinter;
    private CommandExecutorFactory commandExecutorFactory;

    public Mode(
            final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    /**
     * Helper method to process a command. It basically uses {@link CommandExecutor} to run the given
     * command.
     *
     * @param command Command to be processed.
     */
    protected void processCommand(final Command command) {
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if (commandExecutor.validate(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Abstract method to process the mode. Each mode will process in its own way.
     *
     * @throws IOException
     */
    public abstract void process() throws IOException;
}
