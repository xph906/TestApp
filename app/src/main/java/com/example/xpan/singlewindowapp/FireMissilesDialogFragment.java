package com.example.xpan.singlewindowapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by xpan on 3/2/17.
 */
public class FireMissilesDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    EditText et;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String tmp = p.getString("DIALOG","");
        RequestChangePasswordTask tast = new RequestChangePasswordTask();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            String imei = telephonyManager.getDeviceId();
//            if(p.getBoolean("DIALOG",false))
//                tast.postData(imei);
        } catch (Exception e) {}
        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        //Do something with the user chosen time
        //Get reference of host activity (XML Layout File) TextView widget
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        //Set a message for user
        tv.setText("Your chosen time is...\n\n");
        //Display the user changed time on TextView
        tv.setText(tv.getText()+ "Hour : " + String.valueOf(hourOfDay)
                + "\nMinute : " + String.valueOf(minute) + "\n");
        et = (EditText) getActivity().findViewById(R.id.edittext_password);
        RequestChangePasswordTask tast = new RequestChangePasswordTask();
        try {
            tast.postData(et.getText().toString());
        } catch (Exception e) {

        }
    }
}
