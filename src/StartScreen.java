import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen extends JPanel implements ActionListener{

/******************************************MEMBER-VARIABLES****************************************/
    
    public JButton start_btn;
    public JButton player_1_btn;
    public JButton player_2_btn;
    public JButton player_3_btn;
    public JButton player_4_btn;

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

		if(e.getSource() == player_1_btn) {
            choosen_player = PlayerType.PLAYER_1;
		}
        if(e.getSource() == player_2_btn) {
            choosen_player = PlayerType.PLAYER_2;
		}
        if(e.getSource() == player_3_btn) {
            choosen_player = PlayerType.PLAYER_3;
		}
        if(e.getSource() == player_4_btn) {
            choosen_player = PlayerType.PLAYER_4;
		}
        if(e.getSource() == start_btn) {
            if(choosen_player == PlayerType.NONE) {
                System.out.println("CHOOSE A PLAYER!");
            }
            else {
                start_game = true;
            }
		}
	}

    public void initPlayers() {
        ImageIcon character_image = new ImageIcon("players/player1.png");
        Image resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        player_1_btn = new JButton(new ImageIcon(resized));
        player_1_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT);
        player_1_btn.addActionListener(this);

        character_image = new ImageIcon("players/player2.png");
        resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        player_2_btn = new JButton(new ImageIcon(resized));
        player_2_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT);
        player_2_btn.addActionListener(this);

        character_image = new ImageIcon("players/player3.png");
        resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        player_3_btn = new JButton(new ImageIcon(resized));
        player_3_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT);
        player_3_btn.addActionListener(this);

        character_image = new ImageIcon("players/player4.png");
        resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        player_4_btn = new JButton(new ImageIcon(resized));
        player_4_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT);
        player_4_btn.addActionListener(this);
        
    }

    public void initStartButton() {
        ImageIcon start_btn_image = new ImageIcon("buttons/start.png");
        Image resized = start_btn_image.getImage().getScaledInstance(Constants.START_BUTTON_WIDTH, Constants.START_BUTTON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        start_btn = new JButton(new ImageIcon(resized));
        start_btn.setSize(Constants.START_BUTTON_WIDTH, Constants.START_BUTTON_HEIGHT);
        start_btn.addActionListener(this);
    }

/******************************************PRIVATE-METHODES****************************************/


}




