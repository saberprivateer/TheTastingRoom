package com.example.TheTastingRoom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class StartGame extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments);

        Fragment frl = new Inventory();
        Fragment frr = new Forecast();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
 //       fragmentTransaction.replace(R.id.fragleft, frl);
        fragmentTransaction.replace(R.id.fragright, frr);
        fragmentTransaction.commit();
    }
}
