package seedu.addressbook.commands;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;


public class DeleteCommandTest {

    private AddressBook addressBook;
    private List<ReadOnlyPerson> emptyDisplayList;

    @Before
    public void setUp() throws Exception {
        addressBook = TestUtil.createAddressBook();
        emptyDisplayList = TestUtil.createList();
    }

    /**
     * Creates a new delete command.
     *
     * @param targetVisibleIndex of the person that we want to delete
     */
    private DeleteCommand createDeleteCommand(int targetVisibleIndex, AddressBook addressBook,
                                                                      List<ReadOnlyPerson> displayList) {

        DeleteCommand command = new DeleteCommand(targetVisibleIndex);
        command.setData(addressBook, displayList);

        return command;
    }
    
    /**
     * Asserts that the isMutating() function returns true for the DeleteCommand
     */
    @Test
    public void isMutating_deleteCommand_returnsTrue() {
        DeleteCommand command = createDeleteCommand(1, addressBook, emptyDisplayList);
        assertTrue(command.isMutating());
    }
}
