package seedu.addressbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import seedu.addressbook.commands.AddCommand;
import seedu.addressbook.logic.Logic;

public class AddWindow extends MainWindow {
	
	private boolean okClicked = false;
	private String parsedString;
	private Logic logic;
	
	public AddWindow() {
	}
	
	public void setLogic(Logic logic) {
		this.logic = logic;
	}
	
	@FXML
	private TextField nameField;
	
	@FXML
	private TextField phoneField;
	
	@FXML
	private TextField emailField;
	
	@FXML
	private TextField addressField;
	
	@FXML
	private TextField tagsField;
	
	private void parseString() {
		String name = nameField.getText();
		String phone = "p/" + phoneField.getText();
		String email = "e/" + emailField.getText();
		String address = "/a" + addressField.getText();
		String tags = "t/" + tagsField.getText(); // Need to edit to accommodate more tags.
		parsedString = name + " " + phone + " " + email + " " + address + " " + tags + " ";
	}
	
	private void onOkButton() {
		parseString();
		commandButton(AddCommand.COMMAND_WORD);
		setOkClicked();
	}
	
	/** 
	 * Returns true if the Ok button is clicked, false if otherwise 
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setOkClicked() {
		this.okClicked = true;
	}
	
}
