import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.border.*;

enum PlayMove {PUT, TAKE, NONE};
enum ActivePlayer {PLAYER, BOT};

public class Board extends JPanel implements ActionListener{
    private static Board instance = null;
    public ArrayList<Card> cards = new ArrayList<>();
    public LinkedHashMap<Point, Boolean> board_map = new LinkedHashMap<Point, Boolean>();
    public JButton put_btn;
    public JButton take_btn;
    public JLabel log;
    public PlayMove current_move = PlayMove.NONE;

    private Board() {
        this.setLayout(null);
        this.setBackground(Constants.GREEN);
        initBoardMap();
    }

    public static Board getInstance() {
        if(instance == null) {
            instance = new Board();
        }
        return instance;
    }

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

    public void removeCardsFromBoard() {
        for(Card card: getActiveBoardCards()) {
            board_map.put(card.getLocation(), Boolean.FALSE);
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

    public Point getNextCardPlace() {
        Point next = null;
        for(Point p : board_map.keySet()) {
            if(board_map.get(p) == Boolean.FALSE) {
                board_map.put(p, Boolean.TRUE);
                next = p;
                break;
            }
        }
        return next;
    }

    private void initBoardMap() {
        for(int x = Constants.CARDS_MOST_LEFT_POSITION; x <= Constants.CARDS_MOST_RIGHT_POSITION; x += Constants.BOARD_CARD_DISTANCE) {
            board_map.put(new Point(x, Constants.BOARD_UPPER_CARD_Y), Boolean.FALSE); 
        }
        for(int x = Constants.CARDS_MOST_LEFT_POSITION; x <= Constants.CARDS_MOST_RIGHT_POSITION; x += Constants.BOARD_CARD_DISTANCE) {
            board_map.put(new Point(x, Constants.BOARD_LOWER_CARD_Y), Boolean.FALSE);
        }
    }
}
