package com.example.TheTastingRoom;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;
import org.w3c.dom.Text;


public class StartGame extends Activity {
    private ViewFlipper vf;
    public int money = 100;
    public int[] btl_qty = null;
    public int[] wineprice = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameframes);
        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        btl_qty=getResources().getIntArray(R.array.stock);
        wineprice = getResources().getIntArray(R.array.wineprices);
    }

    public void flipit(View view) {
     vf.showNext();
    }

    public void cww_click(View view) {
        if (money >= wineprice[0]) {
        money = money -5;
        TextView tv;
        tv = (TextView) findViewById(R.id.moneytxt);
        tv.setText("$"+Integer.toString(money));
        btl_qty[0]=btl_qty[0]+1;
        tv = (TextView) findViewById(R.id.cww_qty_tv);
        tv.setText(Integer.toString(btl_qty[0])); }
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
