import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import java.awt.*;

public class Deck{
/******************************************MEMBER-VARIABLES****************************************/
  
    public ArrayList<Card> cards = new ArrayList<>();

    public JLabel deck_backside_label;

/******************************************CONSTRUCTORS********************************************/
   
    public Deck() {
        initDeck();
        initDeckLabels();
    }

/******************************************PUBLIC-METHODES*****************************************/
    
    public Card getCard() {
        Card card = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return card;
    }

/******************************************PRIVATE-METHODES****************************************/
    
    private void initDeck() {
        for(int value = 1; value <= 13; value++) {
            cards.add(new Card(CardSuit.CLUB, value));
            cards.add(new Card(CardSuit.DIAMOND, value));
            cards.add(new Card(CardSuit.HEART, value));
            cards.add(new Card(CardSuit.SPADE, value));
        }
        Collections.shuffle(cards);
    }

    private void initDeckLabels() {
        deck_backside_label = new JLabel();
        ImageIcon image = new ImageIcon("cards/backside_deck.png");
        Image resized = image.getImage().getScaledInstance(Constants.DECK_DECK_WIDTH, Constants.DECK_DECK_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        deck_backside_label.setIcon(image);
        deck_backside_label.setSize(Constants.DECK_DECK_WIDTH, Constants.DECK_DECK_HEIGHT);
    }
}