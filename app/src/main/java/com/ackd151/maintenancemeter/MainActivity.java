package com.ackd151.maintenancemeter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.app.DialogFragment;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements DeleteDialogFragment.DeleteDialogListener    {

    final int CREATE_MACHINE_REQUEST = 1;
    final int MAX_PROFILES = 3;
    final static String SAVE_FILE = "profiles.data";
    final static String PROFILES = "new_profile_list";
    final static String PRIMARY_PROFILE_IMAGE = "primary_profile_image";

    List<Machine> profiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profiles = new ArrayList<>();
        PreferencesMgr.loadProfiles(profiles, this);
    }

    @Override
    public void onResume()  {
        super.onResume();
        PreferencesMgr.loadProfiles(profiles, this);
        setProfileViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)   {
        if (requestCode == 1)   {
            if (resultCode == RESULT_OK)    {
                Bundle bundle = data.getExtras();
                Machine newProfile = (Machine)bundle.getParcelable("new_profile");
                profiles.add(newProfile);
                PreferencesMgr.saveProfiles(profiles, this);
            }
        }
    }

    public void createMachine(View view) {
        Intent newMachine = new Intent(this, CreateMachine.class);
        startActivityForResult(newMachine, CREATE_MACHINE_REQUEST);
    }

    private void setProfileViews() {
        String profileX = "Profile ";
        int numProfiles = profiles == null ? 0 : profiles.size();

        for (int i = 0; i < MAX_PROFILES; ++i) {

            //set imageView
            String iconFileName = "placeholder.png";
            String resourceName = "profile" + (i+1) + "ImgView";
            int imageViewId = getResources().getIdentifier(resourceName, "id", getPackageName());
            ImageView profile = (ImageView) findViewById(imageViewId);
            if (i < numProfiles)
                iconFileName = profiles.get(i).getIconFilename();
            profile.setImageResource(getResources().getIdentifier(iconFileName, "drawable", getPackageName()));

            //set profile button
            String profileName = (i < numProfiles ? profiles.get(i).getProfileName() : (profileX + (i+1)));
            resourceName = "profile" + (i+1) + "Button";
            int buttonId = getResources().getIdentifier(resourceName, "id", getPackageName());
            Button profileButton = (Button) findViewById(buttonId);
            profileButton.setText(profileName);
            if (i < numProfiles) {
                final int index = i;
                profileButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        machineHome(v, index);
                    }
                });
            }

            //set action button
            resourceName = "profile" + (i+1) + "ModButton";
            int actionButtonId = getResources().getIdentifier(resourceName, "id", getPackageName());
            Button actionButton = (Button) findViewById(actionButtonId);
            setButton(actionButton, (i < numProfiles ? "delete" : "add"));
        }
    }

    private void setButton(Button button, String action)  {
        button.setBackgroundResource(action.equals("add") ? R.color.ledGreen : R.color.colorAccent);
        button.setText(action.equals("add") ? R.string.add_profile : R.string.delete_profile);
        button.setOnClickListener(action.equals("add") ?
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createMachine(v);
                    }
                } :
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteProfile(v);
                    }
                });
    }

    public void machineHome(View view, int profileIndex)  {
        Intent launchMachineHome = new Intent(this, MachineHome.class);
        launchMachineHome.putExtra("profile", (Parcelable)profiles.get(profileIndex));
        startActivity(launchMachineHome);
    }

    public void deleteProfile(View view) {
        DialogFragment deleteConfirm = new DeleteDialogFragment();
        deleteConfirm.show(getFragmentManager(), "delete_dialog");
    }

    @Override
    public void onDialogPositiveClick(android.app.DialogFragment dialog) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove(PRIMARY_PROFILE);
//        editor.remove(PRIMARY_PROFILE_IMAGE);
//        editor.apply();
//        profilePresent(false);
    }

    @Override
    public void onDialogNegativeClick(android.app.DialogFragment dialog) {

    }


    //Troubleshooting methods...
    void toast(String message)  {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

}
