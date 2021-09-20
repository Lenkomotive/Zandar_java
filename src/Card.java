import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

enum CardSuit  {CLUB, DIAMOND, HEART, SPADE};
enum CardState {INACTIVE, ACTIVE_PLAYER_CARD, ACTIVE_BOARD_CARD};
enum CardType  {PLAYER_CARD, BOARD_CARD, BOT_CARD};

public class Card extends JLabel{
    public static Player player;
    public CardSuit suit;
    public CardType type;
    public CardState state = CardState.INACTIVE;
    public int value;
    public Border baoard_card_border = new LineBorder(Constants.GOLD, 5);
    public Border player_card_boarder = new LineBorder(Constants.RED , 5);

    
    public Card(CardSuit type, int value) {
        this.suit = type;
        this.value = value;
        
        initCardImage();
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                doOnClick();
            }           
        });
    }

    public void setPlayerCardActive() {
        player.setCardInactive();
        this.setBorder(player_card_boarder);
        state = CardState.ACTIVE_PLAYER_CARD;
    }

    public void setPlayerCardInactive() {
        this.setBorder(null);
        state = CardState.INACTIVE;
    }

    public void setBoardCardActive() {
        this.setBorder(baoard_card_border);
        state = CardState.ACTIVE_BOARD_CARD;
    }

    public void setBoardCardInactive() {
        this.setBorder(null);
        state = CardState.INACTIVE;
    }

    private void initCardImage() {
        ImageIcon image = new ImageIcon(getPath());
        Image resized = image.getImage().getScaledInstance(Constants.CARD_WIDTH, Constants.CARD_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.CARD_WIDTH + 10, Constants.CARD_HEIGHT + 10);
    }
    
    private String getPath() {
        String path = Constants.PATH;
        switch (suit) {
            case CLUB:
                path += this.value + Constants.PATH_CLUB + Constants.PATH_END;
                break;
            case DIAMOND:
                path += this.value + Constants.PATH_DIAMOND + Constants.PATH_END;
                break;
            case HEART:
                path += this.value + Constants.PATH_HEART + Constants.PATH_END;
                break;
            case SPADE:
                path += this.value + Constants.PATH_SPADE + Constants.PATH_END;
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
