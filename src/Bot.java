import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Bot extends JLabel{
/******************************************MEMBER-VARIABLES****************************************/
    private static Bot instance = null;
    public static Board board = null;
    public JLabel deck_backside_label;

    public JLabel card_backside[] = new JLabel[4];
    ArrayList<Integer> card_backside_index = new ArrayList<Integer>();


    public ArrayList<Card> cards_in_hand = new ArrayList<>();
    public ArrayList<Card> collected_cards = new ArrayList<>();

/******************************************CONSTRUCTORS********************************************/
    private Bot(Board board) {
        ImageIcon image = new ImageIcon("players/chad.png");
        Image resized = image.getImage().getScaledInstance(Constants.BOT_IMAGE_WIDTH, Constants.BOT_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.BOT_IMAGE_WIDTH, Constants.BOT_IMAGE_HEIGHT);
        initDeckLabels();
        initIndexList();
    }

/******************************************PUBLIC-METHODES*****************************************/
    public static Bot getInstance(Board board) {
        if(instance == null) {
            instance = new Bot(board);
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

    public void initIndexList() {
        for(int i = 0; i < 4; i++) {
            card_backside_index.add(i);
        }
    }

    public String getHand() {
        String hand = "";
        for(Card card : cards_in_hand) {
            hand += Integer.toString(card.value) + " ";
        }
        return hand;
    }

    public BotMove getMove() {
        BotMove move = null;

        move = tryTakeOneCard();

        if(move != null) {
            return move;
        }
        move = new BotMove();
        move.card_in_hand = findLowestValue();
        move.move = MoveType.PUT;
        return move;
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

    private Card findLowestValue() {
        Card lowest = null;
        int lowest_value = 0;
        for(Card card: cards_in_hand) {
            if(card.value > lowest_value) {
                lowest = card;
                lowest_value = card.value;
            }
        }
        return lowest;
    }

    private BotMove tryTakeOneCard() {
        BotMove move = null;
        for(Card card_in_hand: cards_in_hand) {
            for(Card card_on_board: board.cards) {
                if(card_in_hand == card_on_board) {
                    move = new BotMove();
                    move.card_in_hand = card_in_hand;
                    move.board_cards.add(card_on_board);
                    move.move = MoveType.TAKE;
                    break;
                }
            }
        }
        return move;
    }
}
