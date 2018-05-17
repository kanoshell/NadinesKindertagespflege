package com.daheim.nadineskindertagespflege;

import android.app.TimePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final GregorianCalendar c = (GregorianCalendar) GregorianCalendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        // Create a new instance of DatePickerDialog and return it
        return new TimePickerDialog(getActivity(),
                (TimePickerDialog.OnTimeSetListener) getActivity(), hourOfDay, minutes, true);
    }

}