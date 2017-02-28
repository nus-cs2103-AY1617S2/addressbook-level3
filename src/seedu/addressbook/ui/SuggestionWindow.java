package seedu.addressbook.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.commands.FindCommand;
import seedu.addressbook.logic.Logic;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Optional;

import static seedu.addressbook.common.Messages.*;

/**
 * Main Window of the GUI.
 */
public class SuggestionWindow {

    private String suggestions;
    private Stoppable mainApp;

    public SuggestionWindow(){
    }

    public void setMenuList(String suggestions){
        this.suggestions = suggestions;
    }

    @FXML
    private TextArea suggestionList;

    public void display(){
        suggestionList.setText(suggestions);
    }

}
