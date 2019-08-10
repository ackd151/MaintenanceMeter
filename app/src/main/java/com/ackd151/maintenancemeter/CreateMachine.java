package com.ackd151.maintenancemeter;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateMachine extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerYear, spinnerMake, spinnerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_machine);

        spinnerYear = findViewById(R.id.spinner_year);
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this,
                R.array.manufactured_year, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterYear);

        spinnerMake = findViewById(R.id.spinner_make);
        ArrayAdapter<CharSequence> adapterMake = ArrayAdapter.createFromResource(this,
                R.array.manufacturer_list, android.R.layout.simple_spinner_item);
        adapterMake.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMake.setAdapter(adapterMake);
        spinnerMake.setOnItemSelectedListener(this);

        spinnerModel = findViewById(R.id.spinner_model);
        ArrayAdapter<CharSequence> adapterModel = ArrayAdapter.createFromResource(this,
                R.array.empty_spinner, android.R.layout.simple_spinner_item);
        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(adapterModel);
    }

    //Populate appropriate models for selected manufacturer.
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String make = parent.getItemAtPosition(pos).toString();
        ArrayAdapter<CharSequence> adapterModel;
        switch (make)   {
            case "Honda" : adapterModel = ArrayAdapter.createFromResource(this,
                    R.array.honda_models, android.R.layout.simple_spinner_item);
                adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModel.setAdapter(adapterModel);
                break;
            case "Husqvarna" : adapterModel = ArrayAdapter.createFromResource(this,
                    R.array.husqvarna_models, android.R.layout.simple_spinner_item);
                adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModel.setAdapter(adapterModel);
                break;
            case "Kawasaki" : adapterModel = ArrayAdapter.createFromResource(this,
                    R.array.kawasaki_models, android.R.layout.simple_spinner_item);
                adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModel.setAdapter(adapterModel);
                break;
            case "KTM" : adapterModel = ArrayAdapter.createFromResource(this,
                    R.array.ktm_models, android.R.layout.simple_spinner_item);
                adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModel.setAdapter(adapterModel);
                break;
            case "Suzuki" : adapterModel = ArrayAdapter.createFromResource(this,
                    R.array.suzuki_models, android.R.layout.simple_spinner_item);
                adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModel.setAdapter(adapterModel);
                break;
            case "Yamaha" : adapterModel = ArrayAdapter.createFromResource(this,
                    R.array.yamaha_models, android.R.layout.simple_spinner_item);
                adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModel.setAdapter(adapterModel);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //no-op
    }

    public void createBikeProfile(View view) {
        String year = (String) spinnerYear.getSelectedItem();
        String make = (String) spinnerMake.getSelectedItem();
        String model = (String) spinnerModel.getSelectedItem();
        String profileName = year + " " + model;
        EditText hoursEntry = (EditText) findViewById(R.id.hours_entry);
        float currentHours = Float.valueOf(hoursEntry.getText().toString());
        //temporary image assignment
        String tempYear = "2017";
        String tempModel = "";
        switch (make) {
            case "Honda":
                tempModel = "CRF 450R";
                break;
            case "Husqvarna":
                tempModel = "FC 450";
                break;
            case "Kawasaki":
                tempModel = "KX 450F";
                break;
            case "KTM":
                tempModel = "450 SX-F";
                break;
            case "Suzuki":
                tempModel = "RM-Z 450";
                break;
            case "Yamaha":
                tempModel = "YZ 450F";
                break;
        }
        String bikeImgFilename = make + tempModel + tempYear;
        bikeImgFilename = bikeImgFilename.toLowerCase().replaceAll("[ -]", "_");
        EditText engOil =  findViewById(R.id.eng_oil_number);
        float engOilInterval = Float.valueOf(engOil.getText().toString());
        EditText oilFilter =  findViewById(R.id.oil_filter_number);
        int oilFilterChgAt = Integer.valueOf(oilFilter.getText().toString());
        EditText forkOil =  findViewById(R.id.fork_oil_number);
        float forkOilInterval = Float.valueOf(forkOil.getText().toString());
        EditText shockOil =  findViewById(R.id.shock_oil_number);
        float shockOilInterval = Float.valueOf(shockOil.getText().toString());
        EditText valve =  findViewById(R.id.valve_number);
        float valveInterval = Float.valueOf(valve.getText().toString());
        EditText topEnd =  findViewById(R.id.top_end_number);
        float topEndInterval = Float.valueOf(topEnd.getText().toString());

        Intent resultIntent = new Intent(this, MainActivity.class);
        Machine newProfile = new Machine(profileName, year, make, model, currentHours, bikeImgFilename,
                bikeImgFilename, engOilInterval, currentHours, oilFilterChgAt, oilFilterChgAt,
                forkOilInterval, currentHours, shockOilInterval, currentHours, topEndInterval,
                currentHours, valveInterval, currentHours);


        // Change to standard activity launch w/ index and save via static prefMgr. Get rid of parcelable...

        
        resultIntent.putExtra("new_profile", (Parcelable) newProfile);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
