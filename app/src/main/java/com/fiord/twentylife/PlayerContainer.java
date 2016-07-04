package com.fiord.twentylife;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjord on 30.05.2016.
 */
public class PlayerContainer {




    private static List<Player> players = new ArrayList();



    public PlayerContainer(Activity _activity){


    }



    public void newPlayer(Player _player) {
        players.add(_player);


    }

    public void newPlayer(TextView textLife, Button up, Button down, int StartingLife){
        //// TODO: 04.07.2016
        players.add(new Player(textLife,up,down,StartingLife));
    }







    public void setLife(int life){
        for(int i = 0; i < players.size(); i++) {
            players.get(i).setLife(life);
        }
    }

    public void restartLife(){
        for(int i = 0; i < players.size(); i++) {

            players.get(i).setLife(MainActivity.startingLife);
        }
    }

    public static String getCurrentSituation(){
        String gcs=" ";
        for(int i = 0; i < players.size(); i++) {

            gcs+="  "+players.get(i).getLife();
        }
        return(gcs);
    }
}