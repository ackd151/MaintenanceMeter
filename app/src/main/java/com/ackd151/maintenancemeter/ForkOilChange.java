package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ForkOilChange extends AppCompatActivity {

    int profileIndex;
    Machine profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fork_oil_change);

        // Get profileIndex from intent extras
        profileIndex = getIntent().getIntExtra("profileIndex", -1);

        // Get profile from preferences
        profile = PreferencesMgr.getProfile(this, profileIndex);
    }

    public void chgForkOil(View v) {
        profile.changeForkOil();
        PreferencesMgr.saveProfile(this, profileIndex, profile);

        setResult(Activity.RESULT_OK);
        finish();
    }
}
