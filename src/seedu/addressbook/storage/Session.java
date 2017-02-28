package seedu.addressbook.storage;

import java.util.ArrayList;
import java.util.Stack;
import seedu.addressbook.common.Pair;
import seedu.addressbook.commands.Command;
import seedu.addressbook.data.person.Person;

public class Session {
	private String sessionKey;
	private ArrayList<Command> history;
	private Stack<Pair<String, Person>> redoStack;
	private Stack<Pair<String, Person>> undoStack;
	
	public Session() {
		this.history = new ArrayList<Command>();
		this.sessionKey = "someKey";
		this.redoStack = new Stack<Pair<String, Person>>();
		this.undoStack = new Stack<Pair<String, Person>>();
	}
	
	public Pair<String, Person> popRedoStack() {
		if (!redoStack.empty()) {
			return redoStack.pop();
		} else {
			return null;
		}
	}
	
	public void pushRedoStack(Pair<String, Person> toPush) {
		redoStack.push(toPush);
	}
	
	public Pair<String, Person> popUndoStack() {
		if (!undoStack.empty()) {
			return undoStack.pop();
		} else {
			return null;
		}
	}
	
	public void pushUndoStack(Pair<String, Person> toPush) {
		undoStack.push(toPush);
	}
	
	public void clearRedoStack() {
		this.redoStack = new Stack<Pair<String, Person>>();
	}
	
	public Stack<Pair<String, Person>> getUndoStack() {
		return this.undoStack;
	}
	
	public Stack<Pair<String, Person>> getRedoStack() {
		return this.redoStack;
	}
	
	public void updateHistorySession(Command command) {
		history.add(command);
	}
	
	public ArrayList<Command> getHistory() {
		return this.history;
	}
	

}
