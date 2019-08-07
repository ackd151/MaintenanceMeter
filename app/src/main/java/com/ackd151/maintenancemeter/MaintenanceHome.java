package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.ackd151.maintenancemeter.MainActivity.PRIMARY_PROFILE_IMAGE;
import static com.ackd151.maintenancemeter.PreferencesMgr.objectToString;

public class MaintenanceHome extends AppCompatActivity {

    Machine profile;
    String year, make, model;
    int profileIndex;
    float hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_home);

        // retrieve profileIndex from intent
        profileIndex = getIntent().getIntExtra("profileIndex", -1);

        screenBuilder();
    }

    public float getTimeLeft(float currHours, float lastDoneAt, float interval)   {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        return Float.valueOf(format.format(interval - (currHours - lastDoneAt)));
    }

    public void screenBuilder() {
        // Get current profile from preferences
        profile = PreferencesMgr.getProfile(this, profileIndex);

        // set view data
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

        float engOilDoneAt = profile.getEngOilDoneAt();
        float engOilInterval = profile.getEngOilInterval();
        float engOilHrsLeft = getTimeLeft(hours, engOilDoneAt, engOilInterval);
        TextView engOilDue = findViewById(R.id.eng_oil_hrs_left_float);
        engOilDue.setText(String.valueOf(engOilHrsLeft));
        engOilDue.setTextColor(engOilHrsLeft < 1.5 ? Color.RED : Color.GREEN);

        int oilFilterLeft = profile.getOilFilterLeft();
        TextView oilFilterDueIn = findViewById(R.id.filter_chgs_left_int);
        oilFilterDueIn.setText(oilFilterLeft == 1 ? "next" : String.valueOf(oilFilterLeft));
        oilFilterDueIn.setTextColor(oilFilterLeft > 1 ? Color.GREEN : Color.RED);

        float forkOilDoneAt = profile.getForkOilDoneAt();
        float forkOilInterval = profile.getForkOilInterval();
        float forkOilHrsLeft = getTimeLeft(hours, forkOilDoneAt, forkOilInterval);
        TextView forkOilDue = findViewById(R.id.fork_oil_hrs_left_float);
        forkOilDue.setText(String.valueOf(forkOilHrsLeft));
        forkOilDue.setTextColor(forkOilHrsLeft < 1.5 ? Color.RED : Color.GREEN);

        float shockOilDoneAt = profile.getShockOilDoneAt();
        float shockOilInterval = profile.getShockOilInterval();
        float shockOilHrsLeft = getTimeLeft(hours, shockOilDoneAt, shockOilInterval);
        TextView shockOilDue = findViewById(R.id.shock_oil_hrs_left_float);
        shockOilDue.setText(String.valueOf(shockOilHrsLeft));
        shockOilDue.setTextColor(shockOilHrsLeft < 1.5 ? Color.RED : Color.GREEN);

        float valvesDoneAt = profile.getValvesDoneAt();
        float valvesInterval = profile.getValvesInterval();
        float valvesHrsLeft = getTimeLeft(hours, valvesDoneAt, valvesInterval);
        TextView valvesDue = findViewById(R.id.valves_hrs_left_float);
        valvesDue.setText(String.valueOf(valvesHrsLeft));
        valvesDue.setTextColor(valvesHrsLeft < 1.5 ? Color.RED : Color.GREEN);

        float topEndDoneAt = profile.getTopEndDoneAt();
        float topEndInterval = profile.getTopEndInterval();
        float topEndHrsLeft = getTimeLeft(hours, topEndDoneAt, topEndInterval);
        TextView topEndDue = findViewById(R.id.top_end_hrs_left_float);
        topEndDue.setText(String.valueOf(topEndHrsLeft));
        topEndDue.setTextColor(topEndHrsLeft < 1.5 ? Color.RED : Color.GREEN);
    }

    public void changeOil(View v) {
        Intent chgOilIntent = new Intent(this, OilChange.class);
        chgOilIntent.putExtra("profileIndex", profileIndex);

        startActivityForResult(chgOilIntent, 1);
    }

    public void changeForkOil(View v) {
        Intent forkOilChgIntent = new Intent(this, ForkOilChange.class);
        forkOilChgIntent.putExtra("profileIndex", profileIndex);

        startActivityForResult(forkOilChgIntent, 1);
    }

    public void changeShockOil(View v) {
        Intent shockOilChgIntent = new Intent(this, ShockOilChange.class);
        shockOilChgIntent.putExtra("profileIndex", profileIndex);

        startActivityForResult(shockOilChgIntent, 1);
    }

    public void valveClearanceCheck(View v) {
        Intent valveCheckIntent = new Intent(this, ValveClearanceCheck.class);
        valveCheckIntent.putExtra("profileIndex", profileIndex);

        startActivityForResult(valveCheckIntent, 1);
    }

    public void topEndRebuild(View v) {
        Intent topEndIntent = new Intent(this, TopEndRebuild.class);
        topEndIntent.putExtra("profileIndex", profileIndex);

        startActivityForResult(topEndIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                screenBuilder();
            }
        }
    }

}
