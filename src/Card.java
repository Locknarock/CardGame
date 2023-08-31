
public class Card {
	
	public String toString() {
		
		String card;
		
		String frame =
				" ____\n" +
                "|%-3s|\n" +
                "|%2s |\n" +
                "|%3s|\n"+
                "|___|";
		
		card =String.format(frame, suit, name, suit);
		return card ;
		
	}

	private String name;
	private char suit;
	private int value;

	public Card(String name, char suit, int value) {

		this.name=name;
		this.suit = suit;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSuit() {
		return suit;
	}

	public void setSuit(char suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	

}

