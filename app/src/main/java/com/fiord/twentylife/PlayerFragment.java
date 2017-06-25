package com.fiord.twentylife;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PlayerFragment extends Fragment implements RotationGestureDetector.OnRotationGestureListener {

    TextView hp;
    LinearLayout buttons;
    Button up, down;
    int life = 20;
    View view;
    private float mCurrentDegree = 0.f;
    Boolean isRotated = false;


    private RotationGestureDetector mRotationDetector;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_player, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hp = (TextView) getView().findViewById(R.id.player);
        up = (Button) getView().findViewById(R.id.buttonUp);
        down = (Button) getView().findViewById(R.id.buttonDown);
        buttons = (LinearLayout) getView().findViewById(R.id.buttons);



        hp.setText(String.valueOf(life));

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRotated)
                    hp.setText(String.valueOf(++life));
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRotated)
                    hp.setText(String.valueOf(--life));
            }
        });

        mRotationDetector = new RotationGestureDetector(this);





        hp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MotionEvent me = MotionEvent.obtain(event);
                mRotationDetector.onTouchEvent(me);
                switch (event.getActionMasked()) {
                    case (MotionEvent.ACTION_DOWN):
                        isRotated = false;
                        break;
                    case (MotionEvent.ACTION_POINTER_DOWN):
                        isRotated = true;
                        break;
                }

                buttons.dispatchTouchEvent(event);
                return true;
            }
        });
    }


    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {
        float angle = rotationDetector.getAngle();
        //player.setText(Float.toString(angle));
        if (angle > 45)
            getView().setRotation(mCurrentDegree -= 90);
        if (angle < -45)
            getView().setRotation(mCurrentDegree += 90);
    }

}
