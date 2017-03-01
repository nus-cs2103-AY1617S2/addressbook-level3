package seedu.addressbook.commands;

public class PrintCommand extends Command {

    public static final String COMMAND_WORD = "print";

    public static final String MESSAGE_USAGE =  COMMAND_WORD + ":\n" + "Print something.\n\t"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_PRINT_ACKNOWEDGEMENT = "Something printed ...";

    @Override
    public CommandResult execute() {
    	System.out.println("Hello World");
        return new CommandResult(MESSAGE_PRINT_ACKNOWEDGEMENT);
    }
}

