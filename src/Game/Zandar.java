package Game;

import Card.*;
import Constants.Types.*;
import Constants.Constants;
import Frame.MyFrame;
import Frame.Panel.*;
import Players.*;

public class Zandar extends Thread {
    static MyFrame frame = MyFrame.getInstance();
    static StartScreen start_screen = StartScreen.getInstance();
    static Log log = Log.getInstance();
    static Board board = null;
    static Bot bot = null;
    static Player player = null;
    static Deck deck = null;
    static BotMove bot_move = null;

    static ActivePlayer active_player;
    static ActivePlayer last_to_take;

    public static void main(String[] args) throws Exception {
        initStartScreen();
        initBoard();
        initPlayer();
        initBot();
        initDeck();
        startLog();

        active_player = ActivePlayer.PLAYER;
        boolean first_round = true;
        while (deck.cards.size() != 0) {
            dealPlayerCards();
            if(deck.cards.size() == 0) {
                deck.deck_backside_label.setVisible(false);
            }
            if(first_round) {
                dealBoardCards();
                first_round = false;
            }
            for(int move = 0, current_player = active_player.ordinal(); move < 8; move++) {
                current_player = current_player == ActivePlayer.PLAYER.ordinal()? playerMove() : botMove();
                Thread.sleep(Constants.SLEEP_BETWEEN_MOVE);
            }
            active_player = active_player == ActivePlayer.BOT? ActivePlayer.PLAYER: ActivePlayer.BOT;
            bot.initIndexList();
        }

        switch (last_to_take) {
            case PLAYER:
                for(var card: board.cards) {
                    card.setVisible(false);
                    player.collected_cards.add(card);
                }
                break;
            case BOT:
                for(var card: board.cards) {
                    card.setVisible(false);
                    bot.collected_cards.add(card);
                }
        }

        calcPoints();
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
        waitUntilGameStart();
        start_screen.setVisible(false);
    }

    static void initBoard() {
        board = Board.getInstance();
        frame.add(board);
        board.initPutBtn();
        board.initTakeBtn();
        board.add(board.put_btn);
        board.add(board.take_btn);
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
        bot = Bot.getInstance();
        board.add(bot);
        board.add(bot.deck_backside_label);
        bot.setLocation(Constants.BOT_IMAGE_POSITION_X, Constants.BOT_IMAGE_POSITION_Y);
        bot.deck_backside_label.setLocation(Constants.BOT_DECK_POSITION_X, Constants.BOT_DECK_POSITION_Y);
        for(int card = 0, X = Constants.CARDS_MOST_LEFT_POSITION; card < Constants.NUM_CARDS_IN_HAND; card++, X += Constants.BOARD_CARD_DISTANCE) {
            board.add(bot.card_backside[card]);
            bot.card_backside[card].setLocation(X, Constants.BOT_CARD_Y);
            bot.card_backside[card].setVisible(false);
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

    static int playerMove() throws InterruptedException {
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
                    move_successfull = true;
                    break;
                case TAKE: //! player awähla, board awähla, player abwähla, take
                    Card active_card = player.getActiveCard();
                    int board_value = board.getActiveBoardCardValue();
                    switch (active_card.value) {
                        case Constants.Queen: case Constants.King:
                            if(active_card.value == board_value && board.getActiveBoardCards().size() == 1) {
                                player.addToCollectedCards(board.getActiveBoardCards());
                                board.removeCardsFromBoard();
                                move_successfull = true;
                            } else {
                                player.setCardInactive();
                                board.setCardsInactive();
                            }
                            break;
                        case Constants.Zandar:
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
                    last_to_take = ActivePlayer.PLAYER;
                    break;
                default:
                    break;
            }
            player.card_chosen = false;
            board.current_move = PlayMove.NONE;
        }
        return ActivePlayer.BOT.ordinal();
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

    static void waitUntilGameStart() throws InterruptedException {
        while (!start_screen.start_game) {
            Thread.sleep(50);
        }
    }
 
    static int botMove() throws InterruptedException{
        bot.hideCardBackside();
        bot_move = bot.getMove(board.cards);
        bot.cards_in_hand.remove(bot_move.card_in_hand);
        bot_move.card_in_hand.setVisible(true);
        board.add(bot_move.card_in_hand);
        showBotCard();

        switch (bot_move.move_type) {
            case TAKE:
                botTake();
                break;
            case PUT:
                botPut();
                break;
        }
        return ActivePlayer.PLAYER.ordinal();
    }

    public static void botTake() {
        bot_move.card_in_hand.setVisible(false);
        bot.collected_cards.add(bot_move.card_in_hand);
        for(Card card: bot_move.board_cards) {
            bot.collected_cards.add(card);
            board.cards.remove(card);
            board.board_map.put(card.getLocation(), Boolean.FALSE);
            card.setVisible(false);
        }
        last_to_take = ActivePlayer.BOT;
    }

    public static void botPut() {
        board.cards.add(bot_move.card_in_hand);
        bot_move.card_in_hand.type = CardType.BOARD_CARD;
        bot_move.card_in_hand.setLocation(board.getNextCardPlace());
    }

    public static void showBotCard() throws InterruptedException {
        bot_move.card_in_hand.setLocation(Constants.BOT_SHOW_CARD_TO_PLAY_X, Constants.BOT_CARD_Y);
        Thread.sleep(Constants.BOT_SHOW_CARD_TIME);
    }

    public static void startLog() {
        board.initLogLabel();
        board.add(board.log);
        board.log.setLocation(Constants.LOG_X, Constants.LOG_Y);
        log.initLog(player, bot, board, deck);
        Zandar log_thread = new Zandar();
        log_thread.start();
    }

    public static void calcPoints() {
        player.countPoints();
        bot.countPoints();

        int total_points_player = 0;
        int total_points_bot = 0;

        if(player.collected_cards.size() > bot.collected_cards.size()) {
            total_points_player += 2;
        } else if (player.collected_cards.size() < bot.collected_cards.size()) {
            total_points_bot += 2;
        }

        if(player.num_clubs > bot.num_clubs) {
            total_points_player++;
        } else if (player.num_clubs < bot.num_clubs) {
            total_points_bot++;
        }
        total_points_player += player.special_card_points;
        total_points_bot += bot.special_card_points;

        System.out.println("PLAYER: " + total_points_player + " BOT: " + total_points_bot);
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
