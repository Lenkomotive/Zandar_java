import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
/******************************************MEMBER-VARIABLES****************************************/
    public ArrayList<Card> cards = new ArrayList<>();

    public JButton put_btn;
    public JButton take_btn;


/******************************************CONSTRUCTORS********************************************/
    public Board() {
        this.setLayout(null);
        this.setBackground(new Color(Constants.RED, Constants.GREEN, Constants.BLUE));
    }

/******************************************PUBLIC-METHODES*****************************************/

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == put_btn) {
            System.out.println("put");
        }
        if(e.getSource() == take_btn) {
            System.out.println("take");

        }
    }

    public void initButtons() {
        ImageIcon image = new ImageIcon("buttons/put.png");
        Image resized = image.getImage().getScaledInstance(120, 70, java.awt.Image.SCALE_SMOOTH);
        put_btn = new JButton(new ImageIcon(resized));
        put_btn.setSize(120, 70);
        put_btn.addActionListener(this);

        image = new ImageIcon("buttons/take.png");
        resized = image.getImage().getScaledInstance(150, 70, java.awt.Image.SCALE_SMOOTH);
        take_btn = new JButton(new ImageIcon(resized));
        take_btn.setSize(150, 70);
        take_btn.addActionListener(this);
    }


/******************************************PRIVATE-METHODES****************************************/

}
