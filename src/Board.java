import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.border.*;


enum PlayMove {PUT, TAKE, NONE};
enum ActivePlayer {PLAYER, BOT, NONE};

public class Board extends JPanel implements ActionListener{
/******************************************MEMBER-VARIABLES****************************************/
    public ArrayList<Card> cards = new ArrayList<>();

    public JButton put_btn;
    public JButton take_btn;

    private Player player;
    private Bot bot;
    private Deck deck;

    public JLabel log;

    public PlayMove current_move = PlayMove.NONE;


/******************************************CONSTRUCTORS********************************************/
    public Board() {
        this.setLayout(null);
        this.setBackground(Constants.GREEN);
    }

/******************************************PUBLIC-METHODES*****************************************/
    public int getActiveBoardCardValue() {
        int sum = 0;
        for(Card card: cards) {
            if(card.state == CardState.ACTIVE_BOARD_CARD) {
                sum += card.value;
            }
        }
        return sum;
    }

    public void setCardsInactive() {
        for(Card c: cards) {
            c.setBoardCardInactive();
        }
    }

    public ArrayList<Card> getActiveBoardCards() {
        ArrayList<Card> active_cards = new ArrayList<Card>();
        for(Card card: cards) {
            if(card.state == CardState.ACTIVE_BOARD_CARD) {
                active_cards.add(card);
            }
        }
        return active_cards;
    }

    public void removeCardsFromBoard(ArrayList<Card> active_cards) {
        for(Card card: active_cards) {
            cards.remove(card);
            card.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == put_btn) {
            current_move = PlayMove.PUT;
        }
        if(e.getSource() == take_btn) {
            current_move = PlayMove.TAKE;
        }
    }

    public void initPutBtn() {
        ImageIcon image = new ImageIcon("buttons/put.png");
        Image resized = image.getImage().getScaledInstance(Constants.PUT_BUTTON_WIDTH, Constants.PUT_BUTTON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        put_btn = new JButton(new ImageIcon(resized));
        put_btn.setSize(Constants.PUT_BUTTON_WIDTH + 10, Constants.PUT_BUTTON_HEIGHT + 10);
        put_btn.setBackground(Color.black);
        put_btn.addActionListener(this);
    }

    public void initTakeBtn() {
        ImageIcon image = new ImageIcon("buttons/take.png");
        Image resized = image.getImage().getScaledInstance(Constants.TAKE_BUTTON_WIDTH, Constants.TAKE_BUTTON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        take_btn = new JButton(new ImageIcon(resized));
        take_btn.setSize(Constants.TAKE_BUTTON_WIDTH + 10, Constants.TAKE_BUTTON_HEIGHT + 10);
        take_btn.setBackground(Color.black);
        take_btn.addActionListener(this);
    }

    public void initLogLabel() {
        log = new JLabel();
        log.setSize(Constants.LOG_WIDTH, Constants.LOG_HEIGHT);
        log.setFont(new Font("Arial", Font.BOLD, Constants.LOG_FONT_SIZE));
        log.setForeground(Color.black);
        log.setHorizontalAlignment(SwingConstants.LEFT);
        log.setVerticalAlignment(SwingConstants.TOP);
        log.setBorder(new LineBorder(Color.black, 3));
    }

    public void initLog(Player player, Bot bot, Deck deck) {
        this.player = player;
        this.bot = bot;
        this.deck = deck;
    }

    public void log() {
        String log_string =
            "<html>" + "<p style=font-size:20px> Log:</p>" +
            "<pre> NUM BOARD CARDS:                 " + this.cards.size() + "<br/>"  + 
            "<pre> NUM DECK CARDS:                  " + deck.cards.size() + "<br/>"  + "<br/>" +
            "<pre> SUM ACTIVE PLAYER CARDS:         " + player.getActivePlayerCardValue() + "<br/>"  + 
            "<pre> SUM ACTIVE BOARD CARDS:          " + this.getActiveBoardCardValue() + "<br/>" +
            "<pre> NUM PLAYER CARDS COLLECTED:      " + player.collected_cards.size() + "<br/>"  +
            "<pre> NUM BOT CARDS COLLECTED:         " + bot.collected_cards.size() + "<br/>"  +
            "<html/>";

            this.log.setText(log_string);
    }



/******************************************PRIVATE-METHODES****************************************/

}
