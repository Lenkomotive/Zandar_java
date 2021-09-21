package Constants;

public class Types {
    public enum CardSuit     {CLUB, DIAMOND, HEART, SPADE};
    public enum CardState    {INACTIVE, ACTIVE_PLAYER_CARD, ACTIVE_BOARD_CARD};
    public enum CardType     {PLAYER_CARD, BOARD_CARD, BOT_CARD};
    public enum MoveType     {TAKE, PUT};
    public enum PlayMove     {PUT, TAKE, NONE};
    public enum ActivePlayer {PLAYER, BOT};
    public enum PlayerType   {NONE, PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4};
}
