import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.awt.*;

enum CardSuit {CLUB, DIAMOND, HEART, SPADE};
enum State {INACTIVE, ACTIVE_PLAYER_CARD, ACTIVE_BOARD_CARD};
enum CardType{PLAYER_CARD, BOARD_CARD};

public class Card extends JLabel{

/******************************************MEMBER-VARIABLES****************************************/

    private CardSuit suit;
    public CardType type;
    public State state = State.INACTIVE;

    public int value;

    public final String PATH_CLUB = "_of_clubs";
    public final String PATH_DIAMOND = "_of_diamonds";
    public final String PATH_SPADE = "_of_spades";
    public final String PATH_HEART = "_of_hearts";
    public final String PATH = "cards/";
    public final String PATH_END = ".png";
    
    public Border border = new LineBorder(new Color(200,170,0), 5);

/******************************************CONSTRUCTORS********************************************/
    
public Card(CardSuit type, int value) {
        this.suit = type;
        this.value = value;
        
        ImageIcon image = new ImageIcon(getPath());
        Image resized = image.getImage().getScaledInstance(Constants.CARD_WIDTH, Constants.CARD_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.CARD_WIDTH + 10, Constants.CARD_HEIGHT + 10);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                doOnClick();
            }           
          });
    }

/******************************************PUBLIC-METHODES*****************************************/
    

    
/******************************************PRIVATE-METHODES****************************************/
   
    private String getPath() {
        String path = PATH;
        switch (suit) {
            case CLUB:
                path += this.value + PATH_CLUB + PATH_END;
                break;
            case DIAMOND:
                path += this.value + PATH_DIAMOND + PATH_END;
                break;
            case HEART:
                path += this.value + PATH_HEART + PATH_END;
                break;
            case SPADE:
                path += this.value + PATH_SPADE + PATH_END;
                break;
        }
        return path;
    }
    
    private void doOnClick() {
        switch (type) {
            case PLAYER_CARD:
                if(this.state == State.INACTIVE) {
                    this.setLocation(this.getLocation().x, this.getLocation().y - 20);
                    state = State.ACTIVE_PLAYER_CARD;
                }
                else {
                    this.setLocation(this.getLocation().x, this.getLocation().y + 20);
                    state = State.INACTIVE;
                }
                break;
            case BOARD_CARD:
                if(this.state == State.INACTIVE) {
                    this.setBorder(border);
                    state = State.ACTIVE_BOARD_CARD;
                }
                else {
                    this.setBorder(null);
                    state = State.INACTIVE;
                }
            default:
                break;
        }

    }
}
