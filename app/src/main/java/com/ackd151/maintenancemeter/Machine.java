package com.ackd151.maintenancemeter;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


/**
 * Created by ACKD151 on 7/1/2017.
 */

public class Machine implements Parcelable, Serializable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Machine createFromParcel(Parcel in) {
            return new Machine(in);
        }

        public Machine[] newArray(int size) {
            return new Machine[size];
        }
    };

    private Color primary, secondary, accent;
    private String profileName, year, make, model, imgFilename, iconFilename;
    private int oilFilterInterval, oilFilterLeft;
    private float currentHours, engOilInterval, engOilDoneAt, topEndInterval, topEndDoneAt,
            forkOilInterval, forkOilDoneAt, shockOilInterval, shockOilDoneAt,
            valvesInterval, valvesDoneAt;
    private float intakeLeftShim, intakeRightShim, exhaustLeftShim, exhaustRightShim;

    //Constructor
    public Machine(String year, String make, String model, float hours)  {
        profileName = year + " " + model;
        this.year = year;
        this.make = make;
        this.model = model;
        currentHours = hours;
    }

    public Machine(String profileName, String year, String make, String model, /*String imgFilename,
                   String iconFilename, /*Color primary, Color secondary, Color accent,*/
                   float currentHours, String imgFilename, String iconFilename,
                   float engOilInterval, float engOilDoneAt, int oilFilterInterval,
                   int oilFilterLeft, float forkOilInterval, float forkOilDoneAt,
                   float shockOilInterval, float shockOilDoneAt, float topEndInterval,
                   float topEndDoneAt, float valvesInterval, float valvesDoneAt
                   /*, float intakeLeftAt, float intakeRightAt,
                   float exhaustLeftAt, float exhaustRightAt*/)   {
        this.profileName = profileName;
        this.year = year;
        this.make = make;
        this.model = model;
        this.currentHours = currentHours;
        this.imgFilename = imgFilename;
        this.iconFilename = iconFilename;
//        this.primary = primary;
//        this.secondary = secondary;
//        this.accent = accent;
        this.engOilInterval = engOilInterval;
        this.engOilDoneAt = engOilDoneAt;
        this.oilFilterInterval = oilFilterInterval;
        this.oilFilterLeft = oilFilterLeft;
        this.forkOilInterval = forkOilInterval;
        this.forkOilDoneAt = forkOilDoneAt;
        this.shockOilInterval = shockOilInterval;
        this.shockOilDoneAt = shockOilDoneAt;
        this.topEndInterval = topEndInterval;
        this.topEndDoneAt = topEndDoneAt;
        this.valvesInterval = valvesInterval;
        this.valvesDoneAt = valvesDoneAt;
//        this.intakeLeftAt = intakeLeftAt;
//        this.intakeRightAt = intakeRightAt;
//        this.exhaustLeftAt = exhaustLeftAt;
//        this.exhaustRightAt = exhaustRightAt;
    }

    void setprofileName(String profileName)   {   this.profileName = profileName;   }

    String getProfileName()    {   return profileName;    }

    String getYear() {   return year;   }

    String getMake() {   return make;    }

    String getModel()    {   return model;   }

    void setCurrentHours(float hours)   {   currentHours = hours;   }

    float getCurrentHours()  {   return currentHours;    }

    void setImgFilename(String imgFilename)  {   this.imgFilename = imgFilename; }

    String getImgFilename()  {   return imgFilename;  }

    void setIconFilename(String imgFilename)  {  this.imgFilename = imgFilename; }

    String getIconFilename()  {   return iconFilename;  }

    void setPrimaryColor(Color primary)  {   this.primary = primary; }

    Color getPrimaryColor()  {   return primary; }

    void setSecondaryColor(Color secondary)  {   this.secondary = secondary; }

    Color getSecondaryColor()    {   return secondary;   }

    void setAccentColor(Color accent)  {   this.accent = accent; }

    Color getAccentColor() {   return accent;  }

    //Engine Oil
    void setEngOilInterval(float interval)  {  engOilInterval = interval;   }

    float getEngOilInterval()   {   return engOilInterval;  }

    float getEngOilDoneAt()    {   return engOilDoneAt;  }

    void resetEngineOil(boolean filter)   {
        engOilDoneAt = currentHours;
        if (filter) {
            resetOilFilterCount();
        }   else    {
            --oilFilterLeft;
        }
    }

    void resetEngineOil(float hours, boolean filter) {
        engOilDoneAt = hours;
        if (filter) {
            resetOilFilterCount();
        }   else    {
            --oilFilterLeft;
        }
    }

    //Oil Filter
    void resetOilFilterCount()  {
        oilFilterLeft = oilFilterInterval;
    }

    void setOilFilterDueIn(int dueIn)   {   oilFilterLeft = dueIn;    }

    int getOilFilterLeft() {   return oilFilterLeft; }

    void setOilFilterLeft(int oilChanges)   {   oilFilterLeft = oilChanges;    }


    //Forks
    void setForkOilInterval(float interval)  {   forkOilInterval = interval; }

    float getForkOilInterval()  {   return forkOilInterval; }

    void setForkOilDoneAt(float hours)  {   forkOilDoneAt = hours;  }

    float getForkOilDoneAt()  {   return forkOilDoneAt;    }

    void changeForkOil() {   forkOilDoneAt = currentHours;    }


    //Shock
    void setShockOilInterval(float interval) {   shockOilInterval = interval;    }

    float getShockOilInterval()  {   return shockOilInterval; }

    void setShockOilDoneAt(float hours) {   shockOilDoneAt = hours;  }

    float getShockOilDoneAt() {   return shockOilDoneAt;   }

    void changeShockOil() {  shockOilDoneAt = currentHours;   }


    //Top-end
    void setTopEndInterval(float interval)   {   topEndInterval = interval;  }

    float getTopEndInterval()   {   return topEndInterval;  }

    void setTopEndDoneAt(float hours)   {   topEndDoneAt = hours;   }

    float getTopEndDoneAt()  {   return topEndDoneAt;    }

    void resetTopEnd(float intakeLeftShim, float intakeRightShim, float exhaustLeftShim,
                     float exhaustRightShim) {
        topEndDoneAt = currentHours;
        this.intakeLeftShim = intakeLeftShim;
        this.intakeRightShim = intakeRightShim;
        this.exhaustLeftShim = exhaustLeftShim;
        this.exhaustRightShim = exhaustRightShim;
    }

    void resetTopEnd(float hours, float intakeLeftShim, float intakeRightShim, float exhaustLeftShim,
                     float exhaustRightShim) {
        topEndDoneAt = hours;
        this.intakeLeftShim = intakeLeftShim;
        this.intakeRightShim = intakeRightShim;
        this.exhaustLeftShim = exhaustLeftShim;
        this.exhaustRightShim = exhaustRightShim;
    }

    void resetTopEnd()  {   topEndDoneAt = currentHours;    }

    void setIntakeLeftShim(float shimSize)    { intakeLeftShim = shimSize;  }

    float getIntakeLeftShim()  {   return intakeLeftShim;    }

    void setIntakeRightShim(float shimSize)    { intakeRightShim = shimSize;  }

    float getIntakeRightShim() {   return intakeRightShim;   }

    void setExhaustLeftShim(float shimSize)    { exhaustLeftShim = shimSize;  }

    float getExhaustLeftShim() {   return exhaustLeftShim;   }

    void setExhaustRightShim(float shimSize)    { exhaustRightShim = shimSize;  }

    float getExhaustRightShim()    {   return exhaustRightShim;  }


    //Valves
    void setValvesInterval(float interval)   {   valvesInterval = interval;  }

    float getValvesInterval()   {   return valvesInterval;  }

    void setValvesDoneAt(float hours)   {   valvesDoneAt = hours;   }

    float getValvesDoneAt()  {   return valvesDoneAt;    }

    void resetValveClearance(float intakeLeftShim, float intakeRightShim, float exhaustLeftShim,
            float exhaustRightShim)   {
        valvesDoneAt = currentHours;
        this.intakeLeftShim = intakeLeftShim;
        this.intakeRightShim = intakeRightShim;
        this.exhaustLeftShim = exhaustLeftShim;
        this.exhaustRightShim = exhaustRightShim;
    }

    void resetValveClearance(float hours, float intakeLeftShim, float intakeRightShim,
                             float exhaustLeftShim, float exhaustRightShim)   {
        valvesDoneAt = hours;
        this.intakeLeftShim = intakeLeftShim;
        this.intakeRightShim = intakeRightShim;
        this.exhaustLeftShim = exhaustLeftShim;
        this.exhaustRightShim = exhaustRightShim;
    }

    //Parcel Constructor
    public Machine(Parcel in)   {
        this.profileName = in.readString();
        this.year = in.readString();
        this.make = in.readString();
        this.model = in.readString();
        this.currentHours = in.readFloat();
        this.imgFilename = in.readString();
        this.iconFilename = in.readString();
        this.engOilInterval = in.readFloat();
        this.engOilDoneAt = in.readFloat();
        this.oilFilterInterval = in.readInt();
        this.oilFilterLeft = in.readInt();
        this.forkOilInterval = in.readFloat();
        this.forkOilDoneAt = in.readFloat();
        this.shockOilInterval = in.readFloat();
        this.shockOilDoneAt = in.readFloat();
        this.topEndInterval = in.readFloat();
        this.topEndDoneAt = in.readFloat();
        this.valvesInterval = in.readFloat();
        this.valvesDoneAt = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.profileName);
        dest.writeString(this.year);
        dest.writeString(this.make);
        dest.writeString(this.model);
        dest.writeFloat(this.currentHours);
        dest.writeString(this.imgFilename);
        dest.writeString(this.iconFilename);
        dest.writeFloat(this.engOilInterval);
        dest.writeFloat(this.engOilDoneAt);
        dest.writeInt(this.oilFilterInterval);
        dest.writeInt(this.oilFilterLeft);
        dest.writeFloat(this.forkOilInterval);
        dest.writeFloat(this.forkOilDoneAt);
        dest.writeFloat(this.shockOilInterval);
        dest.writeFloat(this.shockOilDoneAt);
        dest.writeFloat(this.topEndInterval);
        dest.writeFloat(this.topEndDoneAt);
        dest.writeFloat(this.valvesInterval);
        dest.writeFloat(this.valvesDoneAt);
    }

    @Override
    public String toString() {
        return "Machine{" +
                "profileName='" + profileName + '\'' +
                ", year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", currentHours='" + currentHours + '\'' +
                ", engOilInterval='" + engOilInterval + '\'' +
                ", engOilDoneAt='" + engOilDoneAt + '\'' +
                ", oilFilterInterval='" + oilFilterInterval + '\'' +
                ", oilFilterLeft='" + oilFilterLeft + '\'' +
                ", forkOilInterval='" + forkOilInterval + '\'' +
                ", forkOilDoneAt='" + forkOilDoneAt + '\'' +
                ", shockOilInterval='" + shockOilInterval + '\'' +
                ", shockOilDoneAt='" + shockOilDoneAt + '\'' +
                ", topEndInterval='" + topEndInterval + '\'' +
                ", topEndDoneAt='" + topEndDoneAt + '\'' +
                ", valvesInterval='" + valvesInterval + '\'' +
                ", valvesDoneAt='" + valvesDoneAt + '\'' +
                '}';
    }

}
