package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShockOilChange extends AppCompatActivity {

    int profileIndex;
    Machine profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shock_oil_change);

        profileIndex = getIntent().getIntExtra("profileIndex", -1);
        profile = PreferencesMgr.getProfile(this, profileIndex);
    }

    public void chgShockOil(View v) {
        profile.changeShockOil();
        PreferencesMgr.saveProfile(this, profileIndex, profile);

        setResult(Activity.RESULT_OK);
        finish();
    }
}
