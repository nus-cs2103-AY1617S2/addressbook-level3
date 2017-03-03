package seedu.addressbook.command;

import static org.junit.Assert.*;

import org.junit.Test;

import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.logic.Logic;

public class FindCommandTest {

    private static final String COMMAND_FIND = "find";

    @Test
    public void findCommand_TestFindNameSmallCharacterResult_ReturnTrue() {
        String[] inputs = {"add John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney",
        "add Bob Doe p/98765432 e/bob@gmail.com a/322, Toa Payoh Ave 3, #02-27 t/friends t/owesMoney"};

        String expectedResult = "Bob Doe Phone: 98765432 Email: bob@gmail.com Address: 322, Toa Payoh Ave 3, #02-27 Tags: [owesMoney][friends]\n";

        String findCommandInput = "bob";
        try {
            Logic logic = new Logic();
            logic.execute("clear");
            for (String input : inputs) {
                logic.execute(input);
            }
            
            CommandResult result = logic.execute(COMMAND_FIND + " " + findCommandInput);
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
    
    @Test
    public void findCommand_TestFindPhoneResult_ReturnTrue() {
        String[] inputs = {"add John Doe p/98765431 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney",
        "add Bob Doe p/98765432 e/bob@gmail.com a/322, Toa Payoh Ave 3, #02-27 t/friends t/owesMoney",
        "add Bernard Yip p/1234567 e/bernardyipmk@gmail.com a/14, Hougang Ave 1, #13-10 t/friends t/bonded"};

        String expectedResult = "Bernard Yip Phone: 1234567 Email: bernardyipmk@gmail.com Address: 14, Hougang Ave 1, #13-10 Tags: [bonded][friends]\n";

        String findCommandInput = "1234567";
        try {
            Logic logic = new Logic();
            logic.execute("clear");
            for (String input : inputs) {
                logic.execute(input);
            }
            
            CommandResult result = logic.execute(COMMAND_FIND + " " + findCommandInput);
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
    
    @Test
    public void findCommand_TestFindEmailResult_ReturnTrue() {
        String[] inputs = {"add John Doe p/98765431 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney",
        "add Bob Doe p/98765432 e/bob@gmail.com a/322, Toa Payoh Ave 3, #02-27 t/friends t/giveMoney",
        "add Bernard Yip p/1234567 e/bernardyipmk@gmail.com a/14, Hougang Ave 1, #13-10 t/friends t/bonded"};

        String expectedResult = "John Doe Phone: 98765431 Email: johnd@gmail.com Address: 311, Clementi Ave 2, #02-25 Tags: [owesMoney][friends]\n";

        String findCommandInput = "johnd";
        try {
            Logic logic = new Logic();
            logic.execute("clear");
            for (String input : inputs) {
                logic.execute(input);
            }
            
            CommandResult result = logic.execute(COMMAND_FIND + " " + findCommandInput);
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
    
    @Test
    public void findCommand_TestFindAddressResult_ReturnTrue() {
        String[] inputs = {"add John Doe p/98765431 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney",
        "add Bob Doe p/98765432 e/bob@gmail.com a/322, Toa Payoh Ave 3, #02-27 t/friends t/giveMoney",
        "add Bernard Yip p/1234567 e/bernardyipmk@gmail.com a/14, Hougang Ave 1, #13-10 t/friends t/bonded"};

        String expectedResult = "Bernard Yip Phone: 1234567 Email: bernardyipmk@gmail.com Address: 14, Hougang Ave 1, #13-10 Tags: [bonded][friends]\n";

        String findCommandInput = "Hougang";
        try {
            Logic logic = new Logic();
            logic.execute("clear");
            for (String input : inputs) {
                logic.execute(input);
            }
            
            CommandResult result = logic.execute(COMMAND_FIND + " " + findCommandInput);
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

    @Test
    public void findCommand_TestFindTagResult_ReturnTrue() {
        String[] inputs = {"add John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney",
        "add Bob Doe p/98765432 e/bob@gmail.com a/322, Toa Payoh Ave 3, #02-27 t/friends t/saveMoney",
        "add Bernard Yip p/1234567 e/bernardyipmk@gmail.com a/14, Clementi Ave 1, #13-10 t/friends t/bonded"};

        String expectedResult = "Bob Doe Phone: 98765432 Email: bob@gmail.com Address: 322, Toa Payoh Ave 3, #02-27 Tags: [saveMoney][friends]\n";

        String findCommandInput = "saveMoney";
        try {
            Logic logic = new Logic();
            logic.execute("clear");
            for (String input : inputs) {
                logic.execute(input);
            }
            
            CommandResult result = logic.execute(COMMAND_FIND + " " + findCommandInput);
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

    @Test
    public void findCommand_TestFindMultipleKeywordResult_ReturnTrue() {
        String[] inputs = {"add John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney",
        "add Bob Doe p/98765431 e/bob@gmail.com a/322, Toa Payoh Ave 3, #02-27 t/friends t/giveMoney",
        "add Bernard Yip p/1234567 e/bernardyipmk@gmail.com a/14, Hougang Ave 1, #13-10 t/friends t/bonded",
        "add James p/0987654 e/jamesisnoob@hotmail.com a/14, Bishan Bridge, #20-01 t/isMe t/onceVegetarian",
        "add Yesha p/2345689 e/yeshamarina@gmail.com a/1, South Buona Vista, #06-10 t/friends t/vegetarian",
        "add dummy1 p/1234566 e/dummy1@hotmail.com a/111, Newton MRT, #01-13 t/isDummy1 t/robot",
        "add dummy2 p/2345677 e/dummy2@hotmail.com a/222, Sin Ming Ave 1, #50-18 t/isDummy2 t/stupid",
        "add dummy3 p/4567899 e/dummy3@hotmail.com a/333, East Coast Park, #09-20 t/dummy3 t/dumb",
        "add dummy4 p/0987656 e/dummy4@hotmail.com a/444, Orchard Road, #10-02 t/dummy4 t/creepy"};

        String expectedResult = "John Doe Phone: 98765432 Email: johnd@gmail.com Address: 311, Clementi Ave 2, #02-25 Tags: [owesMoney][friends]\n"
                + "Bernard Yip Phone: 1234567 Email: bernardyipmk@gmail.com Address: 14, Hougang Ave 1, #13-10 Tags: [bonded][friends]\n"
                + "James Phone: 0987654 Email: jamesisnoob@hotmail.com Address: 14, Bishan Bridge, #20-01 Tags: [onceVegetarian][isMe]\n"
                + "Yesha Phone: 2345689 Email: yeshamarina@gmail.com Address: 1, South Buona Vista, #06-10 Tags: [vegetarian][friends]\n"
                + "dummy2 Phone: 2345677 Email: dummy2@hotmail.com Address: 222, Sin Ming Ave 1, #50-18 Tags: [isDummy2][stupid]\n"
                + "dummy4 Phone: 0987656 Email: dummy4@hotmail.com Address: 444, Orchard Road, #10-02 Tags: [dummy4][creepy]\n";

        String findCommandInput = "98765432 bonded Bishan 444 dummy2 vegetarian";
        try {
            Logic logic = new Logic();
            logic.execute("clear");
            for (String input : inputs) {
                logic.execute(input);
            }
            
            CommandResult result = logic.execute(COMMAND_FIND + " " + findCommandInput);
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
