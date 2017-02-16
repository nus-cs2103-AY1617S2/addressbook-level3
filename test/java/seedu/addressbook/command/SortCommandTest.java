package seedu.addressbook.command;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.logic.Logic;

import org.junit.Test;

public class SortCommandTest {

    private static final String COMMAND_SORT = "sort";

    @Test
    public void sortCommand_TestSortResultExist_ReturnTrue() {
        try {
            Logic logic = new Logic();
            CommandResult result = logic.execute(COMMAND_SORT);
            assertNotNull(result);
        } catch (Exception e) {
            fail("Exception");
        }
    }

    @Test
    public void sortCommand_TestSortResults_ReturnTrue() {
        String[] inputs = {"add John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney",
        "add Bob Doe p/98765432 e/bob@gmail.com a/311, Clementi Ave 3, #02-27 t/friends t/owesMoney"};

        String expectedResult = "Bob Doe Phone: 98765432 Email: bob@gmail.com Address: 311, Clementi Ave 3, #02-27 Tags: [owesMoney][friends]\n"
                + "John Doe Phone: 98765432 Email: johnd@gmail.com Address: 311, Clementi Ave 2, #02-25 Tags: [owesMoney][friends]\n";

        try {
            Logic logic = new Logic();
            logic.execute("clear");
            for (String input : inputs) {
                logic.execute(input);
            }
            CommandResult result = logic.execute(COMMAND_SORT);
            StringBuilder resultString = new StringBuilder();
            for (ReadOnlyPerson person : result.getRelevantPersons().get()) {
                resultString.append(person.toString());
                resultString.append("\n");
            }
            assertTrue(resultString.toString().equals(expectedResult));
        } catch (Exception e) {
            fail("Exception");
        }
    }
}
