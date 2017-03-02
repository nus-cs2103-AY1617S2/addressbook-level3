package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.tag.*;


public class ListTagsCommand extends Command {
	public static final String COMMAND_WORD = "List_tags";
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + 
			"list all the tags." + 
			"\n\tExample: " + COMMAND_WORD;
	
	@Override
	public CommandResult execute() {
		List<Tag> allTags = addressBook.getAllTags().immutableListView();
		String tagListStringResult = getTagListStringResult(allTags);
		return new CommandResult(tagListStringResult);
	}
	
	private String getTagListStringResult(List<Tag> tagList) {
		StringBuilder sb = new StringBuilder();
		int index = 1;
		for (Tag tag : tagList) {
			sb.append(index);
			sb.append(": ");
			sb.append(tag.toString());
			sb.append('\n');
			index++;
		}
		return sb.toString();
	}
}
