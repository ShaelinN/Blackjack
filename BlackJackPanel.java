package cardGames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class BlackJackPanel extends JPanel implements ActionListener {
    private JPanel buttonPanel = new JPanel();
        private JButton hit = new JButton("HIT");
        private JButton stand = new JButton("STAND");
        private JButton newGame = new JButton("NEW GAME");
        private JLabel comment = new JLabel();

    private Table dealerPanel = new Table();
    private Table playerPanel = new Table();

    private Deck deck;

    private boolean playerBust;

    public BlackJackPanel(){
    //prepare and place components before game
        hit.addActionListener(this);
        stand.addActionListener(this);
        newGame.addActionListener(this);

        buttonPanel.add(hit);
        buttonPanel.add(stand);
        buttonPanel.add(newGame);
        buttonPanel.add(comment);


        playerPanel.setBackground(Color.GREEN);
        dealerPanel.setBackground(Color.GREEN);
        playerPanel.add(new JLabel("PLAYER"));
        dealerPanel.add(new JLabel("DEALER"));

        dealerPanel.setPreferredSize(new Dimension(600,260));


        setLayout(new BorderLayout());
        add(buttonPanel,BorderLayout.SOUTH);
        add(dealerPanel,BorderLayout.NORTH);
        add(playerPanel);


        setVisible(true);

        setUpNewGame();

    }

    private void initialDeal(){
        playerPanel.hand.addCard(deck.deal());
        dealerPanel.hand.addCard(deck.deal());
            dealerPanel.hand.getCard(0).flip();
        playerPanel.hand.addCard(deck.deal());
        dealerPanel.hand.addCard(deck.deal());

        checkForBlackJack();

    }

    private void checkForBlackJack() {
        boolean playerBlackjack = (playerPanel.hand.getBlackjackValue()==21);
        boolean dealerBlackjack = (dealerPanel.hand.getBlackjackValue()==21);

        if(playerBlackjack || dealerBlackjack){
            dealerPanel.hand.getCard(0).flip();
            repaint();

            if(playerBlackjack && !dealerBlackjack){
                comment.setText("YOU WIN!");
            }
            else if(dealerBlackjack && !playerBlackjack){
                comment.setText("YOU LOSE!");
            }
            else if(dealerBlackjack && playerBlackjack){
                comment.setText("DRAW");
            }
        }
    }

    private void doEndActions(){
        hit.setEnabled(false);
        stand.setEnabled(false);


        while(dealerPanel.hand.getBlackjackValue()<=16){
            dealerPanel.hand.addCard(deck.deal());
        }
        repaint();

        //look for busts
        if(dealerPanel.hand.getBlackjackValue()>21){
            comment.setText("DEALER BUSTS");
            if(!playerBust){
                comment.setText("YOU WIN!");
                return;
            }
            else{
                comment.setText("DRAW");
                return;
            }
        }
        else if(playerBust){
            comment.setText("YOU LOSE");
            return;
        }

        //if reached here then neither has bust
        if(dealerPanel.hand.getBlackjackValue()>playerPanel.hand.getBlackjackValue()){
            comment.setText("YOU LOSE");
        }
        else if (dealerPanel.hand.getBlackjackValue()< playerPanel.hand.getBlackjackValue()){
            comment.setText("YOU WIN!");
        }
        else{
            comment.setText("DRAW");
        }

        dealerPanel.hand.getCard(0).flip();
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("HIT")){// if hit clicked
            playerPanel.hand.addCard(deck.deal());
            repaint();
            if(playerPanel.hand.getBlackjackValue()>21){
                playerBust = true;
                doEndActions();
            }
        }

        else if(e.getActionCommand().equals("STAND")){ //if stand clicked
            doEndActions();
        }

        else if(e.getActionCommand().equals("NEW GAME")){ // if new game clicked
            setUpNewGame();
        }
    }

    private void setUpNewGame() {
        playerPanel.emptyHand();
        dealerPanel.emptyHand();
        comment.setText("");
        playerBust = false;
        deck = new Deck();
        deck.shuffle();
        initialDeal();
        hit.setEnabled(true);
        stand.setEnabled(true);

        repaint();
    }

    class Table extends JPanel{
        BlackJackHand hand;
        private int leftMost = 20;

        public Table() {
            emptyHand();
        }

        public void emptyHand(){
            hand = new BlackJackHand();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            leftMost = 20;
            //actual code
            for (int i = 0; i < hand.size(); i++) { //for each card
                //generate card image name
                String cardName = "cardImages/";
                if(hand.getCard(i).faceUp){
                    switch (hand.getCard(i).getSuit()){
                        case 0:
                            cardName+= "h";
                            break;
                        case 1:
                            cardName+= "c";
                            break;
                        case 2:
                            cardName+="s";
                            break;
                        case 3:
                            cardName+="d";
                            break;

                    }
                    cardName+= hand.getCard(i).getValue()+".gif";
                }
                else{
                    cardName = "cardImages/b1fv.gif";
                }
                //full card name acquired


                Image img = null;
                try {
                    img = ImageIO.read(new File(cardName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(img != null){
                    g.drawImage(img,leftMost,40,70,120,null);
                    leftMost+=30;
                }

            }//end forloop

        }//end paintComponent
    }//end Tableclass
}
