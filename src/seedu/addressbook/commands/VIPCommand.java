package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;


public class VIPCommand extends Command{

	public static final String COMMAND_WORD = "vip";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a VIP tag to the person identified by the index number used in the last person listing.. \n\t"
			+ "Parameters: INDEX\n\t"
			+ "Example: " + COMMAND_WORD + " 1";

	public static final String MESSAGE_SUCCESS = "VIP tag added to person: %1$s";
	public static final String MESSAGE_DUPLICATE_TAG = "This person is already a VIP";

	public VIPCommand(int targetVisibleIndex) {
		super(targetVisibleIndex);
	}

	public CommandResult execute() {
		try {
			final Person target = (Person) getTargetPerson();
			UniqueTagList tags = target.getTags();
			tags.add(new Tag("VIP"));
			target.setTags(tags);
			return new CommandResult(String.format(MESSAGE_SUCCESS, target));

		} catch (IndexOutOfBoundsException ie) {
			return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
		}  catch (DuplicateTagException ie) {
			return new CommandResult(MESSAGE_DUPLICATE_TAG);
		}catch (IllegalValueException pnfe) {
			return new CommandResult(pnfe.getMessage());
		}

	}

}