import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StartScreen extends JPanel implements ActionListener{

/******************************************MEMBER-VARIABLES****************************************/
    
    ArrayList<JButton> character_buttons = new ArrayList<>();
    JButton play_btn;

    private boolean start_game = false;
    private PlayerType choosen_player = PlayerType.NONE;

    private int R = 12;
    private int G = 100;
    private int B = 12;

    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 250;
    private static final int NUM_PLAYERS = 4;

/******************************************CONSTRUCTORS********************************************/
    
    public StartScreen() {
        this.setBackground(new Color(R,G,B));
        this.setVisible(true);
    }

/******************************************PUBLIC-METHODES*****************************************/
    
    public boolean getStartGame() {
        return start_game;
    }

    public PlayerType getChoosenPlayer() {
        return choosen_player;
    }

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == character_buttons.get(0)) {
            choosen_player = PlayerType.PLAYER_1;
		}
        if(e.getSource() == character_buttons.get(1)) {
            choosen_player = PlayerType.PLAYER_2;
		}
        if(e.getSource() == character_buttons.get(2)) {
            choosen_player = PlayerType.PLAYER_3;
		}
        if(e.getSource() == character_buttons.get(3)) {
            choosen_player = PlayerType.PLAYER_4;
		}
        if(e.getSource() == play_btn) {
            if(choosen_player == PlayerType.NONE) {
                System.out.println("CHOOSE A PLAYER!");
                //!TODO make this a label!
            }
            else {
                start_game = true;
            }
		}
	}

    public void addPlayers() {
        for(int player = 1; player < NUM_PLAYERS + 1; player++) {
            ImageIcon character_image = new ImageIcon("players/player" + player + ".png");
            Image resized = character_image.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            character_image = new ImageIcon(resized);
            JButton character_btn = new JButton(character_image);
            character_btn.setBounds(100 + player *210,100, IMAGE_WIDTH, IMAGE_HEIGHT);
            //character_btn.setVisible(true);
            //!TODO Border emptyBorder = BorderFactory.createEmptyBorder();
            character_btn.addActionListener(this);
            character_buttons.add(character_btn);
            this.add(character_btn);
        }
    }

    public void addPlayButton() {
        ImageIcon play_btn_image = new ImageIcon("buttons/play.png");
        Image resized = play_btn_image.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        play_btn = new JButton(new ImageIcon(resized));
        play_btn.setBounds(1400,800, 150, 150);
       // play_btn.setVisible(true);
        play_btn.addActionListener(this);
        this.add(play_btn);
    }

/******************************************PRIVATE-METHODES****************************************/


}




