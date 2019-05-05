package com.ackd151.maintenancemeter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static com.ackd151.maintenancemeter.MainActivity.PRIMARY_PROFILE_IMAGE;

public class MaintenanceHome extends AppCompatActivity {

    Machine profile;
    String year, make, model;
    float hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_home);

        profile = getIntent().getExtras().getParcelable("active_profile");
        screenBuilder();

        float engOilDoneAt = profile.getEngOilDoneAt();
        float engOilInterval = profile.getEngOilInterval();
        float engOilHrsLeft = getTimeLeft(hours, engOilDoneAt, engOilInterval);
        TextView engOilDue = (TextView)findViewById(R.id.eng_oil_hrs_left_float);
        engOilDue.setText(String.valueOf(engOilHrsLeft));
        engOilDue.setTextColor(engOilHrsLeft < 1.5 ? Color.RED : Color.GREEN);

        int oilFilterLeft = profile.getOilFilterLeft();
        TextView oilFilterDueIn = (TextView)findViewById(R.id.filter_chgs_left_int);
        oilFilterDueIn.setText(String.valueOf(oilFilterLeft));
        oilFilterDueIn.setTextColor(oilFilterLeft > 0 ? Color.GREEN : Color.RED);

        float forkOilDoneAt = profile.getForkOilDoneAt();
        float forkOilInterval = profile.getForkOilInterval();
        float forkOilHrsLeft = getTimeLeft(hours, forkOilDoneAt, forkOilInterval);
        TextView forkOilDue = (TextView)findViewById(R.id.fork_oil_hrs_left_float);
        forkOilDue.setText(String.valueOf(forkOilHrsLeft));
        forkOilDue.setTextColor(forkOilHrsLeft < 1.5 ? Color.RED : Color.GREEN);

        float shockOilDoneAt = profile.getShockOilDoneAt();
        float shockOilInterval = profile.getShockOilInterval();
        float shockOilHrsLeft = getTimeLeft(hours, shockOilDoneAt, shockOilInterval);
        TextView shockOilDue = (TextView)findViewById(R.id.shock_oil_hrs_left_float);
        shockOilDue.setText(String.valueOf(shockOilHrsLeft));
        shockOilDue.setTextColor(shockOilHrsLeft < 1.5 ? Color.RED : Color.GREEN);

        float valvesDoneAt = profile.getValvesDoneAt();
        float valvesInterval = profile.getValvesInterval();
        float valvesHrsLeft = getTimeLeft(hours, valvesDoneAt, valvesInterval);
        TextView valvesDue = (TextView)findViewById(R.id.valves_hrs_left_float);
        valvesDue.setText(String.valueOf(valvesHrsLeft));
        valvesDue.setTextColor(valvesHrsLeft < 1.5 ? Color.RED : Color.GREEN);

        float topEndDoneAt = profile.getTopEndDoneAt();
        float topEndInterval = profile.getTopEndInterval();
        float topEndHrsLeft = getTimeLeft(hours, topEndDoneAt, topEndInterval);
        TextView topEndDue = (TextView)findViewById(R.id.top_end_hrs_left_float);
        topEndDue.setText(String.valueOf(topEndHrsLeft));
        topEndDue.setTextColor(topEndHrsLeft < 1.5 ? Color.RED : Color.GREEN);
    }

    public float getTimeLeft(float currHours, float lastDoneAt, float interval)   {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        return Float.valueOf(format.format(interval - (currHours - lastDoneAt)));
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

}
