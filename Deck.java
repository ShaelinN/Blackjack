/**
 * package cardGames
 * 
 */
package cardGames;

import java.util.*;

/**
 * Class Deck
 * @author anban
 */
public class Deck {

	private ArrayList<Card> deck;
	
	public Deck(){
		deck = new ArrayList<Card>(52);
		
		for (int i = 0; i < 4; i++){
			for (int j = 1; j <= 13; j++ ) {
				deck.add(new Card(i,j));
			}
		}
		
	}

	public void shuffle(){
		//Collections class has static utility methods that work with lists
		Collections.shuffle(deck);



	}
	
	public int size(){
		return deck.size();
	}

	

	public boolean isEmpty(){
		return deck.isEmpty();
	}

	public Card deal(){
		if (! isEmpty())
			return deck.remove(size()-1);
		else
			return null;
	}



}
