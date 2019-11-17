package com.androidha.mashinheart.dagger.persianpicker;

import android.content.Context;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import dagger.Module;
import dagger.Provides;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import java.util.Calendar;

@Module
public class PersianPickerModul {
    public static Context context;

    @Provides
    public PersianDatePickerDialog getPersianDatePickerDialog() {
        return new PersianDatePickerDialog(context).setPositiveButtonString(context.getString(R.string.DatePickerChoose)).setNegativeButton(context.getString(R.string.DatePickerCancle)).setTodayButton(context.getString(R.string.DatePickerToday)).setTodayButtonVisible(true).setInitDate(getPersianCalendar()).setMaxYear(-1).setMinYear(1300).setActionTextColor(context.getResources().getColor(R.color.MainRight)).setTitleColor(context.getResources().getColor(R.color.MainRight)).setPickerBackgroundColor(context.getResources().getColor(R.color.MainDatePicker)).setBackgroundColor(context.getResources().getColor(R.color.MainWhite));
    }

    @Provides
    public PersianCalendar getPersianCalendar() {
        String ChangeDate = MachinHeartApplication
                .getMachinHeartApplication(context)
                .getApplicationUtilityComponent()
                .getApplicationUtility()
                .MiladiToJalali(Calendar.getInstance().getTime(), "FullJalaliNumber")
                .replaceAll("/", "");
        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(Integer.valueOf(ChangeDate.substring(0, 4)).intValue(), Integer.valueOf(ChangeDate.substring(4, 6)).intValue(), Integer.valueOf(ChangeDate.substring(6, 8)).intValue());
        return initDate;
    }
}
