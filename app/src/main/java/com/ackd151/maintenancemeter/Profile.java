package com.ackd151.maintenancemeter;

import android.graphics.Color;

/**
 * Created by ACKD151 on 8/6/2017.
 */

class Profile {
//    Color primary, secondary, accent;
    String profileName, year, make, model, imgFilename, iconFilename;
    int oilFilterChgAt, chgFilterIn;
    float currentHours, engineOilInterval, engineOilLastChgAt, topEndInterval, topEndLastAt,
            forkOilInterval, forkOilLastChgAt, shockOilInterval, shockOilLastChgAt,
            valveClearanceInterval, valveClearanceLastAt;
    float intakeLeftAt, intakeRightAt, exhaustLeftAt, exhaustRightAt;

    //Constructor
    public Profile(String year, String make, String model, float hours)  {
        profileName = year + " " + model;
        this.year = year;
        this.make = make;
        this.model = model;
        currentHours = hours;
    }

    public Profile(String profileName, String year, String make, String model, String imgFilename,
                   String iconFilename, /*Color primary, Color secondary, Color accent,*/
                   int oilFilterChgAt, int chgFilterIn, float currentHours, float engineOilInterval,
                   float engineOilLastChgAt, float topEndInterval, float topEndLastAt,
                   float forkOilInterval, float forkOilLastChgAt, float shockOilInterval,
                   float shockOilLastChgAt, float valveClearanceInterval,
                   float valveClearanceLastAt/*, float intakeLeftAt, float intakeRightAt,
                   float exhaustLeftAt, float exhaustRightAt*/)   {
        this.profileName = profileName;
        this.year = year;
        this.make = make;
        this.model = model;
        this.imgFilename = imgFilename;
        this.iconFilename = iconFilename;
//        this.primary = primary;
//        this.secondary = secondary;
//        this.accent = accent;
        this.oilFilterChgAt = oilFilterChgAt;
        this.chgFilterIn = chgFilterIn;
        this.currentHours = currentHours;
        this.engineOilInterval = engineOilInterval;
        this.engineOilLastChgAt = engineOilLastChgAt;
        this.topEndInterval = topEndInterval;
        this.topEndLastAt = topEndLastAt;
        this.forkOilInterval = forkOilInterval;
        this.forkOilLastChgAt = forkOilLastChgAt;
        this.shockOilInterval = shockOilInterval;
        this.shockOilLastChgAt = shockOilLastChgAt;
        this.valveClearanceInterval = valveClearanceInterval;
        this.valveClearanceLastAt = valveClearanceLastAt;
//        this.intakeLeftAt = intakeLeftAt;
//        this.intakeRightAt = intakeRightAt;
//        this.exhaustLeftAt = exhaustLeftAt;
//        this.exhaustRightAt = exhaustRightAt;
    }

}
