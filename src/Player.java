import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

enum PlayerType {NONE, PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4};

public class Player extends JLabel{
/******************************************MEMBER-VARIABLES****************************************/
   
    public JLabel deck_backside_label;
    public JLabel num_cards_label;

    public ArrayList<Card> cards = new ArrayList<>();

/******************************************CONSTRUCTORS********************************************/
    
    public Player(PlayerType player) {
        ImageIcon image = new ImageIcon("players/player" + player.ordinal() + ".png");
        Image resized = image.getImage().getScaledInstance(Constants.PLAYER_IMAGE_WIDTH, Constants.PLAYER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.PLAYER_IMAGE_WIDTH, Constants.PLAYER_IMAGE_HEIGHT);
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
    }

/******************************************PRIVATE-METHODES****************************************/

}

