package seedu.addressbook.commands;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.group.Group;

public class AddGroupCommand extends Command {

    public AddGroupCommand(String name, AddressBook addressBook){
        addressBook.addGroup(new Group(name));
    }
}
