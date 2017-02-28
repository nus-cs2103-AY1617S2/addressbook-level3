package seedu.addressbook.common;

public class Pair<F, S> {
	
	private F first;
	private S second;
	
	public Pair(F fir, S sec) {
		this.first = fir;
		this.second = sec;
	}
	
	public F getFirst() {
		return this.first;
	}
	
	public S getSecond() {
		return this.second;
	}

}
