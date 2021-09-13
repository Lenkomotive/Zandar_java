import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StartScreen extends JPanel implements ActionListener{

/******************************************MEMBER-VARIABLES****************************************/
    
    ArrayList<JButton> character_buttons = new ArrayList<>();
    public JButton play_btn;
    public JButton player_1;
    public JButton player_2;
    public JButton player_3;
    public JButton player_4;

    private boolean start_game = false;
    private PlayerType choosen_player = PlayerType.NONE;

/******************************************CONSTRUCTORS********************************************/
    
    public StartScreen() {
        this.setBackground(new Color(Constants.RED, Constants.GREEN, Constants.BLUE));
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
            }
            else {
                start_game = true;
            }
		}
	}

    public void initPlayers() {
        for(int player = 1; player <= Constants.NUM_PLAYERS; player++) {
            ImageIcon character_image = new ImageIcon("players/player" + player + ".png");
            Image resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            character_image = new ImageIcon(resized);
            JButton character_btn = new JButton(character_image);
           // character_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT);
            character_btn.setBounds(100 + player *230, 300, Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT);
            character_btn.addActionListener(this);
            character_buttons.add(character_btn);
            this.add(character_btn);
        }
    }

    public void initPlayButton() {
        ImageIcon play_btn_image = new ImageIcon("buttons/start.png");
        Image resized = play_btn_image.getImage().getScaledInstance(Constants.PLAY_BUTTON_WIDTH, Constants.PLAY_BUTTON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        play_btn = new JButton(new ImageIcon(resized));
        play_btn.setSize(Constants.PLAY_BUTTON_WIDTH, Constants.PLAY_BUTTON_HEIGHT);
        play_btn.addActionListener(this);
    }

/******************************************PRIVATE-METHODES****************************************/


}




