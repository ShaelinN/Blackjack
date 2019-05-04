package cardGames;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame blackjack = new JFrame("Blackjack");
        blackjack.setSize(600,600);
        blackjack.setLocationRelativeTo(null);
        blackjack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        blackjack.add(new BlackJackPanel());
        blackjack.setVisible(true);
    }
}
