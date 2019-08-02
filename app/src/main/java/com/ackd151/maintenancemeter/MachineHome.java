package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MachineHome extends AppCompatActivity {

    Machine profile;
    String year, make, model;
    float hours;
    int profileIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_home);

        // Get profileIndex from intent extras
        profileIndex = getIntent().getIntExtra("profileIndex", -1);

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
                screenBuilder();
            }
        }
    }

//    @Override
//    public void onBackPressed() {
//        Intent returnProfile = new Intent();
//        returnProfile.putExtra("returningProfile", (Parcelable)profile);
//        returnProfile.putExtra("profileIndex", profileIndex);
//        setResult(Activity.RESULT_OK, returnProfile);
//        finish();
//    }

    public void screenBuilder() {
        // Retrieve current profile from preferences
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

    public void postRide(View view) {
        Intent postRideActivity = new Intent(this, PostRide.class);
        postRideActivity.putExtra("profileIndex", profileIndex);

        startActivityForResult(postRideActivity, 1);
    }

    public void preRide(View view) {
        Intent preRideActivity = new Intent(this, PreRide.class);
        preRideActivity.putExtra("profileIndex", profileIndex);

        startActivity(preRideActivity);
    }

    public void maintHome(View view) {
        Intent maintenanceActivity = new Intent(this, MaintenanceHome.class);
        maintenanceActivity.putExtra("profileIndex", profileIndex);

        startActivity(maintenanceActivity);
    }
}
