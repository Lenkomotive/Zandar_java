package Frame;
import javax.swing.*;

import Constants.Constants;

public class MyFrame extends JFrame{
    private static MyFrame instance = null;
    
    private MyFrame() {
        this.setTitle("P E P E - Ž A N D A R");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        this.setVisible(true);
    }

    public static MyFrame getInstance() {
        if(instance == null) {
            instance = new MyFrame();
        }
        return instance;
    }


}
