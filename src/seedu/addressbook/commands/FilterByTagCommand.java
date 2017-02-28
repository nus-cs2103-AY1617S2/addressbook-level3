package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

public class FilterByTagCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose tags contain "
            + "the specified tag(case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD\n\t"
            + "Example: " + COMMAND_WORD + " teammates";

    private final String keyword;

    public FilterByTagCommand(String keyword) {
        this.keyword = keyword;
    }
    
    /**
     * Returns copy of keyword in this command.
     */
    public String getKeyword() {
        return keyword;
    }
    
    @Override
    public CommandResult execute() {
        try {
            final List<ReadOnlyPerson> personsFound = getPersonsWithSpecifiedTag(keyword);
            return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
        } catch (IllegalValueException ive) {
            return new CommandResult(keyword + " invalid. " + Tag.MESSAGE_TAG_CONSTRAINTS);
        }  
    }
    
    /**
     * Retrieve all persons in the address book whose tags contain specified keyword.
     *
     * @param keyword for searching
     * @return list of persons found
     * @throws IllegalValueException 
     */
    private List<ReadOnlyPerson> getPersonsWithSpecifiedTag(String keyword) throws IllegalValueException {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        Tag filter = new Tag(keyword);
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            if (person.getTags().contains(filter)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
