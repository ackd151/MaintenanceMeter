package com.ackd151.maintenancemeter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.ackd151.maintenancemeter.MainActivity.PRIMARY_PROFILE_IMAGE;

public class MachineHome extends AppCompatActivity {

    Machine profile;
    String year, make, model;
    float hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_home);
        profile = (Machine)getIntent().getExtras().getParcelable("profile");
        screenBuilder();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        screenBuilder();
    }

    public void screenBuilder() {
        year = profile.getYear();
        make = profile.getMake();
        model = profile.getModel();
        hours = profile.getCurrentHours();

        TextView tYear = (TextView)findViewById(R.id.yearTV);
        tYear.setText(year);
        TextView tMake = (TextView)findViewById(R.id.makeTV);
        tMake.setText(make);
        TextView tModel = (TextView)findViewById(R.id.modelTV);
        tModel.setText(model);

        String bikeImgFilename = profile.getImgFilename();
        ImageView bikeImage = (ImageView)findViewById(R.id.bikeIV);
        bikeImage.setImageResource(
                getResources().getIdentifier(bikeImgFilename, "drawable", getPackageName()));

        TextView currHours = (TextView)findViewById(R.id.floatHrsTV);
        currHours.setText(String.valueOf(hours));
    }

    public void postRide(View view) {
        Intent postRideActivity = new Intent(this, PostRide.class);
        postRideActivity.putExtra("active_profile", (Parcelable)profile);
        startActivity(postRideActivity);
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
