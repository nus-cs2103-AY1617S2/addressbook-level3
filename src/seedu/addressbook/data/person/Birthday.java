package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Birthday {

	public static final String EXAMPLE = "DD-MM-YYY";
	public static final String MESSAGE_BIRTHDAY_CONSTRAINTS = "Date should be in format DD-MM-YYYY";
	
	public String value;
	private boolean isPrivate;
	
	/**
	 * * Validates given birthday.
	 * @throws ParseException 
	 * 
	 * @throws IllegalVauleException if given birthday is invalid.
	 */
	public Birthday(String birthday,boolean isPrivate) throws IllegalValueException, ParseException{
		//this.isPrivate=isPrivate;
		birthday = birthday.trim();
		if(!isValidBirthday(birthday)){
			throw new IllegalValueException(MESSAGE_BIRTHDAY_CONSTRAINTS);
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(birthday);
		this.value= formatter.format(date);
	}

	public static boolean isValidBirthday(String test) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		format.setLenient(false);
		try{
			format.parse(test);
		return true;	
	}
		catch (ParseException e){
			return false;
		}
}
	public void setValue(String value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return value;
	}
	
	 @Override
	    public boolean equals(Object other) {
	        return other == this // short circuit if same object
	                || (other instanceof Birthday // instanceof handles nulls
	                && this.value.equals(((Birthday) other).value)); // state check
	    }

	    @Override
	    public int hashCode() {
	        return value.hashCode();
	    }

	    public boolean isPrivate() {
	        return isPrivate;
	    }
}
