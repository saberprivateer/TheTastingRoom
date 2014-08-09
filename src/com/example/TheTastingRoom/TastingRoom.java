package com.example.TheTastingRoom;

import android.app.Activity;

import java.util.Random;

/**
 * Created by dkamerling on 7/30/2014.
 */
public class TastingRoom extends Activity {
    public int[] segdist = null;

    public int rollsegment() {
        Random rnd = new Random();
        segdist = getResources().getIntArray(R.array.segmentdist);
        int roll = rnd.nextInt(100);
        if (roll<=segdist[0]) {return 0;}
        if (roll<=(segdist[0]+segdist[1])) {return 1;}
        return 2;
    }
}
