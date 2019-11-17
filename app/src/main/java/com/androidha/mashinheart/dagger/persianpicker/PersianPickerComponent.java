package com.androidha.mashinheart.dagger.persianpicker;

import dagger.Component;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;

@Component(modules = {PersianPickerModul.class})
public interface PersianPickerComponent {
    PersianDatePickerDialog getPersianDatePickerDialog();
}
