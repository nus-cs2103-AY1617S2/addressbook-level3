package seedu.addressbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.commands.CommandHistory;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Optional;

import static seedu.addressbook.common.Messages.*;

/**
 * Main Window of the GUI.
 */
public class MainWindow {

    private Logic logic;
    private Stoppable mainApp;

    public MainWindow(){
    }

    public void setLogic(Logic logic){
        this.logic = logic;
    }

    public void setMainApp(Stoppable mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private TextArea outputConsole;

    @FXML
    private TextField commandInput;

    @FXML
    public void handleKeyPressed(KeyEvent event){
	    if (event.getCode() == KeyCode.ENTER) {
	    	try {
	            String userCommandText = commandInput.getText();
	            CommandHistory.restore();
	            if(!CommandHistory.addCommandHistory(userCommandText)) {
	            	throw new ArrayIndexOutOfBoundsException();
	            }
	            CommandResult result = logic.execute(userCommandText);
	            if(isExitCommand(result)) {
	                exitApp();
	                return;
	            }
	            displayResult(result);
	            clearCommandInput();
	        } catch (Exception e) {
	            display(e.getMessage());
	            throw new RuntimeException(e);
	        }
	    }else if(event.getCode() == KeyCode.UP) {
	    	String userCommandText = CommandHistory.getPreviousCommand();
	    	if(userCommandText == null) {
	    		setCommandInput("");
	    	}else {
	    		setCommandInput(userCommandText);
	    	}
	    }else if(event.getCode() == KeyCode.DOWN) {
	    	String userCommandText = CommandHistory.getNextCommand();
	    	if(userCommandText == null) {
	    		setCommandInput("");
	    	}else {
	    		setCommandInput(userCommandText);
	    	}
	    }	
	}
    
    private void exitApp() throws Exception {
        mainApp.stop();
    }

    /** Returns true of the result given is the result of an exit command */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
    }
    
    /** Sets the command input box 'input' as the given parameter */
    public void setCommandInput(String result) {
        commandInput.setText(result);
    }

    /** Clears the command input box */
    private void clearCommandInput() {
        commandInput.setText("");
    }

    /** Clears the output display area */
    public void clearOutputConsole(){
        outputConsole.clear();
    }

    /** Displays the result of a command execution to the user. */
    public void displayResult(CommandResult result) {
        clearOutputConsole();
        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
        if(resultPersons.isPresent()) {
            display(resultPersons.get());
        }
        display(result.feedbackToUser);
    }

    public void displayWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        display(MESSAGE_WELCOME, version, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE, storageFileInfo);
    }

    /**
     * Displays the list of persons in the output display area, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void display(List<? extends ReadOnlyPerson> persons) {
        display(new Formatter().format(persons));
    }

    /**
     * Displays the given messages on the output display area, after formatting appropriately.
     */
    private void display(String... messages) {
        outputConsole.setText(outputConsole.getText() + new Formatter().format(messages));
    }

}
