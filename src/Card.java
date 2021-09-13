import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;

enum CardType {CLUB, DIAMOND, HEART, SPADE};
enum Position {UP, DOWN};

public class Card extends JLabel{

/******************************************MEMBER-VARIABLES****************************************/

    private CardType card_type;

    private int value;

    public final String PATH_CLUB = "_of_clubs";
    public final String PATH_DIAMOND = "_of_diamonds";
    public final String PATH_SPADE = "_of_spades";
    public final String PATH_HEART = "_of_hearts";
    public final String PATH = "cards/";
    public final String PATH_END = ".png";
    
    static int pos_x = 0;
    static int pos_y = 0;

    Position position = Position.DOWN;

    private static final int WIDTH = 80;
    private static final int HEIGHT = 100;

/******************************************CONSTRUCTORS********************************************/
    
public Card(CardType type, int value) {
        this.card_type = type;
        this.value = value;
        
        ImageIcon image = new ImageIcon(getPath());
        Image resized = image.getImage().getScaledInstance(WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(WIDTH, HEIGHT);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                doOnClick();
            }           
          });
    }

/******************************************PUBLIC-METHODES*****************************************/
    
    public int getValue() {
        return value;
    }

    public void setPosition(int x, int y) {
        this.setLocation(x, y);
    }


    public void floatCard() throws InterruptedException {
        for(int i = 0; i < 300; i++) {
            this.setLocation(0+5*i, 300);
            Thread.sleep(20);
        }
    }

    private void doOnClick() {
        if(this.position == Position.DOWN) {
            setPosition(500, 190);
            position = Position.UP;
        }
        else {
            setPosition(500, 200);
            position = Position.DOWN;
        }
    }
    
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
    

}
