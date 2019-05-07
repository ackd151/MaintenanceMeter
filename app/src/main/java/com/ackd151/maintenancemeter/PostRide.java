package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.ackd151.maintenancemeter.MainActivity.PRIMARY_PROFILE_IMAGE;
import static com.ackd151.maintenancemeter.PreferencesMgr.objectToString;

public class PostRide extends AppCompatActivity {

    ArrayList<Machine> profiles;
    Machine profile;
    String year, make, model;
    float hours;
    int profileIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ride);

        // retrieve profiles from intent
        profiles = getIntent().getParcelableArrayListExtra("profiles");
        profileIndex = getIntent().getIntExtra("profileIndex", -1);
        profile = profiles.get(profileIndex);
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
        saveProfile();
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra("profiles", profiles);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public void updateHourMeter(View view)  {
        EditText hourMeter = findViewById(R.id.hour_meter_float);
        profile.setCurrentHours(Float.valueOf(hourMeter.getText().toString()));
        screenBuilder();
        finish();
    }

    public void screenBuilder() {
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

    public void saveProfile() {
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.remove("profile_" + profileIndex);
        editor.putString("profile_" + profileIndex, objectToString(profile));
        editor.commit();
    }

}
