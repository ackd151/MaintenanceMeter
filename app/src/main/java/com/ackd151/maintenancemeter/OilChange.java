package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;


public class OilChange extends AppCompatActivity {

    Machine profile;
    int profileIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_change);

        // Get profileIndex from intent extras
        profileIndex = getIntent().getIntExtra("profileIndex", -1);

        // Get profile from preferences
        profile = PreferencesMgr.getProfile(this, profileIndex);
    }

    public void completeOilChange(View v) {
        CheckBox filterCheckbox = findViewById(R.id.filterCheckbox);
        profile.resetEngineOil(profile.getCurrentHours(), filterCheckbox.isChecked());
        PreferencesMgr.saveProfile(this, profileIndex, profile);

        setResult(Activity.RESULT_OK);
        finish();
    }
}
