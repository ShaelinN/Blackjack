package cardGames;

import java.util.ArrayList;

/**
 * @author anban
 *
 */
public class Hand {
	private ArrayList<Card> hand;

	public Hand() {
		hand = new ArrayList<Card>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public  int value() {
		int value = 0;
		for (int i = 0; i < hand.size(); i++) {
			value += hand.get(i).getValue();
		}
		return value;
	}

	public int size(){
		return hand.size();
	}


	public Card getCard(int i) {
		if (i < hand.size())
			return hand.get(i);
		else
			return null;
	}

	public void printHand(){
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i));
		}
	}
	
}
