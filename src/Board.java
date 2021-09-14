import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Board extends JPanel{
/******************************************MEMBER-VARIABLES****************************************/
    private int R = 12;
    private int G = 100;
    private int B = 12;

    public ArrayList<Card> cards = new ArrayList<>();

/******************************************CONSTRUCTORS********************************************/
    public Board() {
        this.setLayout(null);
        this.setBackground(new Color(R,G,B));
    }

/******************************************PUBLIC-METHODES*****************************************/

/******************************************PRIVATE-METHODES****************************************/

}
