
public class Zandar {

    static Frame frame;
    static StartScreen start_screen;
    static Board board;
    
    static Bot bot;
    static Player player;
    static Deck deck;
    public static void main(String[] args) throws Exception {
        frame = new Frame();
        showStartScreen();
        showBoard();
        initDeck();
        initPlayer();
        initBot();

        dealCards();

        // while(num_cards != 0) {
        // }
    



    }

    static void showStartScreen() throws InterruptedException {
        start_screen = new StartScreen();
        frame.add(start_screen);
        start_screen.initPlayers();
        start_screen.initStartButton();
        start_screen.initChoosePlayerLabel();

        start_screen.add(start_screen.start_btn);
        start_screen.add(start_screen.player_1_btn);
        start_screen.add(start_screen.player_2_btn);
        start_screen.add(start_screen.player_3_btn);
        start_screen.add(start_screen.player_4_btn);
        start_screen.add(start_screen.choose_player_label);

        start_screen.start_btn.setLocation(Constants.START_BUTTON_X, Constants.START_BUTTON_Y);
        start_screen.player_1_btn.setLocation(Constants.PLAYER_1_BUTTON_X, Constants.PLAYER_BUTTON_Y);
        start_screen.player_2_btn.setLocation(Constants.PLAYER_2_BUTTON_X, Constants.PLAYER_BUTTON_Y);
        start_screen.player_3_btn.setLocation(Constants.PLAYER_3_BUTTON_X, Constants.PLAYER_BUTTON_Y);
        start_screen.player_4_btn.setLocation(Constants.PLAYER_4_BUTTON_X, Constants.PLAYER_BUTTON_Y);
        start_screen.choose_player_label.setLocation(Constants.CHOOSE_PLAYER_LABEL_X, Constants.CHOOSE_PLAYER_LABEL_Y);

        while( !start_screen.start_game) {
            Thread.sleep(50);
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

    static void dealCards() throws InterruptedException{

        int x = Constants.CARDS_MOST_LEFT_POSITION;
        for(int num_card = 0; num_card < 8; num_card++) {
            Card card = deck.getCard();
            deck.num_cards_label.setText(Integer.toString(deck.cards.size()));
            if(num_card % 2 == 0) { 
                player.cards.add(card);
                board.add(card);
                card.setLocation(x, Constants.PLAYER_CARD_Y);
            }
            else { 
                bot.cards.add(card);
                board.add(card);
                card.setLocation(x, Constants.BOT_CARD_Y);
                x += Constants.PLAYER_CARD_DISTANCE;
        }
            Thread.sleep(Constants.SLEEP_BETWEEN_DEALING);
        }

        x = Constants.CARDS_MOST_LEFT_POSITION;
        for(int i = 0; i < 8; i++) {
            Card card = deck.getCard();
            board.cards.add(card);
            board.add(card);
            deck.num_cards_label.setText(Integer.toString(deck.cards.size()));
            card.setLocation(x, Constants.BOARD_CARD_Y);
            x += Constants.BOARD_CARD_DISTANCE;
            Thread.sleep(Constants.SLEEP_BETWEEN_DEALING);
        }
        x = Constants.CARDS_MOST_LEFT_POSITION;
        for(int i = 0; i < 8; i++) {
            Card card = deck.getCard();
            board.cards.add(card);
            board.add(card);
            deck.num_cards_label.setText(Integer.toString(deck.cards.size()));
            card.setLocation(x, 500);
            x += Constants.BOARD_CARD_DISTANCE;
            Thread.sleep(Constants.SLEEP_BETWEEN_DEALING);
        }
    }
}
