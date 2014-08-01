package com.example.TheTastingRoom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class StartGame extends Activity {
    private ViewFlipper vf;
    public int money = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments);
        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
    }

    public void flipit(View view) {
     vf.showNext();
    }

    public void cww_click(View view) {
        money = money -5;
        TextView tv;
        tv = (TextView) findViewById(R.id.moneytxt);
        tv.setText("$"+Integer.toString(money));
    }

    public void eww_click(View view) {
        money = money -10;
        TextView tv;
        tv = (TextView) findViewById(R.id.moneytxt);
        tv.setText("$"+Integer.toString(money));
    }

    public void crw_click(View view) {
        money = money -10;
        TextView tv;
        tv = (TextView) findViewById(R.id.moneytxt);
        tv.setText("$"+Integer.toString(money));
    }

    public void erw_click(View view) {
        money = money -15;
        TextView tv;
        tv = (TextView) findViewById(R.id.moneytxt);
        tv.setText("$"+Integer.toString(money));
    }

    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
            {vf.showNext();}
        }
        return false;
    }

    @Override
    public void onBackPressed() {
    }
}
