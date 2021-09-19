import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.awt.*;

enum CardSuit {CLUB, DIAMOND, HEART, SPADE};
enum CardState {INACTIVE, ACTIVE_PLAYER_CARD, ACTIVE_BOARD_CARD};
enum CardType{PLAYER_CARD, BOARD_CARD};

public class Card extends JLabel{

/******************************************MEMBER-VARIABLES****************************************/

    public static Player player;

    private CardSuit suit;
    public CardType type;
    public CardState state = CardState.INACTIVE;

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
    public void setPlayerCardActive() {
        //!player.setCardInactive();
        this.setLocation(this.getLocation().x, this.getLocation().y - 20);
        state = CardState.ACTIVE_PLAYER_CARD;
    }

    public void setPlayerCardInactive() {
        this.setLocation(this.getLocation().x, this.getLocation().y + 20);
        state = CardState.INACTIVE;
    }

    public void setBoardCardActive() {
        this.setBorder(border);
        state = CardState.ACTIVE_BOARD_CARD;
    }

    public void setBoardCardInactive() {
        this.setBorder(null);
        state = CardState.INACTIVE;
    }
    
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
                if(this.state == CardState.INACTIVE) {
                    setPlayerCardActive();
                }
                else {
                    setPlayerCardInactive();
                }
                break;
            case BOARD_CARD:
                if(this.state == CardState.INACTIVE) {
                    setBoardCardActive();
                }
                else {
                    setBoardCardInactive();
                }
            default:
                break;
        }
    }
}
