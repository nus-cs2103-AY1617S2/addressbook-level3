package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final String feedbackToUser;

    /** The list of persons that was produced by the command */
    private final List<? extends ReadOnlyPerson> relevantPersons;
    
    /** The list of commands that was produced by the command */
    private final ArrayList<String> relevantCommands;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.relevantPersons = null;
        this.relevantCommands = null;
    }

    public CommandResult(String feedbackToUser, ArrayList<String> relevantCommands) {
        this.feedbackToUser = feedbackToUser;
        this.relevantPersons = null;
        this.relevantCommands = relevantCommands;
    }
    
    public CommandResult(String feedbackToUser, List<? extends ReadOnlyPerson> relevantPersons) {
        this.feedbackToUser = feedbackToUser;
        this.relevantPersons = relevantPersons;
        this.relevantCommands = null;
    }

    /**
     * Returns list of commands relevant to the command command result, if any.
     */
    public Optional<ArrayList<String>> getRelevantCommands() {
        return Optional.ofNullable(relevantCommands);
    }
    
    /**
     * Returns list of persons relevant to the command command result, if any.
     */
    public Optional<List<? extends ReadOnlyPerson>> getRelevantPersons() {
        return Optional.ofNullable(relevantPersons);
    }

}
