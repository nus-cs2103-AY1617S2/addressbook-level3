package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

/**
 * Keeps track of command history.
 * @author amro
 *
 */

public class CommandRecord {
    
    public enum Action {ADD, DELETE};
    
    private Action actionTaken;
    private Person person;
    
    CommandRecord(Action actionTaken, Person person) {
        this.actionTaken = actionTaken;
        this.person = person;
    }
    
    public Action getAction() {
        return this.actionTaken;
    }
    
    public Person getPerson() {
        return this.person;
    }
}
