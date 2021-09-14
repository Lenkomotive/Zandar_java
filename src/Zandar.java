
public class Zandar {

    static Frame frame;
    static StartScreen start_screen;
    static Board board;
    
    static Bot bot;
    static Player player;
    static Deck deck;

    static int num_cards = Constants.TOTAL_CARDS;
    public static void main(String[] args) throws Exception {
        frame = new Frame();
        showStartScreen();
        showBoard();
        initDeck();
        initPlayer();
        initBot();


        while(num_cards != 0) {

        }
    


        bot.num_cards_label.setText("69");
        player.num_cards_label.setText("31");

    }

    static void showStartScreen() throws InterruptedException {
        start_screen = new StartScreen();
        frame.add(start_screen);
        start_screen.initPlayers();
        start_screen.initStartButton();

        start_screen.add(start_screen.start_btn);
        start_screen.add(start_screen.player_1_btn);
        start_screen.add(start_screen.player_2_btn);
        start_screen.add(start_screen.player_3_btn);
        start_screen.add(start_screen.player_4_btn);

        start_screen.start_btn.setLocation(Constants.START_BUTTON_X, Constants.START_BUTTON_Y);
        start_screen.player_1_btn.setLocation(Constants.PLAYER_1_BUTTON_X, Constants.PLAYER_BUTTON_Y);
        start_screen.player_2_btn.setLocation(Constants.PLAYER_2_BUTTON_X, Constants.PLAYER_BUTTON_Y);
        start_screen.player_3_btn.setLocation(Constants.PLAYER_3_BUTTON_X, Constants.PLAYER_BUTTON_Y);
        start_screen.player_4_btn.setLocation(Constants.PLAYER_4_BUTTON_X, Constants.PLAYER_BUTTON_Y);

        while(start_screen.getStartGame() != true) {
            Thread.sleep(100);
        }
        start_screen.setVisible(false);
    }

    static void showBoard() {
        board = new Board();
        frame.add(board);
    }

    static void initPlayer() {
        player = new Player(start_screen.getChoosenPlayer());
        board.add(player);
        player.setLocation(Constants.PLAYER_IMAGE_POSITION_X, Constants.PLAYER_IMAGE_POSITION_Y);
        board.add(player.num_cards_label);
        player.num_cards_label.setLocation(Constants.PLAYER_NUM_CARD_POSITION_X, Constants.PLAYER_NUM_CARD_POSITION_Y);
        board.add(player.deck_backside_label);
        player.deck_backside_label.setLocation(Constants.PLAYER_DECK_POSITION_X, Constants.PLAYER_DECK_POSITION_Y);
    }

    static void initBot() {
        bot = new Bot();
        board.add(bot);
        bot.setLocation(Constants.BOT_IMAGE_POSITION_X, Constants.BOT_IMAGE_POSITION_Y);
        board.add(bot.num_cards_label);
        bot.num_cards_label.setLocation(Constants.BOT_NUM_CARD_POSITION_X, Constants.BOT_NUM_CARD_POSITION_Y);
        board.add(bot.deck_backside_label);
        bot.deck_backside_label.setLocation(Constants.BOT_DECK_POSITION_X, Constants.BOT_DECK_POSITION_Y);
    }

    static void initDeck() {
        deck = new Deck();
        board.add(deck.num_cards_label);
        deck.num_cards_label.setLocation(Constants.DECK_NUM_CARD_POSITION_X, Constants.DECK_NUM_CARD_POSITION_Y);
        board.add(deck.deck_backside_label);
        deck.deck_backside_label.setLocation(Constants.DECK_POSITION_X, Constants.DECK_POSITION_Y);
    }

    static void dealCards() {
        // for(int i = 0; i < 8; i++) {
        //     Card tmp = deck.getCard();
        //     num_cards--;
        //     deck.num_cards_label.setText(Integer.toString(num_cards));
        // }
    }
}


        // int schieben = 0;
        // for(Card c: deck.getCards()) {
        //     board.add(c);
        //     c.setVisible(true);
        //     c.setPosition(500 + schieben, 400);
        //     schieben +=15;
        //     Thread.sleep(150);
        //     c.setVisible(false);
        // }