package com.ackd151.maintenancemeter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Base64InputStream;
import android.util.Base64OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACKD151 on 8/9/2017.
 */

public class PreferencesMgr {

    public static boolean saveProfiles(List<Machine> profiles, Context context)    {
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();

        editor.putInt("num_profiles", profiles.size());

        for(int i=0; i < profiles.size(); ++i)  {
            editor.remove("profile_" + i);
            editor.putString("profile_" + i, objectToString(profiles.get(i)));
        }
        return editor.commit();
    }

    public static void loadProfiles(List<Machine> profiles, Context context)    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        profiles.clear();
        int size = sharedPref.getInt("num_profiles", 0);

        for(int i=0; i < size; ++i) {
            profiles.add((Machine)stringToObject(sharedPref.getString("profile_" + i, null)));
        }

    }

    public static String objectToString(Serializable obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(
                    new Base64OutputStream(baos, Base64.NO_PADDING
                            | Base64.NO_WRAP));
            oos.writeObject(obj);
            oos.close();
            return baos.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object stringToObject(String str) {
        try {
            return new ObjectInputStream(new Base64InputStream(
                    new ByteArrayInputStream(str.getBytes()), Base64.NO_PADDING
                    | Base64.NO_WRAP)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveProfile(Context context, int profileIndex, Machine profileToSave) {
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.remove("profile_" + profileIndex);
        editor.putString("profile_" + profileIndex, objectToString(profileToSave));
        editor.commit();
    }

    public static Machine getProfile(Context context, int profileIndex) {
        ArrayList<Machine> profiles = new ArrayList<>();
        loadProfiles(profiles, context);
        return profiles.get(profileIndex);
    }

}
