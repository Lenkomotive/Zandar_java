import java.awt.*;

public interface Constants {

    //!Game
    int TOTAL_CARDS = 52;
    int NUM_PLAYERS = 4;

    int NUM_CARDS_IN_HAND = 4;
    int NUM_CARDS_ON_BOARD = 4;
    int MAX_CARDS_PER_ROW = 10;

    Color GREEN = new Color(12,100,12);
    Color GOLD = new Color(200,170,0);
    Color RED = new Color(255,40,40);

    int SLEEP_BETWEEN_DEALING = 200;
    int SLEEP_BETWEEN_MOVE = 600;

    //!Player
    int PLAYER_IMAGE_WIDTH = 150;
    int PLAYER_IMAGE_HEIGHT = 180;

    int PLAYER_IMAGE_POSITION_X = 60;
    int PLAYER_IMAGE_POSITION_Y = 750;

    int PLAYER_DECK_WIDTH = 100;
    int PLAYER_DECK_HEIGHT = 120;

    int PLAYER_DECK_POSITION_X = 1000;
    int PLAYER_DECK_POSITION_Y = 800;

    int PLAYER_NUM_CARD_FONT_SIZE = 30;

    int PLAYER_NUM_CARD_POSITION_X = 1030;
    int PLAYER_NUM_CARD_POSITION_Y = 815;

    int PLAYER_NUM_CARD_WIDTH = 100;
    int PLAYER_NUM_CARD_HEIGHT = 50;

    //!Bot
    int BOT_IMAGE_WIDTH = 200;
    int BOT_IMAGE_HEIGHT = 170;

    int BOT_IMAGE_POSITION_X = 60;
    int BOT_IMAGE_POSITION_Y = 30;

    int BOT_DECK_POSITION_X = 1000;
    int BOT_DECK_POSITION_Y = 50;

    int BOT_NUM_CARD_FONT_SIZE = 30;

    int BOT_NUM_CARD_POSITION_X = 1030;
    int BOT_NUM_CARD_POSITION_Y = 95;

    int BOT_NUM_CARD_WIDTH = 100;
    int BOT_NUM_CARD_HEIGHT = 50;

    int BOT_SHOW_CARD_TO_PLAY_X = 790;
    int BOT_SHOW_CARD_TIME = 1000;

    //!Deck
    int DECK_DECK_WIDTH = 140;
    int DECK_DECK_HEIGHT = 180;

    int DECK_POSITION_X = 80;
    int DECK_POSITION_Y = 400;

    int DECK_NUM_CARD_FONT_SIZE = 40;

    int DECK_NUM_CARD_POSITION_X = 110;
    int DECK_NUM_CARD_POSITION_Y = 465;

    int DECK_NUM_CARD_WIDTH = 100;
    int DECK_NUM_CARD_HEIGHT = 50;

    //!StartScreen
    int CHARACTER_IMAGE_WIDTH = 200;
    int CHARACTER_IMAGE_HEIGHT = 250;

    int CHARACTER_IMAGE_BORDER = 15;

    int PLAYER_1_BUTTON_X = 290;
    int PLAYER_2_BUTTON_X = 530;
    int PLAYER_3_BUTTON_X = 770;
    int PLAYER_4_BUTTON_X = 1010;
    int PLAYER_BUTTON_Y = 300;

    int START_BUTTON_WIDTH = 250;
    int START_BUTTON_HEIGHT = 75;
    
    int START_BUTTON_BORDER = 20;

    int START_BUTTON_X = 625;
    int START_BUTTON_Y = 650;

    int CHOOSE_PLAYER_LABEL_X = 425;
    int CHOOSE_PLAYER_LABEL_Y = 120;

    int CHOOSE_PLAYER_LABEL_WIDTH = 650;
    int CHOOSE_PLAYER_LABEL_HEIGHT = 60;

    //!Board
    int CARDS_MOST_LEFT_POSITION = 350;
    int CARDS_MOST_RIGHT_POSITION = 900;

    int PLAYER_CARD_DISTANCE = 100;
    int BOARD_CARD_DISTANCE = 110;

    int PLAYER_CARD_Y = 800;
    int BOT_CARD_Y = 50;
    int BOARD_UPPER_CARD_Y = 350;
    int BOARD_LOWER_CARD_Y = 490;

    int PUT_BUTTON_WIDTH = 100;
    int PUT_BUTTON_HEIGHT = 60;
    int PUT_BUTTON_X = 1150;
    int PUT_BUTTON_Y = 830;

    int TAKE_BUTTON_WIDTH = 120;
    int TAKE_BUTTON_HEIGHT = 60;
    int TAKE_BUTTON_X = 1300;
    int TAKE_BUTTON_Y = 830;

    int LOG_WIDTH = 320;
    int LOG_HEIGHT = 400;
    int LOG_X = 1150;
    int LOG_Y = 50;
    int LOG_UPDATE_TIME = 200;

    int LOG_FONT_SIZE = 14;

    //!Card
    String PATH_CLUB = "_of_clubs";
    String PATH_DIAMOND = "_of_diamonds";
    String PATH_SPADE = "_of_spades";
    String PATH_HEART = "_of_hearts";
    String PATH = "cards/";
    String PATH_END = ".png";

    int CARD_WIDTH = 90;
    int CARD_HEIGHT = 120;

    int King = 13;
    int Queen = 12;
    int Zandar = 11;

    //!Frame
    int FRAME_WIDTH = 1500;
    int FRAME_HEIGHT = 1000;


}
