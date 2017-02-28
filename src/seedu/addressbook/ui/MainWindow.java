package seedu.addressbook.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;

import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.commands.FindCommand;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.Main;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Optional;

import static seedu.addressbook.common.Messages.*;

/**
 * Main Window of the GUI.
 */
public class MainWindow {

    private Logic logic;
    private Stoppable mainApp;
    private SuggestionWindow suggestionWindow;
    private Stage suggestionStage;
    private Scene scene;

    public MainWindow(){
        initializeSuggestionWindow();
    }

    private void initializeSuggestionWindow() {
        try{
            suggestionStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            
            /* Note: When calling getResource(), use '/', instead of File.separator or '\\'
             * More info: http://docs.oracle.com/javase/8/docs/technotes/guides/lang/resources.html#res_name_context
             */
            loader.setLocation(Main.class.getResource("ui/suggestionwindow.fxml"));
    
            suggestionStage.setTitle("Suggestions");
            suggestionStage.setScene(new Scene(loader.load(), 500, 200));
            
            suggestionWindow = loader.getController();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setLogic(Logic logic){
        this.logic = logic;
    }

    public void setMainApp(Stoppable mainApp){
        this.mainApp = mainApp;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    @FXML
    private MenuBar menuBar;

    @FXML
    private TextArea outputConsole;

    @FXML
    private TextField commandInput;

    @FXML
    void onCommand(ActionEvent event) {
        try {
            String userCommandText = commandInput.getText();
            CommandResult result = logic.execute(userCommandText);
            if(isExitCommand(result)){
                exitApp();
                return;
            }
            displayResult(result);
            clearCommandInput();
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    @FXML
    void OnTextChanged(KeyEvent event) {
        try {
            String userCommandText = commandInput.getText();
            if(userCommandText.length() > 4 && userCommandText.startsWith(FindCommand.COMMAND_WORD)){
                CommandResult result = logic.execute(userCommandText);
                final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
                if(resultPersons.isPresent() && resultPersons.get().size() > 0) {
                    if(!suggestionStage.isShowing()){
                        suggestionStage.show();
                    }
                    suggestionWindow.setMenuList(new Formatter().format(resultPersons.get()));
                    suggestionWindow.display();
                }else{
                    suggestionStage.hide();
                }
                
            }
        } catch (Exception e) {
            display(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Handle hot key action (ALT + S) to launch directory chooser
     */
    @FXML
    void handleKeyInput(InputEvent event){
       if (event instanceof KeyEvent){
          final KeyEvent keyEvent = (KeyEvent) event;
          if (keyEvent.isAltDown() && keyEvent.getCode() == KeyCode.S){
              loadDirectoryChooser();
          }
       }
    }

    @FXML
    void handleSaveLocation(ActionEvent event){
        loadDirectoryChooser();
    }

    private void loadDirectoryChooser(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(scene.getWindow());
        if(selectedDirectory!=null){
            String newStorageFilePath = selectedDirectory.getAbsolutePath()+"\\addressbook.txt";
            logic.setStorageFilePath(newStorageFilePath);
            String storageFileInfo = String.format(MESSAGE_CHANGED_STORAGE_FILE_LOCATION, newStorageFilePath);
            display(storageFileInfo);
        }
    }

    private void exitApp() throws Exception {
        mainApp.stop();
    }

    /** Returns true of the result given is the result of an exit command */
    private boolean isExitCommand(CommandResult result) {
        return result.feedbackToUser.equals(ExitCommand.MESSAGE_EXIT_ACKNOWEDGEMENT);
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
