package seedu.addressbook.commands;

import seedu.addressbook.data.AddressBook;

/**
   Sort Persons according to alphabetical order in the addressbook
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "Sort";


    public SortCommand(AddressBook addressBook){
        addressBook.sortPerson();
    }
}
