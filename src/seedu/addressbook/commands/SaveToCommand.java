package seedu.addressbook.commands;

public class SaveToCommand extends Command {

    public static final String COMMAND_WORD = "saveTo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Change the savefile directory.\n\t"
            + "Example: " + COMMAND_WORD + " C:\\";

    public static final String MESSAGE_SUCCESS = "The save directory has been changed to %1$s!";
    private final String newSaveLocation;

    public SaveToCommand(String newSaveLocation) {
        this.newSaveLocation = newSaveLocation;
    }

    @Override
    public CommandResult execute() {
        
        return new CommandResult(String.format(MESSAGE_SUCCESS, newSaveLocation));
    }

}
