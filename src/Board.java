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

    public JLabel log;

    public PlayMove current_move = PlayMove.NONE;
    public Border border = new LineBorder(new Color(200,170,0), 5);
    public Border log_border = new LineBorder(Color.black, 3);

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

    public void initButtons() {
        ImageIcon image = new ImageIcon("buttons/put.png");
        Image resized = image.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
        put_btn = new JButton(new ImageIcon(resized));
        put_btn.setSize(110, 70);
        put_btn.addActionListener(this);
        put_btn.setBackground(Color.black);
        put_btn.addActionListener(this);

        image = new ImageIcon("buttons/take.png");
        resized = image.getImage().getScaledInstance(120, 60, java.awt.Image.SCALE_SMOOTH);
        take_btn = new JButton(new ImageIcon(resized));
        take_btn.setSize(130, 70);
        take_btn.setBackground(Color.black);
        take_btn.addActionListener(this);

        log = new JLabel();
        log.setSize(300,400);
        log.setFont(new Font("Arial", Font.BOLD, 14));
        log.setForeground(Color.black);
        log.setHorizontalAlignment(SwingConstants.LEFT);
        log.setVerticalAlignment(SwingConstants.TOP);
        log.setBorder(log_border);
    }


/******************************************PRIVATE-METHODES****************************************/

}
