package ohtu;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        java.util.Date date = new java.util.Date();

        System.out.println("PLAYERS from FIN: " + date);
        System.out.println();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        ArrayList<Player> finnishPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                finnishPlayers.add(player);
            }
        }
        Collections.sort(finnishPlayers);
        for(Player player : finnishPlayers){
            System.out.println(player);
        }

    }


}
