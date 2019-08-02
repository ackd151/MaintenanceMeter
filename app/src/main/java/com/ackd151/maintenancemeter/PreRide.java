package com.ackd151.maintenancemeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


public class PreRide extends AppCompatActivity {

    int profileIndex;
    Machine profile;
    String year, make, model;
    float hours;
    CheckBox airFilterCB, spokesCB, chainCB, brakesCB, throttleCB, chassisCB;
    TextView readyNotReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_ride);

        // Get checkbox views
        airFilterCB = findViewById(R.id.checkBoxAirFilter);
        spokesCB = findViewById(R.id.checkBoxSpokes);
        chainCB = findViewById(R.id.checkBoxChain);
        brakesCB = findViewById(R.id.checkBoxBrakes);
        throttleCB = findViewById(R.id.checkBoxThrottle);
        chassisCB = findViewById(R.id.checkBoxChassis);

        readyNotReady = findViewById(R.id.ready_not_ready);

        profileIndex = getIntent().getIntExtra("profileIndex", -1);
        screenBuilder();
    }

    public void screenBuilder() {
        profile = PreferencesMgr.getProfile(this, profileIndex);
        year = profile.getYear();
        make = profile.getMake();
        model = profile.getModel();
        hours = profile.getCurrentHours();

        TextView tYear = findViewById(R.id.yearTV);
        tYear.setText(year);
        TextView tMake = findViewById(R.id.makeTV);
        tMake.setText(make);
        TextView tModel = findViewById(R.id.modelTV);
        tModel.setText(model);

        String bikeImgFilename = profile.getImgFilename();
        ImageView bikeImage = findViewById(R.id.bikeIV);
        bikeImage.setImageResource(
                getResources().getIdentifier(bikeImgFilename, "drawable", getPackageName()));

        TextView currHours = findViewById(R.id.floatHrsTV);
        currHours.setText(String.valueOf(hours));

        // Set pre-ride checkboxes
        airFilterCB.setChecked(profile.getAirFilterCB());
        spokesCB.setChecked(profile.getSpokesCB());
        chainCB.setChecked(profile.getChainCB());
        brakesCB.setChecked(profile.getBrakesCB());
        throttleCB.setChecked(profile.getThrottleClutchCB());
        chassisCB.setChecked(profile.getChassisCB());
        setReadyNotReady(profile.getPreRideComplete());
    }

    public void checkReady() {
        if (airFilterCB.isChecked() && spokesCB.isChecked() && chainCB.isChecked() &&
                brakesCB.isChecked() && throttleCB.isChecked() && chassisCB.isChecked()) {
            profile.setPreRideComplete(true);
            setReadyNotReady(true);
        } else {
            profile.setPreRideComplete(false);
            setReadyNotReady(false);
        }
        PreferencesMgr.saveProfile(this, profileIndex, profile);
    }

    public void setReadyNotReady(boolean isReady) {
        if (isReady) {
            readyNotReady.setBackgroundColor(getResources().getColor(R.color.ledGreen));
            readyNotReady.setText(R.string.ready);
        } else {
            readyNotReady.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            readyNotReady.setText(R.string.not_ready);
        }
    }

    // Set pre-ride checks in profile, then checkReady()
    public void checkAirFilterCB(View v) {
        profile.setAirFilterCB(airFilterCB.isChecked());
        checkReady();
    }
    public void checkSpokesCB(View v) {
        profile.setSpokesCB(spokesCB.isChecked());
        checkReady();
    }
    public void checkChainCB(View v) {
        profile.setChainCB(chainCB.isChecked());
        checkReady();
    }
    public void checkBrakesCB(View v) {
        profile.setBrakesCB(brakesCB.isChecked());
        checkReady();
    }
    public void checkThrottleClutchCB(View v) {
        profile.setThrottleClutchCB(throttleCB.isChecked());
        checkReady();
    }
    public void checkChassisCB(View v) {
        profile.setChassisCB(chassisCB.isChecked());
        checkReady();
    }
}
