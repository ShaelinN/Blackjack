package cardGames;

/**
 * @author anban
 *
 */
public class Card {

	public static final int heart = 0;
	public static final int club = 1;
	public static final int spade = 2;
	public static final int diamond = 3;

	public boolean faceUp = true;
	private int suit;
	private int value;

	/**
	 * @param suit
	 * @param value
	 */
	public Card(int suit, int value) {
		this.suit = suit;
		this.value = value;
	}

	/**
	 * Represent the card as string for printing Now the cardGames.Card can be printed with
	 * println
	 */
	public String toString() {
		return "cardGames.Card [suit=" + suit + ", value=" + value + "]";
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the suit
	 */
	public int getSuit() {
		return suit;
	}

	public void flip(){
	    faceUp = !faceUp;
    }
}
