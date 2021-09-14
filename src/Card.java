import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

enum CardType {CLUB, DIAMOND, HEART, SPADE};
enum State {INACTIVE, ACTIVE};

public class Card extends JLabel{

/******************************************MEMBER-VARIABLES****************************************/

    private CardType card_type;

    public int value;

    public final String PATH_CLUB = "_of_clubs";
    public final String PATH_DIAMOND = "_of_diamonds";
    public final String PATH_SPADE = "_of_spades";
    public final String PATH_HEART = "_of_hearts";
    public final String PATH = "cards/";
    public final String PATH_END = ".png";
    
    State state = State.INACTIVE;

/******************************************CONSTRUCTORS********************************************/
    
public Card(CardType type, int value) {
        this.card_type = type;
        this.value = value;
        
        ImageIcon image = new ImageIcon(getPath());
        Image resized = image.getImage().getScaledInstance(Constants.CARD_WIDTH, Constants.CARD_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.CARD_WIDTH, Constants.CARD_HEIGHT);

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
        switch (card_type) {
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
        if(this.state == State.INACTIVE) {
            this.setLocation(this.getLocation().x, this.getLocation().y - 20);
            state = State.ACTIVE;
        }
        else {
            this.setLocation(this.getLocation().x, this.getLocation().y + 20);
            state = State.INACTIVE;
        }
    }
}
