package Players;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import Card.*;
import Constants.Types.*;
import Constants.*;

public class Player extends JLabel{
    private static Player instance = null;
    public JLabel deck_backside_label;
    public ArrayList<Card> cards_in_hand = new ArrayList<Card>();
    public ArrayList<Card> collected_cards = new ArrayList<Card>();
    public boolean card_chosen = false;
    public int num_clubs = 0;
    public int special_card_points = 0;

    private Player(PlayerType player) {
        initPlayerImage(player);
        initDeckLabels();
    }

    public static Player getInstance(PlayerType player) {
        if(instance == null) {
            instance = new Player(player);
        }
        return instance;
    }

    public void chooseCard() {
        for(Card card: cards_in_hand) {
            if(card.state == CardState.ACTIVE_PLAYER_CARD) {
                card_chosen = true;
                break;
            }
        }
    }

    public Card putCard() {
        Card card_to_put = getActiveCard();
        card_to_put.state = CardState.INACTIVE;
        card_to_put.type = CardType.BOARD_CARD;
        card_to_put.setBorder(null);
        cards_in_hand.remove(card_to_put);
        return card_to_put;
    }

    public int getActivePlayerCardValue() {
        Card active_card = getActiveCard();
        if(active_card != null) {
            return active_card.value;
        }
        return 0;
    }

    public void setCardInactive() {
        Card active_card = getActiveCard();
        if(active_card != null) {
            active_card.setPlayerCardInactive();
        }
    }

    public void addToCollectedCards(ArrayList<Card> board_cards) {
        for(Card card: board_cards) {
            collected_cards.add(card);
        }
        Card active_card = getActiveCard();
        active_card.setVisible(false);
        cards_in_hand.remove(active_card);
        collected_cards.add(active_card);
    }

    public int getActiveCardValue() {
        if(getActiveCard() != null) {
            return getActiveCard().value;
        }
        return 0;
    }

    public Card getActiveCard() {
        Card active_card = null;
        for(Card card: cards_in_hand) {
            if(card.state == CardState.ACTIVE_PLAYER_CARD) {
                active_card = card;
            }
        }
        return active_card;
    }

    public void countPoints() {
        for(Card card: collected_cards) {
            if((card.value == 10 && card.suit == CardSuit.DIAMOND) || (card.value == 2 && card.suit == CardSuit.CLUB)) {
                special_card_points++;
            }
            if(card.suit == CardSuit.CLUB) {
                num_clubs++;
            }
        }
    }

    private void initPlayerImage(PlayerType player) {
        ImageIcon image = new ImageIcon("players/player" + player.ordinal() + ".png");
        Image resized = image.getImage().getScaledInstance(Constants.PLAYER_IMAGE_WIDTH, Constants.PLAYER_IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        this.setIcon(image);
        this.setSize(Constants.PLAYER_IMAGE_WIDTH, Constants.PLAYER_IMAGE_HEIGHT);
    }

    private void initDeckLabels() {
        deck_backside_label = new JLabel();
        ImageIcon image = new ImageIcon("cards/backside_deck.png");
        Image resized = image.getImage().getScaledInstance(Constants.PLAYER_DECK_WIDTH, Constants.PLAYER_DECK_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        deck_backside_label.setIcon(image);
        deck_backside_label.setSize(Constants.PLAYER_DECK_WIDTH, Constants.PLAYER_DECK_HEIGHT);
    }
}

