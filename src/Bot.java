import javax.swing.*;
import java.util.*;

import java.awt.*;

public class Bot extends JLabel{
/******************************************MEMBER-VARIABLES****************************************/
       
    public JLabel deck_backside_label;
    public JLabel num_cards_label;

    public JLabel card_backside[] = new JLabel[4];

    public ArrayList<Card> cards_in_hand = new ArrayList<>();
    public ArrayList<Card> collected_cards = new ArrayList<>();

/******************************************CONSTRUCTORS********************************************/
  
    public Bot() {
        ImageIcon image = new ImageIcon("players/chad.png");
        Image resized = image.getImage().getScaledInstance(Constants.BOT_IMAGE_WIDTH, Constants.BOT_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.BOT_IMAGE_WIDTH, Constants.BOT_IMAGE_HEIGHT);
        initDeckLabels();
    }

/******************************************PUBLIC-METHODES*****************************************/
   
    private void initDeckLabels() {
        deck_backside_label = new JLabel();
        ImageIcon image = new ImageIcon("cards/backside_deck.png");
        Image resized = image.getImage().getScaledInstance(Constants.PLAYER_DECK_WIDTH, Constants.PLAYER_DECK_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        deck_backside_label.setIcon(image);
        deck_backside_label.setSize(Constants.PLAYER_DECK_WIDTH, Constants.PLAYER_DECK_HEIGHT);

        num_cards_label = new JLabel("0");
        num_cards_label.setFont(new Font("Comic Sans",Font.BOLD, Constants.PLAYER_NUM_CARD_FONT_SIZE));
        num_cards_label.setSize(Constants.PLAYER_NUM_CARD_WIDTH, Constants.PLAYER_NUM_CARD_HEIGHT);

        for(int i = 0; i < 4; i++) {
            card_backside[i] = new JLabel();
            image = new ImageIcon("cards/backside.png");
            resized = image.getImage().getScaledInstance(Constants.CARD_WIDTH, Constants.CARD_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(resized);
            card_backside[i].setIcon(image);
            card_backside[i].setSize(Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        }
    }
    
/******************************************PRIVATE-METHODES****************************************/

}