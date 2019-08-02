package com.ackd151.maintenancemeter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TopEndRebuild extends AppCompatActivity {

    int profileIndex;
    Machine profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_end_rebuild);

        profileIndex = getIntent().getIntExtra("profileIndex", -1);
        profile = PreferencesMgr.getProfile(this, profileIndex);

        screenBuilder();
    }

    public void rebuildTopEnd(View v) {
        EditText leftIntake = findViewById(R.id.left_intake_meas);
        EditText leftExhaust = findViewById(R.id.left_exhaust_meas);
        EditText rightIntake = findViewById(R.id.right_intake_meas);
        EditText rightExhaust = findViewById(R.id.right_exhaust_meas);

        // Check for empty editText fields
        float leftIntakeGap = leftIntake.getText().length() == 0 ?
                0 : Float.valueOf(leftIntake.getText().toString());
        float leftExhaustGap = leftExhaust.getText().length() == 0 ?
                0 : Float.valueOf(leftExhaust.getText().toString());
        float rightIntakeGap = rightIntake.getText().length() == 0 ?
                0 : Float.valueOf(rightIntake.getText().toString());
        float rightExhaustGap = rightExhaust.getText().length() == 0 ?
                0 : Float.valueOf(rightExhaust.getText().toString());

        profile.resetTopEnd(leftIntakeGap, rightIntakeGap, leftExhaustGap, rightExhaustGap);
        PreferencesMgr.saveProfile(this, profileIndex, profile);

        setResult(Activity.RESULT_OK);
        finish();
    }

    public void screenBuilder() {
        TextView lastLE = findViewById(R.id.last_LE), lastRE = findViewById(R.id.last_RE);
        TextView lastLI = findViewById(R.id.last_LI), lastRI = findViewById(R.id.last_RI);
        lastLE.setText(Float.toString(profile.getExhaustLeftShim()));
        lastRE.setText(Float.toString(profile.getExhaustRightShim()));
        lastLI.setText(Float.toString(profile.getIntakeLeftShim()));
        lastRI.setText(Float.toString(profile.getIntakeRightShim()));
    }

}
