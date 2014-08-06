package com.example.TheTastingRoom;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
import org.w3c.dom.Text;

import java.util.Random;


public class StartGame extends Activity {
    private ViewFlipper vf;
    public int money = 100;
    public int[] btl_qty = null;
    public int[] wineprice = null;
    private Button invbutton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameframes);
        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        btl_qty=getResources().getIntArray(R.array.stock);
        wineprice = getResources().getIntArray(R.array.wineprices);
 //       SeasonForecast();
    }

    public void SeasonForecast() {
        int[] array;

        //Fisher-Yates Sort...sez the interwebs
        array = getResources().getIntArray(R.array.segmentorder);
        {
            int index;
            Random random = new Random();
            for (int i = array.length - 1; i > 0; i--)
            {
                index = random.nextInt(i + 1);
                if (index != i)
                {
                    array[index] ^= array[i];
                    array[i] ^= array[index];
                    array[index] ^= array[i];
                }
            }
        }

        TextView tv = (TextView) findViewById(R.id.segment1name);
        tv.setText(getResources().getStringArray(R.array.segments)[array[0]]);
        tv = (TextView) findViewById(R.id.segment2name);
        tv.setText(getResources().getStringArray(R.array.segments)[array[1]]);
        tv = (TextView) findViewById(R.id.segment3name);
        tv.setText(getResources().getStringArray(R.array.segments)[array[2]]);
    }

    //Buying wine functions

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

    //Buying wine functions call this to add inventory

    public void updatestock(int index, TextView wtv) {
        if (money >= wineprice[index]) {
            money = money - wineprice[index];
            TextView tv;
            tv = (TextView) findViewById(R.id.moneytxt);
            tv.setText("$"+Integer.toString(money));
            btl_qty[index]=btl_qty[index]+1;
            wtv.setText(Integer.toString(btl_qty[index])); }
    }

    //for flipping back and forth in forecast/stock phase

    public void backtowine (View view) {
        vf.setDisplayedChild(1);
    }

    public void backtoforecast (View view) {
        vf.setDisplayedChild(0);
    }

    //Kicks off the tasting room phase

    public void SeasonStart (View view) {
        invbutton = (Button) findViewById(R.id.invbutton);
        invbutton.setVisibility(View.GONE);
        vf.setDisplayedChild(2);
    }



    @Override
    public void onBackPressed() {
    }
}
