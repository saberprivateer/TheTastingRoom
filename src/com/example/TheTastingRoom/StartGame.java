package com.example.TheTastingRoom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Random;

public class StartGame extends Activity {
    private ViewFlipper vf;
    public int money = 100;
    public int[] btl_qty = {0, 0, 0, 0};
    public int[] wineprice = {0, 0, 0, 0};
    public int[] tst_qty = {0, 0, 0, 0};
    private Button invbutton;
    public int service = 1;
    public int guest = 1;
    public int guestqty = 0;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameframes);
        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        btl_qty = getResources().getIntArray(R.array.stock);
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
    //End of buying wine functions


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
    public void actionbutton(View view) {
        invbutton = (Button) findViewById(R.id.invbutton);
        invbutton.setVisibility(View.GONE);
        if (vf.getDisplayedChild() < 2) {
            vf.setDisplayedChild(2);
        }
        resetrating();
        guest = rollsegment();
        setguestpic(guest);
        if (guestqty == 3) {
            endthegame();
        }
        guestqty = guestqty + 1;
    }

    public void endthegame() {
        setContentView(R.layout.results);
        TextView tv = (TextView) findViewById(R.id.finalresult);
        tv.setText(getResources().getString(R.string.congratstext) + Integer.toString(money) + "!");
    }

    public void playagain(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Would you like to play again?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        recreate();
                    }
                }

    );
    AlertDialog alert = builder.create();
    alert.show();
}

    public void servewine(View view) {
        vf = (ViewFlipper) findViewById(R.id.viewFlipper);
        int guestrating;
        if (vf.getDisplayedChild() == 2 && service<5) {
            switch (view.getId()) {
                case R.id.progress_cww:
                    if (tst_qty[0] > 0 || btl_qty[0] > 0) {
                        guestrating = getguestrating(0);
                        setrb(service, guestrating);
                        openbottle(0);
                        setchance(guestrating);
                    }
                    service = service + 1;
                    break;
                case R.id.progress_eww:
                    if (tst_qty[1] > 0 || btl_qty[1] > 0) {
                        guestrating = getguestrating(1);
                        setrb(service, guestrating);
                        openbottle(1);
                        setchance(guestrating);
                    }
                    service = service + 1;
                    break;
                case R.id.progress_crw:
                    if (tst_qty[2] > 0 || btl_qty[2] > 0) {
                        guestrating = getguestrating(2);
                        setrb(service, guestrating);
                        openbottle(2);
                        setchance(guestrating);
                    }
                    service = service + 1;
                    break;
                case R.id.progress_erw:
                    if (tst_qty[3] > 0 || btl_qty[3] > 0) {
                        guestrating = getguestrating(3);
                        setrb(service, guestrating);
                        openbottle(3);
                        setchance(guestrating);
                    }
                    service = service + 1;
                    break;
            }
            if (service > 4) {
                guestbuybottle();
            }
        }
    }

    public void setrb(int selectrb, int stars) {
        RatingBar rb;
        switch (selectrb) {
            case 1:
                rb = (RatingBar) findViewById(R.id.w1rating);
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

    public void guestbuybottle() {
        ProgressBar bc = (ProgressBar) findViewById(R.id.bottlechance);
        Random rnd = new Random();
        int roll = rnd.nextInt(100);
        TextView tv = (TextView) findViewById(R.id.moneytxt);
        String resultmessage;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (roll < bc.getProgress()) {
            money = money + 60;
            resultmessage = "They bought a bottle!";
        } else {
            money = money + 20;
            resultmessage = "They didn't buy a bottle.";
        }
        final String buttonsez;
        if (guestqty ==3){
            buttonsez = "End Season";
        } else {buttonsez = "Next Guest";}

        builder.setMessage(resultmessage)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        invbutton = (Button) findViewById(R.id.invbutton);
                        invbutton.setText(buttonsez);
                        invbutton.setVisibility(View.VISIBLE);


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        tv.setText("$" + Integer.toString(money));
    }


    public void resetrating() {
        ProgressBar bc = (ProgressBar) findViewById(R.id.bottlechance);
        bc.setProgress(0);
//        Button bt = (Button) findViewById(R.id.invbutton);
//        bt.setVisibility(View.VISIBLE);
        setrb(1, 0);
        setrb(2, 0);
        setrb(3, 0);
        setrb(4, 0);
        service = 1;
    }

    //Opens a bottle of wine for tasting
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

    //Rolls for a new segment to enter the tasting room
    public int rollsegment() {
        int[] segdist;
        Random rnd = new Random();
        segdist = getResources().getIntArray(R.array.segmentdist);
        int roll = rnd.nextInt(100);
        if (roll <= segdist[0]) {
            return 0;
        }
        if (roll <= (segdist[0] + segdist[1])) {
            return 1;
        }
        return 2;
    }

    //Sets the guest picture after they've entered the tasting room
    public void setguestpic(int guest) {
        TextView tv = (TextView) findViewById(R.id.segmentnametr);
        tv.setText(getResources().getStringArray(R.array.segments)[guest]);
        ImageView iv = (ImageView) findViewById(R.id.segmentpictr);
        TypedArray imgs = getResources().obtainTypedArray(R.array.segmentimgs);
        iv.setBackgroundResource(imgs.getResourceId(guest, -1));
        imgs.recycle();
    }

    //Determines how much the guests like a bottle of wine
    public int getguestrating(int wine) {
        int rating;
        int[] pref = {5, 5, 5, 5};
        switch (guest) {
            case 0:
                pref = getResources().getIntArray(R.array.seg1pref);
                break;
            case 1:
                pref = getResources().getIntArray(R.array.seg2pref);
                break;
            case 2:
                pref = getResources().getIntArray(R.array.seg3pref);
                break;

        }

        Random rnd = new Random();
        rating = rnd.nextInt(5) + 1;
        if (rating > pref[wine]) {
            rating = pref[wine];
        }
        return rating;
    }

    //Updates the "chance to buy a bottle bar" based on the rating
    public void setchance(int guestrating) {
        int currentprogress;
        int nextrating;
        ProgressBar bc = (ProgressBar) findViewById(R.id.bottlechance);
        currentprogress = bc.getProgress();
        nextrating = guestrating * 5;
        bc.setProgress(nextrating + currentprogress);
    }

    @Override
    public void onBackPressed() {
    }
}
