package seedu.addressbook.commands;

import static org.junit.Assert.assertFalse;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.util.TestUtil;

public class FindCommandTest {

    private AddressBook addressBook;

    private FindCommand createFindCommand(String[] keywords) throws DuplicatePersonException {
        addressBook = TestUtil.createAddressBook();
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindCommand command = new FindCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
    
    /**
     * Asserts that the isMutating() function returns false for the FindCommand
     * @throws DuplicatePersonException 
     */
    @Test
    public void isMutating_findCommand_returnsFalse() throws DuplicatePersonException {
        FindCommand command = createFindCommand(new String[]{});
        assertFalse(command.isMutating());
    }

}
