package com.fiord.twentylife;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    static int startingLife;
    int poison;
    View view;

    /*
    @BindView(R.id.P1life) TextView P1Life;
    @BindView(R.id.P1Up) Button P1Up;
    @BindView(R.id.P1Down) Button P1Down;
    @BindView(R.id.P2life) TextView P2Life;
    @BindView(R.id.P2Up) Button P2Up;
    @BindView(R.id.P2Down) Button P2Down;
    */

    FragmentManager manager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);



        SharedPreferences preferences = getSharedPreferences("prefName", MODE_PRIVATE);
        final SharedPreferences.Editor edit= preferences.edit();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);




        startingLife = Integer.parseInt(preferences.getString("life","20"));
        poison = Integer.parseInt(preferences.getString("poison","0"));



        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setBehindWidthRes(R.dimen.trzysta);
        menu.setMenu(R.layout.menu);
        menu.setSecondaryMenu(R.layout.history);

        final SwipyRefreshLayout mSwipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);




        //Add new players
        /*final PlayerContainer pc = new PlayerContainer(this);
         pc.newPlayer(P1Life,P1Up,P1Down,startingLife);
         pc.newPlayer(new Player(P2Life,P2Up,P2Down,startingLife));
*/
        HistoryAdapter hAdapter = new HistoryAdapter(this);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.players, new PlayerFragment(), "P1");
            transaction.add(R.id.players, new PlayerFragment(), "P2");

            transaction.commit();
        }


        final Switch switch40 = (Switch) findViewById(R.id.switch40);
        Switch switchPoison = (Switch) findViewById(R.id.switchPoison);
        final SeekBar seekBarPoison = (SeekBar) findViewById(R.id.seekBarPoison);

        //Set button state
        if (startingLife==20)
        {switch40.setChecked(false);}
        else {switch40.setChecked(true);}

        if (poison==0)
        {switchPoison.setChecked(false);
            seekBarPoison.setVisibility(View.GONE);}
        else{switchPoison.setChecked(true);
            seekBarPoison.setVisibility(View.VISIBLE);}








        //Listener Swipe up, down
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                //pc.restartLife();
                mSwipyRefreshLayout.setRefreshing(false);
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    Toast.makeText(getApplicationContext(), "Won the game", Toast.LENGTH_SHORT).show();
                    HistoryAdapter.addText("Won the game");
                    HistoryAdapter.writeToHisCurrPoints();
                    seekBarPoison.setProgress(0);
                } else {
                    Toast.makeText(getApplicationContext(), "Lost the game", Toast.LENGTH_SHORT).show();
                    HistoryAdapter.addText("Lost the game");
                    HistoryAdapter.writeToHisCurrPoints();
                    seekBarPoison.setProgress(0);
                }
            }
        });

        switch40.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    edit.putString("life","40");
                    edit.commit();
                    //pc.setLife(startingLife = 40);
                    HistoryAdapter.addText("Changed settings");
                    HistoryAdapter.writeToHisCurrPoints();

                }else{
                    edit.putString("life","20");
                    edit.commit();
                    //pc.setLife(startingLife = 20);
                    HistoryAdapter.addText("Changed settings");
                    HistoryAdapter.writeToHisCurrPoints();
                    seekBarPoison.setProgress(0);
                }

            }
        });

        switchPoison.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    seekBarPoison.setVisibility(View.VISIBLE);
                    edit.putString("poison", "1");
                    edit.commit();
                    seekBarPoison.setProgress(0);


                } else {
                    seekBarPoison.setVisibility(View.GONE);
                    edit.putString("poison", "0");
                    edit.commit();
                    seekBarPoison.setProgress(0);

                }
            }
        });

        //Poison bar listener
        seekBarPoison.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        setPoison(i);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void setPoison(int poisony){
        TextView poison = (TextView) findViewById(R.id.poisonText);
        if (poisony == 0) poison.setText("");
        else poison.setText(""+poisony);
    }

    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

}

