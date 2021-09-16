import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class StartScreen extends JPanel implements ActionListener{

/******************************************MEMBER-VARIABLES****************************************/
    
    public JLabel choose_player_label;

    public JButton start_btn;
    public JButton player_1_btn;
    public JButton player_2_btn;
    public JButton player_3_btn;
    public JButton player_4_btn;

    public boolean start_game = false;
    private PlayerType choosen_player = PlayerType.NONE;

    public Border border = new LineBorder(Constants.GOLD, 8);


/******************************************CONSTRUCTORS********************************************/
    
    public StartScreen() {
        this.setLayout(null);
        this.setBackground(Constants.GREEN);
        this.setVisible(true);
    }

/******************************************PUBLIC-METHODES*****************************************/
    
    public PlayerType getChoosenPlayer() {
        return choosen_player;
    }

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == player_1_btn) {
            unsetAllBorders();
            choosen_player = PlayerType.PLAYER_1;
            player_1_btn.setBorder(border);
		}
        if(e.getSource() == player_2_btn) {
            unsetAllBorders();
            choosen_player = PlayerType.PLAYER_2;
            player_2_btn.setBorder(border);
		}
        if(e.getSource() == player_3_btn) {
            unsetAllBorders();
            choosen_player = PlayerType.PLAYER_3;
            player_3_btn.setBorder(border);
		}
        if(e.getSource() == player_4_btn) {
            unsetAllBorders();
            choosen_player = PlayerType.PLAYER_4;
            player_4_btn.setBorder(border);
		}
        if(e.getSource() == start_btn) {
            if(choosen_player != PlayerType.NONE) {
                start_game = true;
            }
		}
	}

    public void initPlayers() {
        ImageIcon character_image = new ImageIcon("players/player1.png");
        Image resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        player_1_btn = new JButton(new ImageIcon(resized));
        player_1_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH + Constants.CHARACTER_IMAGE_BORDER, Constants.CHARACTER_IMAGE_HEIGHT + Constants.CHARACTER_IMAGE_BORDER);
        player_1_btn.addActionListener(this);
        player_1_btn.setBackground(Color.BLACK);

        character_image = new ImageIcon("players/player2.png");
        resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        player_2_btn = new JButton(new ImageIcon(resized));
        player_2_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH + Constants.CHARACTER_IMAGE_BORDER, Constants.CHARACTER_IMAGE_HEIGHT + Constants.CHARACTER_IMAGE_BORDER);
        player_2_btn.addActionListener(this);
        player_2_btn.setBackground(Color.BLACK);

        character_image = new ImageIcon("players/player3.png");
        resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        player_3_btn = new JButton(new ImageIcon(resized));
        player_3_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH + Constants.CHARACTER_IMAGE_BORDER, Constants.CHARACTER_IMAGE_HEIGHT + Constants.CHARACTER_IMAGE_BORDER);
        player_3_btn.addActionListener(this);
        player_3_btn.setBackground(Color.BLACK);

        character_image = new ImageIcon("players/player4.png");
        resized = character_image.getImage().getScaledInstance(Constants.CHARACTER_IMAGE_WIDTH, Constants.CHARACTER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        player_4_btn = new JButton(new ImageIcon(resized));
        player_4_btn.setSize(Constants.CHARACTER_IMAGE_WIDTH + Constants.CHARACTER_IMAGE_BORDER, Constants.CHARACTER_IMAGE_HEIGHT + Constants.CHARACTER_IMAGE_BORDER);
        player_4_btn.addActionListener(this);        
        player_4_btn.setBackground(Color.BLACK);
    }

    public void initStartButton() {
        ImageIcon start_btn_image = new ImageIcon("buttons/start.png");
        Image resized = start_btn_image.getImage().getScaledInstance(Constants.START_BUTTON_WIDTH, Constants.START_BUTTON_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        start_btn = new JButton(new ImageIcon(resized));
        start_btn.setSize(Constants.START_BUTTON_WIDTH + Constants.START_BUTTON_BORDER, Constants.START_BUTTON_HEIGHT + Constants.START_BUTTON_BORDER);
        start_btn.setBackground(Color.BLACK);
        start_btn.addActionListener(this);
    }

    public void initChoosePlayerLabel() {
        ImageIcon choose_player = new ImageIcon("buttons/choose_player.png");
        Image resized = choose_player.getImage().getScaledInstance(Constants.CHOOSE_PLAYER_LABEL_WIDTH, Constants.CHOOSE_PLAYER_LABEL_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        choose_player_label = new JLabel(new ImageIcon(resized));
        choose_player_label.setSize(Constants.CHOOSE_PLAYER_LABEL_WIDTH, Constants.CHOOSE_PLAYER_LABEL_HEIGHT);
    }


/******************************************PRIVATE-METHODES****************************************/
    private void unsetAllBorders() {
        player_1_btn.setBorder(null);
        player_2_btn.setBorder(null);
        player_3_btn.setBorder(null);
        player_4_btn.setBorder(null);
    }
}




