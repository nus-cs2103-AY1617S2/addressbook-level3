package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Represents a read only command.
 */

public class ReadOnlyCommand {
    private final String commandWord;
    private final List<? extends ReadOnlyPerson> relevantPersons;
    
    public ReadOnlyCommand (String commandWord, List<? extends ReadOnlyPerson> relevantPersons) {
        this.commandWord = commandWord;
        this.relevantPersons = relevantPersons;
    }
    
    public String getCommandWord() {
        return commandWord;
    }

    public List<? extends ReadOnlyPerson> getRelevantPersons() {
        return relevantPersons;
    }
}
