public class Square {
	// TODO: Make the owner a final field as it can only be set once
	private char owner = ' ';

	public Square()

	{
	}

	public char getOwner() {
		return this.owner;
	}

	public void setOwner(char owner) {
		this.owner = owner;
	}

	public String printSquare() {
		String r = String.format("[%c]", this.owner);
		return r;

	}

}
