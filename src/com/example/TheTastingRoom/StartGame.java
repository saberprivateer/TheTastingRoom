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
        TextView tv = (TextView) findViewById(R.id.cww_qty_tv);
        updatestock(0,tv);
    }

    public void eww_click(View view) {
        TextView tv = (TextView) findViewById(R.id.eww_qty_tv);
        updatestock(1,tv);
    }

    public void crw_click(View view) {
        TextView tv = (TextView) findViewById(R.id.crw_qty_tv);
        updatestock(2,tv);
    }

    public void erw_click(View view) {
        TextView tv = (TextView) findViewById(R.id.erw_qty_tv);
        updatestock(3,tv);
    }

    public void updatestock(int index, TextView wtv) {
        if (money >= wineprice[index]) {
            money = money - wineprice[index];
            TextView tv;
            tv = (TextView) findViewById(R.id.moneytxt);
            tv.setText("$"+Integer.toString(money));
            btl_qty[index]=btl_qty[index]+1;
            wtv.setText(Integer.toString(btl_qty[index])); }
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
