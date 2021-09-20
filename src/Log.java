public class Log {
    private static Log instance = null;
    private Player player;
    private Bot bot;
    private Deck deck;
    private Board board;

    public static Log getInstance() {
        if(instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void initLog(Player player, Bot bot, Board board, Deck deck) {
        this.player = player;
        this.bot = bot;
        this.board = board;
        this.deck = deck;
    }


    public void log() {
        String log_string =
            "<html>" + "<p style=font-size:20px> Log:</p>" +
            "<pre> NUM BOARD CARDS:                 " + board.cards.size() + "<br/>"  + 
            "<pre> NUM DECK CARDS:                  " + deck.cards.size() + "<br/>"  + "<br/>" +
            "<pre> SUM ACTIVE PLAYER CARDS:         " + player.getActiveCardValue() + "<br/>"  + 
            "<pre> SUM ACTIVE BOARD CARDS:          " + board.getActiveBoardCardValue() + "<br/>" +
            "<pre> NUM PLAYER CARDS COLLECTED:      " + player.collected_cards.size() + "<br/>"  +
            "<pre> NUM BOT CARDS COLLECTED:         " + bot.collected_cards.size() + "<br/>"  +
            "<pre> BOT CARDS: " + bot.getHand() + "<br/>"  +
            "<html/>";

            board.log.setText(log_string);
    }
}
