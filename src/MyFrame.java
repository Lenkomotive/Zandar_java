import javax.swing.*;

public class MyFrame extends JFrame{
/******************************************MEMBER-VARIABLES****************************************/
    
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1500;
    
    private static MyFrame instance = null;
    
/******************************************CONSTRUCTORS********************************************/
    private MyFrame() {
        this.setTitle("P E P E - Ž A N D A R");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(HEIGHT, WIDTH);
        this.setVisible(true);
    }

/******************************************PUBLIC-METHODES*****************************************/
    public static MyFrame getInstance() {
        if(instance == null) {
            instance = new MyFrame();
        }
        return instance;
    }

/******************************************PRIVATE-METHODES****************************************/

}
