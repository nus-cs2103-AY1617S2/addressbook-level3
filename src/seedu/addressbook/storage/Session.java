package seedu.addressbook.storage;

import java.util.ArrayList;

import seedu.addressbook.commands.Command;

public class Session {
	private String sessionKey;
	private ArrayList<Command> history;
	
	public Session() {
		this.history = new ArrayList<Command>();
		this.sessionKey = "someKey";
	}
	
	public void updateHistorySession(Command command) {
		history.add(command);
	}
	
	public ArrayList<Command> getHistory() {
		return this.history;
	}
	

}
