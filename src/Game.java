
public class Game extends Thread{
    static Frame frame;
    static StartScreen start_screen;
    static Board board;
    
    static Bot bot;
    static Player player;
    static Deck deck;

    static int current_X_board = Constants.CARDS_MOST_LEFT_POSITION;
    static int current_Y_board = Constants.BOARD_UPPER_CARD_Y;

    static ActivePlayer active_player = ActivePlayer.NONE;
    public static void main(String[] args) throws Exception {
        frame = new Frame();
        showStartScreen();
        showBoard();
        active_player = ActivePlayer.PLAYER;

        Game log = new Game();
        log.start();
        
        dealBoardCards();
        
        while(deck.cards.size() != 0) {
            dealPlayerCards();
            executeAction();


        }
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
        board.initButtons();
        initDeck();
        initPlayer();
        initBot();

        board.add(board.log);
        board.add(board.put_btn);
        board.add(board.take_btn);

        board.log.setLocation(1150, 50);
        board.put_btn.setLocation(1150,830);
        board.take_btn.setLocation(1300,830);
    }

    static void initPlayer() {
        player = new Player(start_screen.getChoosenPlayer());
        board.add(player);
        board.add(player.deck_backside_label);
        player.setLocation(Constants.PLAYER_IMAGE_POSITION_X, Constants.PLAYER_IMAGE_POSITION_Y);
        player.deck_backside_label.setLocation(Constants.PLAYER_DECK_POSITION_X, Constants.PLAYER_DECK_POSITION_Y);
    }

    static void initBot() {
        bot = new Bot();
        board.add(bot);
        board.add(bot.deck_backside_label);
        bot.setLocation(Constants.BOT_IMAGE_POSITION_X, Constants.BOT_IMAGE_POSITION_Y);
        bot.deck_backside_label.setLocation(Constants.BOT_DECK_POSITION_X, Constants.BOT_DECK_POSITION_Y);
    }

    static void initDeck() {
        deck = new Deck();
        board.add(deck.deck_backside_label);
        deck.deck_backside_label.setLocation(Constants.DECK_POSITION_X, Constants.DECK_POSITION_Y);
    }

    static void dealPlayerCards() throws InterruptedException{

        int X = Constants.CARDS_MOST_LEFT_POSITION;

        for(int num_card = 0, dealt_cards = 1, bot_card = 0; num_card < 8; num_card++, dealt_cards++) {
            Card card = deck.getCard();
            if(num_card % 2 == active_player.ordinal()) {
                card.type = CardType.PLAYER_CARD;
                player.cards_in_hand.add(card);
                board.add(card);
                card.setLocation(X, Constants.PLAYER_CARD_Y);
            }
            else { 
                bot.cards_in_hand.add(card);
                board.add(bot.card_backside[bot_card]);
                bot.card_backside[bot_card].setLocation(X, Constants.BOT_CARD_Y);
                bot_card++;
            }
            X = dealt_cards == 2? X += Constants.PLAYER_CARD_DISTANCE : X; 
            dealt_cards = dealt_cards == 2 ? 0: dealt_cards;
            Thread.sleep(Constants.SLEEP_BETWEEN_DEALING);
        }

    }

    static void dealBoardCards() throws InterruptedException {
        for(int num_card = 0; num_card < Constants.NUM_CARDS_ON_BOARD; num_card++) {
            Card card = deck.getCard();
            card.type = CardType.BOARD_CARD;
            board.cards.add(card);
            board.add(card);
            card.setLocation(current_X_board, Constants.BOARD_UPPER_CARD_Y);
            current_X_board += Constants.BOARD_CARD_DISTANCE;
            Thread.sleep(Constants.SLEEP_BETWEEN_DEALING);
        }
    }

    static void executeAction() throws InterruptedException{

        boolean move_successfull = false;
        while(!move_successfull) {
            waitUntilPlayerChooseCard();
            waitForMoveDecision();
            switch (board.current_move) {
                case PUT:
                    Card card_to_put = player.putCard();
                    board.cards.add(card_to_put);
                    board.add(card_to_put);
                    card_to_put.setLocation(current_X_board, current_Y_board);
                    current_X_board = board.cards.size() % Constants.MAX_CARDS_PER_ROW == 0? Constants.CARDS_MOST_LEFT_POSITION : current_X_board + Constants.BOARD_CARD_DISTANCE;
                    current_Y_board = board.cards.size() >= Constants.MAX_CARDS_PER_ROW ? Constants.BOARD_LOWER_CARD_Y: Constants.BOARD_UPPER_CARD_Y; 
                    card_to_put.setVisible(true);
                    move_successfull = true;
                    break;
                case TAKE:
                    int board_value = board.getActiveBoardCardValue();
                    int player_value = player.getActivePlayerCardValue();
                    if(board_value == player_value) {

                        player.card_to_play = null;    
                        move_successfull = true; 
                    }
                    else {
                        player.setCardInactive();
                        board.setCardsInactive();
                        player.card_to_play = null;    
                    }
                    break;
                default:
                    break;                
            }
            board.current_move = PlayMove.NONE;
        }
    }

    static void waitUntilPlayerChooseCard() throws InterruptedException {
        while(player.card_to_play == null) {
            player.chooseCard();
            board.current_move = PlayMove.NONE;
            Thread.sleep(50);
        }
    }

    static void waitForMoveDecision() throws InterruptedException{
        while(board.current_move == PlayMove.NONE) {
            Thread.sleep(50);
        }
    }



    public void run() {
        while(true) {
            String log_string = "<html>" + "<p style=font-size:20px> Log:</p>" +
            "<pre> NUM BOARD CARDS:         " + board.cards.size() + "<br/>"  + 
            "<pre> NUM DECK CARDS:          " + deck.cards.size() + "<br/>"  + 
            "<pre> SUM PLAYER CARDS:        " + player.getActivePlayerCardValue() + "<br/>"  + 
            "<pre> SUM BOARD CARDS:         " + board.getActiveBoardCardValue() + "<br/>"
            + "<html/>";

            board.log.setText(log_string);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
