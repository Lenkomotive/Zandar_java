import javax.swing.*;
import java.util.*;



import java.awt.*;

public class Bot extends JLabel{
/******************************************MEMBER-VARIABLES****************************************/
    private static Bot instance = null;
    public JLabel deck_backside_label;

    public JLabel card_backside[] = new JLabel[4];
    ArrayList<Integer> card_backside_index = new ArrayList<Integer>();


    public ArrayList<Card> cards_in_hand = new ArrayList<>();
    public ArrayList<Card> collected_cards = new ArrayList<>();

/******************************************CONSTRUCTORS********************************************/
    private Bot() {
        ImageIcon image = new ImageIcon("players/chad.png");
        Image resized = image.getImage().getScaledInstance(Constants.BOT_IMAGE_WIDTH, Constants.BOT_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.BOT_IMAGE_WIDTH, Constants.BOT_IMAGE_HEIGHT);
        initDeckLabels();
        initIndexList();
    }

/******************************************PUBLIC-METHODES*****************************************/
    public static Bot getInstance() {
        if(instance == null) {
            instance = new Bot();
        }
        return instance;
    }

    public void hideCardBackside() {
        Random rand = new Random();
        int list_index = rand.nextInt(card_backside_index.size());
        int card_index = card_backside_index.get(list_index);
        card_backside_index.remove(list_index);
        card_backside[card_index].setVisible(false);
    }


/******************************************PRIVATE-METHODES****************************************/
    private void initDeckLabels() {
        deck_backside_label = new JLabel();
        ImageIcon image = new ImageIcon("cards/backside_deck.png");
        Image resized = image.getImage().getScaledInstance(Constants.PLAYER_DECK_WIDTH, Constants.PLAYER_DECK_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        deck_backside_label.setIcon(image);
        deck_backside_label.setSize(Constants.PLAYER_DECK_WIDTH, Constants.PLAYER_DECK_HEIGHT);

        for(int i = 0; i < 4; i++) {
            card_backside[i] = new JLabel();
            image = new ImageIcon("cards/backside.png");
            resized = image.getImage().getScaledInstance(Constants.CARD_WIDTH, Constants.CARD_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(resized);
            card_backside[i].setIcon(image);
            card_backside[i].setSize(Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        }
    }

    public void initIndexList() {
        for(int i = 0; i < 4; i++) {
            card_backside_index.add(i);
        }
    }
}