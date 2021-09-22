package Players;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.border.*;

import Card.Card;
import Constants.Types.*;
import Constants.Constants;

public class Bot extends JLabel{
    private static Bot instance = null;
    public JLabel deck_backside_label;
    public JLabel card_backside[] = new JLabel[4];
    public ArrayList<Integer> card_backside_index = new ArrayList<Integer>();
    public ArrayList<Card> cards_in_hand = new ArrayList<Card>();
    public ArrayList<Card> collected_cards = new ArrayList<Card>();
    private ArrayList<Card> current_board_cards = null;
    public Border board_card_border = new LineBorder(Constants.GOLD, 5);
    private List<Integer> board_cards = new ArrayList<Integer>();

    private Bot() {
        ImageIcon image = new ImageIcon("players/chad.png");
        Image resized = image.getImage().getScaledInstance(Constants.BOT_IMAGE_WIDTH, Constants.BOT_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.BOT_IMAGE_WIDTH, Constants.BOT_IMAGE_HEIGHT);
        initDeckLabels();
        initIndexList();
    }

    public static Bot getInstance() {
        if(instance == null) {
            instance = new Bot();
        }
        return instance;
    }

    public void hideCardBackside() {
        Random rand = new Random();
        int list_index = rand.nextInt(card_backside_index.size());
        int card_index = card_backside_index.get(list_index);
        card_backside_index.remove(list_index);
        card_backside[card_index].setVisible(false);
    }

    public void initIndexList() {
        for(int i = 0; i < 4; i++) {
            card_backside_index.add(i);
        }
    }

    public String getHand() {
        String hand = "";
        for(Card card : cards_in_hand) {
            hand += Integer.toString(card.value) + " ";
        }
        return hand;
    }

    public BotMove getMove(ArrayList<Card> board_cards) {
        current_board_cards = board_cards;
        BotMove move = null;

        findPotentionBoardCards();

        move = tryTakeThreeCards();
        if(move != null) {
            return move;
        }

        move = tryTakeTwoCards();
        if(move != null) {
            return move;
        }

        move = imaliZandar(); 
        if(move != null) {
            return move;
        }

        move = tryTakeOneCard();
        if(move != null) {
            return move;
        }

        move = new BotMove();
        move.card_in_hand = findLowestValue();
        move.move_type = MoveType.PUT;
        return move;
    }

    public Points countPoints() {
        Points points = new Points();
        for(Card card: collected_cards) {
            if (card.value == 10 && card.suit == CardSuit.DIAMOND) {
                points.velika = true;
            }
            if (card.value == 2 && card.suit == CardSuit.CLUB) {
                points.mala = true;
            }
            if (card.suit == CardSuit.CLUB) {
                points.num_clubs++;
            }
        }
        points.num_cards = collected_cards.size();
        return points;
    }

    private void initDeckLabels() {
        deck_backside_label = new JLabel();
        ImageIcon image = new ImageIcon("cards/backside_deck.png");
        Image resized = image.getImage().getScaledInstance(Constants.PLAYER_DECK_WIDTH, Constants.PLAYER_DECK_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        deck_backside_label.setIcon(image);
        deck_backside_label.setSize(Constants.PLAYER_DECK_WIDTH, Constants.PLAYER_DECK_HEIGHT);

        for(int i = 0; i < 4; i++) {
            card_backside[i] = new JLabel();
            image = new ImageIcon("cards/backside.png");
            resized = image.getImage().getScaledInstance(Constants.CARD_WIDTH, Constants.CARD_HEIGHT, java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(resized);
            card_backside[i].setIcon(image);
            card_backside[i].setSize(Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        }
    }

    private Card findLowestValue() {
        Card lowest = null;
        int lowest_value = 15;
        for(Card card: cards_in_hand) {
            if(card.value < lowest_value) {
                lowest = card;
                lowest_value = card.value;
            }
        }
        return lowest;
    }

    private void findPotentionBoardCards() {
        board_cards.clear();
        for(Card card: current_board_cards) {
            if(card.value < 10) {
                board_cards.add((Integer)card.value);
            }
        }
    }

    private BotMove tryTakeThreeCards() {
        if(current_board_cards.size() < 3) {
            return null;
        }

        for(Card card_in_hand: cards_in_hand) {
            if(card_in_hand.value > 10) {
                continue;
            }
            int limit_for_first = card_in_hand.value / 3;
            int limit_for_second = card_in_hand.value % 2 == 0? card_in_hand.value / 2 - 1: card_in_hand.value / 2;
    
            for(Integer first = 1; first <= limit_for_first; first++) {
                if(board_cards.contains(first)) {
                    List<Integer> sublist_1 = new ArrayList<>(board_cards);
                    sublist_1.remove(first);
                    for(Integer second = first; second <= limit_for_second; second++) {
                        if(sublist_1.contains(second)) {
                            List<Integer> sublist_2 = new ArrayList<>(sublist_1);
                            sublist_2.remove(second);
                            Integer third = card_in_hand.value - (first + second);
                            if(sublist_2.contains(third)) {
                                return createMove(card_in_hand, Arrays.asList(first, second, third));
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    private BotMove tryTakeTwoCards() {
        if(current_board_cards.size() < 2) {
            return null;
        }

        for(Card card_in_hand: cards_in_hand) {
            if(card_in_hand.value > 10) {
                continue;
            }
            for(Integer first = 1; first <= card_in_hand.value / 2; first++) {
                if(board_cards.contains(first)) {
                    List<Integer> board_cards_sublist = new ArrayList<>(board_cards);
                    board_cards_sublist.remove(first);
                    Integer second = card_in_hand.value - first;
                    if(board_cards_sublist.contains(second)) {
                        return createMove(card_in_hand, Arrays.asList(first, second));
                    }
                }
            }
        }
        return null;
    }     

    private BotMove tryTakeOneCard() {
        BotMove move = null;
        for(Card card_in_hand: cards_in_hand) {
            for(Card card_on_board: current_board_cards) {
                if(card_in_hand.value == card_on_board.value) {
                    move = new BotMove();
                    move.card_in_hand = card_in_hand;
                    card_on_board.setBorder(board_card_border);
                    move.board_cards.add(card_on_board);
                    move.move_type = MoveType.TAKE;
                    return move;
                }
            }
        } 

        return move;
    }

    private BotMove imaliZandar() {
        BotMove move = null;
        for(Card card: cards_in_hand) {
            if(card.value == Constants.Zandar) {
                move = new BotMove();
                move.card_in_hand = card;
                move.board_cards = new ArrayList<Card>(current_board_cards);
                move.move_type = MoveType.TAKE;
                break;
            }
        }
        return move;
    }

    private BotMove createMove(Card card_in_hand, List<Integer> cards_to_take) {
        BotMove move = new BotMove();
        move.card_in_hand = card_in_hand;
        move.move_type = MoveType.TAKE;

        ArrayList<Card> sublist = new ArrayList<Card>(current_board_cards);
        for(var value: cards_to_take) {
            Card to_remove = null;
            for(Card board_card: sublist) {
                if(board_card.value == value) {
                    move.board_cards.add(board_card);
                    board_card.setBorder(board_card_border);
                    to_remove = board_card;
                    break;
                }
            }
            if(to_remove != null) {
                sublist.remove(to_remove);
            }
        }

        return move;
    }

}
