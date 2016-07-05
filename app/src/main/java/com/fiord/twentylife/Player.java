package com.fiord.twentylife;


        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

/**
 * Created by fjord on 25.05.2016.
 */
public class Player {

    private int life;
    TextView textLife;
    Button buttonUp;
    Button buttonDown;


    public Player(TextView textLife, Button up, Button down, int StartingLife){


        this.textLife = textLife;
        buttonUp = up;
        buttonDown = down;
        setLife(life = StartingLife);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifeUpdate(1);
                HistoryAdapter.writeToHisCurrPoints();
            }
        });

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifeUpdate(-1);
                HistoryAdapter.writeToHisCurrPoints();
            }
        });

        buttonUp.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                lifeUpdate( +5);
                HistoryAdapter.writeToHisCurrPoints();
                return true;
            }
        });

        buttonDown.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                lifeUpdate( +5);
                HistoryAdapter.writeToHisCurrPoints();
                return true;
            }
        });
    }


    public int getLife() {

        return life;
    }

    public int setLife(int i) {
        this.life = i;
        textLife.setText(Integer.toString(getLife()));
        return getLife();
    }
    public int lifeUpdate( int i){
        setLife(getLife()+i);
        return getLife();
    }





}