package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.common.Utils;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.group.Group;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AddGroupCommandTest {
    private String GROUP_NAME = "Bowling";
    @Test
    public void groupIsAdded(){
        AddressBook book = new AddressBook();
        AddGroupCommand addGroupCommand = new AddGroupCommand(GROUP_NAME,book);
        assertIsAdded(book.getLIST_OF_GROUPS());
    }

    public void assertIsAdded(ArrayList<Group> listOfGroups){
        assertTrue(Utils.groupIsAdded(listOfGroups,GROUP_NAME));
    }

}