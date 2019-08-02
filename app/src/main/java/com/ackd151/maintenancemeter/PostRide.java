package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class PostRide extends AppCompatActivity {

    Machine profile;
    String year, make, model;
    float hours;
    int profileIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ride);

        // retrieve profileIndex from intent extras
        profileIndex = getIntent().getIntExtra("profileIndex", -1);

        screenBuilder();
    }

    @Override
    protected void onResume()  {
        super.onResume();
        screenBuilder();
    }

    public void updateCurrHours(View view)   {
        EditText rideDuration = findViewById(R.id.durationFloat);
        profile.setCurrentHours(profile.getCurrentHours() +
                Float.valueOf(rideDuration.getText().toString()));

        // Reset pre-ride checklist
        profile.resetPreRideUponPostRide();

        // Save updated profile to preferences
        PreferencesMgr.saveProfile(this, profileIndex, profile);

        // Return result
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public void updateHourMeter(View view)  {
        EditText hourMeter = findViewById(R.id.hour_meter_float);
        Intent returnIntent = new Intent();

        // Check to ensure updated hours >= current hours
        float newHours = Float.valueOf(hourMeter.getText().toString());
        if (newHours < profile.getCurrentHours()) {
            new AlertDialog.Builder(this)
                    .setMessage("New hours cannot be less than current hours.")
                    .setPositiveButton(android.R.string.yes, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            hourMeter.setText("");
        } else {

            profile.setCurrentHours(Float.valueOf(hourMeter.getText().toString()));

            // Reset pre-ride checklist
            profile.resetPreRideUponPostRide();

            // Save updated profile to preferences
            PreferencesMgr.saveProfile(this, profileIndex, profile);

            // Return result
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    public void screenBuilder() {
        // Get current profile from preferences
        profile = PreferencesMgr.getProfile(this, profileIndex);

        // Set view data
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
    }

}
