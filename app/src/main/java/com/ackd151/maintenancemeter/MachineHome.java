package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.ackd151.maintenancemeter.MainActivity.PRIMARY_PROFILE_IMAGE;

public class MachineHome extends AppCompatActivity {

    ArrayList<Machine> profiles;
    Machine profile;
    String year, make, model;
    float hours;
    int profileIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_home);

        // Get passed profile list and index from intent
        profiles = getIntent().getParcelableArrayListExtra("profiles");
        profileIndex = getIntent().getIntExtra("profileIndex", -1);
        profile = profiles.get(profileIndex);

        screenBuilder();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        screenBuilder();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                profiles = data.getParcelableArrayListExtra("profiles");
                profile = profiles.get(profileIndex);
            }
        }
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

    public void postRide(View view) {
        // pass along profiles and active list index
        Intent postRideActivity = new Intent(this, PostRide.class);
        postRideActivity.putParcelableArrayListExtra("profiles", profiles);
        postRideActivity.putExtra("profileIndex", profileIndex);

        startActivityForResult(postRideActivity, 1);
    }

    public void preRide(View view) {
        Intent preRideActivity = new Intent(this, PreRide.class);
        preRideActivity.putExtra("active_profile", (Parcelable)profile);

        startActivity(preRideActivity);
    }

    public void maintHome(View view) {
        Intent maintenanceActivity = new Intent(this, MaintenanceHome.class);
        maintenanceActivity.putExtra("active_profile", (Parcelable)profile);
        startActivity(maintenanceActivity);
    }
}
