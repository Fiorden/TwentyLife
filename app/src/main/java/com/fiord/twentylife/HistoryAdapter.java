package com.fiord.twentylife;

import android.app.Activity;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by fjord on 05.07.2016.
 */
public class HistoryAdapter {

    public Activity activity;
    static Handler handler;
    static ArrayAdapter<String> adapter;

    public HistoryAdapter(Activity activity) {
        this.activity = activity;

        final ListView history = (ListView) this.activity.findViewById(R.id.row);
        ArrayList<String> list = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this.activity, R.layout.record, list);
        adapter.add("Game started");
        adapter.add(PlayerContainer.getCurrentSituation());
        history.setAdapter(adapter);
        handler = new Handler();
    }

    public static void writeToHisCurrPoints(){
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 5000ms
                adapter.add(PlayerContainer.getCurrentSituation());
            }
        }, 5000);
    }

    public static void addText(String text){
        adapter.add(text);
    }

}
