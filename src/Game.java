import java.util.Random;

public class Game extends Thread {
    static MyFrame frame = MyFrame.getInstance();
    static StartScreen start_screen = StartScreen.getInstance();
    static Board board;

    static Bot bot;
    static Player player;
    static Deck deck;
    static Log log = Log.getInstance();

    static ActivePlayer active_player = ActivePlayer.NONE;

    public static void main(String[] args) throws Exception {
        initStartScreen();
        initBoard();
        initPlayer();
        initDeck();
        initBot();

        log.initLog(player, bot, board, deck);
        Game log_thread = new Game();
        log_thread.start();

        active_player = ActivePlayer.BOT;
        dealPlayerCards();
        dealBoardCards();

        while (deck.cards.size() != 0) {
            for(int i = 0; i < 4; i++) {
                playerMove();
                Thread.sleep(600);
                botMove();
            }
            bot.initIndexList();
            dealPlayerCards();
        }
    }

    static void initStartScreen() throws InterruptedException {
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

        while (!start_screen.start_game) {
            Thread.sleep(50);
        }
        start_screen.setVisible(false);
    }

    static void initBoard() {
        board = Board.getInstance();
        frame.add(board);
        board.initPutBtn();
        board.initTakeBtn();
        board.initLogLabel();
        board.add(board.log);
        board.add(board.put_btn);
        board.add(board.take_btn);
        board.log.setLocation(Constants.LOG_X, Constants.LOG_Y);
        board.put_btn.setLocation(Constants.PUT_BUTTON_X, Constants.PUT_BUTTON_Y);
        board.take_btn.setLocation(Constants.TAKE_BUTTON_X, Constants.TAKE_BUTTON_Y);
    }

    static void initPlayer() {
        player = Player.getInstance(start_screen.getChoosenPlayer());
        board.add(player);
        board.add(player.deck_backside_label);
        player.setLocation(Constants.PLAYER_IMAGE_POSITION_X, Constants.PLAYER_IMAGE_POSITION_Y);
        player.deck_backside_label.setLocation(Constants.PLAYER_DECK_POSITION_X, Constants.PLAYER_DECK_POSITION_Y);
    }

    static void initBot() {
        bot = Bot.getInstance(board);
        board.add(bot);
        board.add(bot.deck_backside_label);
        bot.setLocation(Constants.BOT_IMAGE_POSITION_X, Constants.BOT_IMAGE_POSITION_Y);
        bot.deck_backside_label.setLocation(Constants.BOT_DECK_POSITION_X, Constants.BOT_DECK_POSITION_Y);
        for(int i = 0, X = Constants.CARDS_MOST_LEFT_POSITION; i < 4; i++, X += Constants.BOARD_CARD_DISTANCE) {
            board.add(bot.card_backside[i]);
            bot.card_backside[i].setLocation(X, Constants.BOT_CARD_Y);
            bot.card_backside[i].setVisible(false);
        }
    }

    static void initDeck() {
        deck = Deck.getInstance(player);
        board.add(deck.deck_backside_label);
        deck.deck_backside_label.setLocation(Constants.DECK_POSITION_X, Constants.DECK_POSITION_Y);
    }

    static void dealPlayerCards() throws InterruptedException {
        int X = Constants.CARDS_MOST_LEFT_POSITION;
        for (int num_card = 0, dealt_cards = 1, bot_card = 0; num_card < 8; num_card++, dealt_cards++) {
            Card card = deck.getCard();
            if (num_card % 2 == active_player.ordinal()) {
                card.type = CardType.PLAYER_CARD;
                player.cards_in_hand.add(card);
                board.add(card);
                card.setLocation(X, Constants.PLAYER_CARD_Y);
            } else {
                card.type = CardType.BOT_CARD;
                bot.cards_in_hand.add(card);
                bot.card_backside[bot_card].setVisible(true);
                bot_card++;
            }
            X = dealt_cards == 2 ? X += Constants.PLAYER_CARD_DISTANCE : X;
            dealt_cards = dealt_cards == 2 ? 0 : dealt_cards;
            Thread.sleep(Constants.SLEEP_BETWEEN_DEALING);
        }
    }

    static void dealBoardCards() throws InterruptedException {
        for (int num_card = 0; num_card < Constants.NUM_CARDS_ON_BOARD; num_card++) {
            Card card = deck.getCard();
            card.type = CardType.BOARD_CARD;
            board.cards.add(card);
            board.add(card);
            card.setLocation(board.getNextCardPlace());
            Thread.sleep(Constants.SLEEP_BETWEEN_DEALING);
        }
    }

    static void playerMove() throws InterruptedException {
        boolean move_successfull = false;
        while (!move_successfull) {
            waitUntilPlayerChooseCard();
            waitForMoveDecision();
            switch (board.current_move) {
                case PUT:
                    Card card_to_put = player.putCard();
                    board.cards.add(card_to_put);
                    board.add(card_to_put);
                    card_to_put.setLocation(board.getNextCardPlace());
                    card_to_put.setVisible(true);
                    move_successfull = true;
                    break;
                case TAKE:
                    Card active_card = player.getActiveCard();
                    int board_value = board.getActiveBoardCardValue();
                    switch (active_card.value) {
                        case 12: case 13:
                            if(active_card.value == board_value && board.getActiveBoardCards().size() == 1) {
                                player.addToCollectedCards(board.getActiveBoardCards());
                                board.removeCardsFromBoard();
                                move_successfull = true;
                            } else {
                                player.setCardInactive();
                                board.setCardsInactive();
                            }
                            break;
                        case 11:
                            active_card.setVisible(false);
                            player.cards_in_hand.remove(active_card);
                            player.collected_cards.add(active_card);
                            for(Card card: board.cards) {
                                card.setVisible(false);
                                board.board_map.put(card.getLocation(), Boolean.FALSE);
                                player.collected_cards.add(card);
                            }
                            board.cards.clear();
                            move_successfull = true;
                            break;
                        default:
                            if (board_value == active_card.value) {
                                player.addToCollectedCards(board.getActiveBoardCards());
                                board.removeCardsFromBoard();
                                move_successfull = true;
                            } else {
                                player.setCardInactive();
                                board.setCardsInactive();
                            }
                            break;
                    }
                    break;
                default:
                    break;
            }
            player.card_chosen = false;
            board.current_move = PlayMove.NONE;
        }
    }

    static void waitUntilPlayerChooseCard() throws InterruptedException {
        while (player.card_chosen == false) {
            player.chooseCard();
            board.current_move = PlayMove.NONE;
            Thread.sleep(50);
        }
    }

    static void waitForMoveDecision() throws InterruptedException {
        while (board.current_move == PlayMove.NONE) {
            Thread.sleep(50);
        }
    }

    static void botMove() {
        bot.hideCardBackside();
        Card active_card = bot.cards_in_hand.get(0);
        bot.cards_in_hand.remove(active_card);
        board.cards.add(active_card);
        board.add(active_card);
        active_card.type = CardType.BOARD_CARD;
        active_card.setVisible(true);
        active_card.setLocation(board.getNextCardPlace());

    }



    public void run() {
        while (true) {
            log.log();
            try {
                sleep(Constants.LOG_UPDATE_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
