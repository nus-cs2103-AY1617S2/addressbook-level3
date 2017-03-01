package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.Person;


/**
 * Favorites a person identified using it's last displayed index from the address book.
 */
public class FavoriteCommand extends Command {

    public static final String COMMAND_WORD = "favorite";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "favorite the person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX\n\t"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAVORITE_PERSON_SUCCESS = "Favorited Person: %1$s";


    public FavoriteCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }
    
    public ReadOnlyPerson replicate(ReadOnlyPerson source) {
        ReadOnlyPerson newPerson = new Person(source.getName(),
                                              source.getPhone(),
                                              source.getEmail(),
                                              source.getAddress(),
                                              source.getTags());
        return newPerson;

    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson toFavorite = getTargetPerson();
            ReadOnlyPerson newFavorite = replicate(toFavorite);
            addressBook.addFavorite(newFavorite);
            return new CommandResult(String.format(MESSAGE_FAVORITE_PERSON_SUCCESS, toFavorite));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

}
