import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import java.awt.*;

public class Deck{
/******************************************MEMBER-VARIABLES****************************************/
  
    public ArrayList<Card> cards = new ArrayList<>();

    public JLabel deck_backside_label;
    public JLabel num_cards_label;

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
            cards.add(new Card(CardType.CLUB, value));
            cards.add(new Card(CardType.DIAMOND, value));
            cards.add(new Card(CardType.HEART, value));
            cards.add(new Card(CardType.SPADE, value));
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

        num_cards_label = new JLabel("52");
        num_cards_label.setFont(new Font("Arial", Font.BOLD, Constants.DECK_NUM_CARD_FONT_SIZE));
        num_cards_label.setSize(Constants.DECK_NUM_CARD_WIDTH, Constants.DECK_NUM_CARD_HEIGHT);
    }
}