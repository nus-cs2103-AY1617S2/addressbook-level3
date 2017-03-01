package seedu.addressbook.commands;

import java.util.Iterator;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;

/**
 * Delete all person with certain tag specified by user.
 */
public class DeleteHasTagCommand extends Command{

       public static final String COMMAND_WORD = "deleteHasTag";
       
       public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
               + "Deletes all person with a tag specified by the user.\n\t"
               + "Parameters: TAG\n\t"
               + "Example: " + COMMAND_WORD + " juniors";
       
       public static final String MESSAGE_DELETE_HAS_TAG_SUCCESS = "Deleted %1$d persons.";
       
       private int deletedNumber;
       private final Tag tag;
       
       public DeleteHasTagCommand(String tagName) throws IllegalValueException {
           this.tag = new Tag(tagName);
           deletedNumber = 0;
       }
       
       @Override
       public CommandResult execute(ReadOnlyCommand previousCommand) {
           try{
               UniquePersonList allPerson = addressBook.getAllPersons();
               Iterator<Person> i = allPerson.iterator();
               while(i.hasNext()) {
                   Person person = i.next();
                   if(person.getTags().contains(this.tag)) {
                       addressBook.removePerson(person);
                       deletedNumber++;
                   }
               }
               return new CommandResult(String.format(MESSAGE_DELETE_HAS_TAG_SUCCESS, deletedNumber));
           } catch (PersonNotFoundException pnfe) {
               return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
           }
       }

	@Override
	public String getCommandWord() {
		return COMMAND_WORD;
	}

}
