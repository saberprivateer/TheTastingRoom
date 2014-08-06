package com.example.TheTastingRoom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Random;


public class StartGame extends Activity {
    private ViewFlipper vf;
    public int money = 100;
    public int[] btl_qty = null;
    public int[] wineprice = null;
    public int[] tst_qty = null;
    private Button invbutton;
    public int service = 1;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameframes);
        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        btl_qty = getResources().getIntArray(R.array.stock);
        tst_qty = getResources().getIntArray(R.array.tastingstock);
        wineprice = getResources().getIntArray(R.array.wineprices);
        //       SeasonForecast();
        //       forecastinit();
    }

    //Temp init till I decide about how to randomize

    public void forecastinit() {
        TextView tv = (TextView) findViewById(R.id.segment1name);
        tv.setText(getResources().getStringArray(R.array.segments)[0]);
        tv = (TextView) findViewById(R.id.segment2name);
        tv.setText(getResources().getStringArray(R.array.segments)[1]);
        tv = (TextView) findViewById(R.id.segment3name);
        tv.setText(getResources().getStringArray(R.array.segments)[2]);
    }

    public void SeasonForecast() {
        int[] array;

        //Fisher-Yates Sort...sez the interwebs
        array = getResources().getIntArray(R.array.segmentorder);
        {
            int index;
            Random random = new Random();
            for (int i = array.length - 1; i > 0; i--) {
                index = random.nextInt(i + 1);
                if (index != i) {
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
        updatestock(0, tv);
    }

    public void eww_click(View view) {
        TextView tv = (TextView) findViewById(R.id.eww_qty_tv);
        updatestock(1, tv);
    }

    public void crw_click(View view) {
        TextView tv = (TextView) findViewById(R.id.crw_qty_tv);
        updatestock(2, tv);
    }

    public void erw_click(View view) {
        TextView tv = (TextView) findViewById(R.id.erw_qty_tv);
        updatestock(3, tv);
    }

    //Buying wine functions call this to add inventory

    public void updatestock(int index, TextView wtv) {
        if (money >= wineprice[index]) {
            money = money - wineprice[index];
            TextView tv;
            tv = (TextView) findViewById(R.id.moneytxt);
            tv.setText("$" + Integer.toString(money));
            btl_qty[index] = btl_qty[index] + 1;
            wtv.setText(Integer.toString(btl_qty[index]));
        }
    }

    //for flipping back and forth in forecast/stock phase

    public void backtowine(View view) {
        vf.setDisplayedChild(1);
    }

    public void backtoforecast(View view) {
        vf.setDisplayedChild(0);
    }

    //Kicks off the tasting room phase

    public void SeasonStart(View view) {
        invbutton = (Button) findViewById(R.id.invbutton);
        invbutton.setVisibility(View.GONE);
        vf.setDisplayedChild(2);
        TextView tv = (TextView) findViewById(R.id.segmentnametr);
        tv.setText(getResources().getStringArray(R.array.segments)[0]);

    }

    public void servewine(View view) {

        if (service > 4) {
            service = 1;
            Button bt = (Button) findViewById(R.id.invbutton);
            bt.setVisibility(View.VISIBLE);
            setrb(1, 0);
            setrb(2, 0);
            setrb(3, 0);
            setrb(4, 0);
            service = 1;
        }

        switch (view.getId()) {
            case R.id.progress_cww:
                if (tst_qty[0] > 0 || btl_qty[0] > 0) {
                    setrb(service, 1);
                    openbottle(0);
                }
                break;
            case R.id.progress_eww:
                if (tst_qty[1] > 0 || btl_qty[1] > 0) {
                    setrb(service, 2);
                    openbottle(1);
                }
                break;
            case R.id.progress_crw:
                if (tst_qty[2] > 0 || btl_qty[2] > 0) {
                    setrb(service, 3);
                    openbottle(2);
                }
                break;
            case R.id.progress_erw:
                if (tst_qty[3] > 0 || btl_qty[3] > 0) {
                    setrb(service, 4);
                    openbottle(3);
                }
                break;
        }
        service = service + 1;


    }

    public void setrb(int selectrb, int stars) {
        switch (selectrb) {
            case 1:
                RatingBar rb = (RatingBar) findViewById(R.id.w1rating);
                rb.setRating(stars);
                break;
            case 2:
                rb = (RatingBar) findViewById(R.id.w2rating);
                rb.setRating(stars);
                break;
            case 3:
                rb = (RatingBar) findViewById(R.id.w3rating);
                rb.setRating(stars);
                break;
            case 4:
                rb = (RatingBar) findViewById(R.id.w4rating);
                rb.setRating(stars);
                break;
        }

    }

    public void openbottle(int wine) {
        switch (wine) {
            case 0:
                ProgressBar pb = (ProgressBar) findViewById(R.id.progress_cww);
                if (tst_qty[wine] == 0 && btl_qty[wine] > 0) {
                    //open a new bottle
                    btl_qty[wine] = btl_qty[wine] - 1;
                    TextView tv = (TextView) findViewById(R.id.cww_qty_tv);
                    tv.setText(Integer.toString(btl_qty[wine]));
                    tst_qty[wine] = 100;
                    pb.setProgress(100);
                } else {
                    tst_qty[wine] = tst_qty[wine] - 25;
                    pb.setProgress(tst_qty[wine]);
                }
                break;
            case 1:
                pb = (ProgressBar) findViewById(R.id.progress_eww);
                if (tst_qty[wine] == 0 && btl_qty[wine] > 0) {
                    //open a new bottle
                    btl_qty[wine] = btl_qty[wine] - 1;
                    TextView tv = (TextView) findViewById(R.id.eww_qty_tv);
                    tv.setText(Integer.toString(btl_qty[wine]));
                    tst_qty[wine] = 100;
                    pb.setProgress(100);
                } else {
                    tst_qty[wine] = tst_qty[wine] - 25;
                    pb.setProgress(tst_qty[wine]);
                }
                break;
            case 2:
                pb = (ProgressBar) findViewById(R.id.progress_crw);
                if (tst_qty[wine] == 0 && btl_qty[wine] > 0) {
                    //open a new bottle
                    btl_qty[wine] = btl_qty[wine] - 1;
                    TextView tv = (TextView) findViewById(R.id.crw_qty_tv);
                    tv.setText(Integer.toString(btl_qty[wine]));
                    tst_qty[wine] = 100;
                    pb.setProgress(100);
                } else {
                    tst_qty[wine] = tst_qty[wine] - 25;
                    pb.setProgress(tst_qty[wine]);
                }
                break;
            case 3:
                pb = (ProgressBar) findViewById(R.id.progress_erw);
                if (tst_qty[wine] == 0 && btl_qty[wine] > 0) {
                    //open a new bottle
                    btl_qty[wine] = btl_qty[wine] - 1;
                    TextView tv = (TextView) findViewById(R.id.erw_qty_tv);
                    tv.setText(Integer.toString(btl_qty[wine]));
                    tst_qty[wine] = 100;
                    pb.setProgress(100);
                } else {
                    tst_qty[wine] = tst_qty[wine] - 25;
                    pb.setProgress(tst_qty[wine]);
                }

        }
    }

    @Override
    public void onBackPressed() {
    }
}
