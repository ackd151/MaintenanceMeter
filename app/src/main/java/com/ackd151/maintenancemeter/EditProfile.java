package com.ackd151.maintenancemeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity {

    int profileIndex;
    Machine profile;
    EditText oilInterval, oilFilterInterval, forkInterval, shockInterval, valvesInterval,
            topEndInterval, oilDoneAt, oilFilterDueIn, forkDoneAt, shockDoneAt, valvesDoneAt,
            topEndDoneAt, leftExhaustGap, rightExhaustGap, leftIntakeGap, rightIntakeGap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileIndex = getIntent().getIntExtra("profileIndex", -1);
        profile = PreferencesMgr.getProfile(this, profileIndex);

        oilInterval = findViewById(R.id.oilET);
        oilFilterInterval = findViewById(R.id.oilFilterET);
        forkInterval = findViewById(R.id.forkOilET);
        shockInterval = findViewById(R.id.shockOilET);
        valvesInterval = findViewById(R.id.valvesET);
        topEndInterval = findViewById(R.id.topEndET);

        oilDoneAt = findViewById(R.id.oilChangedET);
        oilFilterDueIn = findViewById(R.id.oilFilterDueET);
        forkDoneAt = findViewById(R.id.forkDoneET);
        shockDoneAt = findViewById(R.id.shockDoneET);
        valvesDoneAt = findViewById(R.id.valvesDoneET);
        topEndDoneAt = findViewById(R.id.topEndDoneET);

        leftExhaustGap = findViewById(R.id.leftExhaustET);
        rightExhaustGap = findViewById(R.id.rightExhaustET);
        leftIntakeGap = findViewById(R.id.leftIntakeET);
        rightIntakeGap = findViewById(R.id.rightIntakeET);

        screenBuilder();
    }

    private void screenBuilder() {
        oilInterval.setText(Float.toString(profile.getEngOilInterval()));
        oilFilterInterval.setText(Integer.toString(profile.getOilFilterInterval()));
        forkInterval.setText(Float.toString(profile.getForkOilInterval()));
        shockInterval.setText(Float.toString(profile.getShockOilInterval()));
        valvesInterval.setText(Float.toString(profile.getValvesInterval()));
        topEndInterval.setText(Float.toString(profile.getTopEndInterval()));

        oilDoneAt.setText(Float.toString(profile.getEngOilDoneAt()));
        oilFilterDueIn.setText(Integer.toString(profile.getOilFilterLeft()));
        forkDoneAt.setText(Float.toString(profile.getForkOilDoneAt()));
        shockDoneAt.setText(Float.toString(profile.getShockOilDoneAt()));
        valvesDoneAt.setText(Float.toString(profile.getValvesDoneAt()));
        topEndDoneAt.setText(Float.toString(profile.getTopEndDoneAt()));

        leftExhaustGap.setText(Float.toString(profile.getExhaustLeftShim()));
        rightExhaustGap.setText(Float.toString(profile.getExhaustRightShim()));
        leftIntakeGap.setText(Float.toString(profile.getIntakeLeftShim()));
        rightIntakeGap.setText(Float.toString(profile.getIntakeRightShim()));
    }

    void chgOilInterval(View v) {
        profile.setEngOilInterval(Float.valueOf(oilInterval.getText().toString()));
        saveProfile();
    }

    void chgOilFilterInterval(View v) {
        profile.setOilFilterInterval(Integer.valueOf(oilFilterInterval.getText().toString()));
        saveProfile();
    }

    void chgForkInterval(View v) {
        profile.setForkOilInterval(Float.valueOf(forkInterval.getText().toString()));
        saveProfile();
    }

    void chgShockInterval(View v) {
        profile.setShockOilInterval(Float.valueOf(shockInterval.getText().toString()));
        saveProfile();
    }

    void chgValvesInterval(View v) {
        profile.setValvesInterval(Float.valueOf(valvesInterval.getText().toString()));
        saveProfile();
    }

    void chgTopEndInterval(View v) {
        profile.setTopEndInterval(Float.valueOf(topEndInterval.getText().toString()));
        saveProfile();
    }


    void chgOilDoneAt(View v) {
        profile.setEngOilDoneAt(Float.valueOf(oilDoneAt.getText().toString()));
        saveProfile();
    }

    void chgOilFilterDueIn(View v) {
        profile.setOilFilterDueIn(Integer.valueOf(oilFilterDueIn.getText().toString()));
        saveProfile();
    }

    void chgForkDoneAt(View v) {
        profile.setForkOilDoneAt(Float.valueOf(forkDoneAt.getText().toString()));
        saveProfile();
    }

    void chgShockDoneAt(View v) {
        profile.setShockOilDoneAt(Float.valueOf(shockDoneAt.getText().toString()));
        saveProfile();
    }

    void chgValvesDoneAt(View v) {
        profile.setValvesDoneAt(Float.valueOf(valvesDoneAt.getText().toString()));
        saveProfile();
    }

    void chgTopEndDoneAt(View v) {
        profile.setTopEndDoneAt(Float.valueOf(topEndDoneAt.getText().toString()));
        saveProfile();
    }


    void chgLeftExhaustGap(View v) {
        profile.setExhaustLeftShim(Float.valueOf(leftExhaustGap.getText().toString()));
        saveProfile();
    }

    void chgRightExhaustGap(View v) {
        profile.setExhaustRightShim(Float.valueOf(rightExhaustGap.getText().toString()));
        saveProfile();
    }

    void chgLeftIntakeGap(View v) {
        profile.setIntakeLeftShim(Float.valueOf(leftIntakeGap.getText().toString()));
        saveProfile();
    }

    void chgRightIntakeGap(View v) {
        profile.setIntakeRightShim(Float.valueOf(rightIntakeGap.getText().toString()));
        saveProfile();
    }

    private void saveProfile() {
        PreferencesMgr.saveProfile(this, profileIndex, profile);
    }

}
