package Players;

public class PointCalculator {
    public static String getResult(Player player, Bot bot) {

        Points player_points = player.countPoints();
        Points bot_points = bot.countPoints();

        String mala = "";
        String velika = "";
        String winner = "";

        int total_points_player = 0;
        int total_points_bot = 0;

        if(player_points.mala == true) {
            total_points_player++;
            mala = "PLAYER";
        } else {
            total_points_bot++;
            mala = "BOT";
        }
        
        if(player_points.velika == true) {
            total_points_player++;
            velika = "PLAYER";
        }
        else {
            total_points_bot++;
            velika = "BOT";
        }

        if(player_points.num_cards > bot_points.num_cards) {
            total_points_player += 2;

        } else if (player_points.num_cards < bot_points.num_cards) {
            total_points_bot += 2;
        }

        if(player_points.num_clubs > bot_points.num_clubs) {
            total_points_player++;
        } else if (player_points.num_clubs < bot_points.num_clubs) {
            total_points_bot++;
        }

        if(total_points_player > total_points_bot) {
            winner = "PLAYER";
        }
        else if (total_points_bot > total_points_player) {
            winner = "BOT";
        }
        else {
            winner = "TIE";
        }

        String result =
        "<html>" +
        "<pre style=font-size:15px> NUM CARDS PLAYER: " + player_points.num_cards + "<br/>"  + 
        "<pre style=font-size:15px> NUM CARDS BOT:    " + bot_points.num_cards    + "<br/>"  + 
        "<pre style=font-size:15px> NUM CLUBS PLAYER: " + player_points.num_clubs + "<br/>"  + 
        "<pre style=font-size:15px> NUM CLUBS BOT:    " + bot_points.num_clubs + "<br/>"  + 
        "<pre style=font-size:15px> VELIKA:           " + velika + "<br/>" +
        "<pre style=font-size:15px> MALA:             " + mala + "<br/>"  + "<br/>"  +
        "<pre style=font-size:15px> WINNER:           " + winner + "<br/>"  +
        "<html/>";

        return result;
    }
}
