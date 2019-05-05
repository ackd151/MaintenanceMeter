package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.ackd151.maintenancemeter.MainActivity.PRIMARY_PROFILE_IMAGE;

public class PostRide extends AppCompatActivity {

    Machine profile;
    String year, make, model;
    float hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ride);
        profile = getIntent().getExtras().getParcelable("active_profile");
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
        Intent returnProfile = new Intent();
        returnProfile.putExtra("returningProfile", (Parcelable)profile);
        setResult(Activity.RESULT_OK, returnProfile);
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

}
