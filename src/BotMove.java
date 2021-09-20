import java.util.ArrayList;

enum MoveType {TAKE, PUT};

public class BotMove {
    public Card card_in_hand;
    public ArrayList<Card> board_cards = new ArrayList<Card>();
    public MoveType move;
}
