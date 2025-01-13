package com.software.donate.ui.donate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.software.donate.databinding.FragmentDonateBinding;

import java.util.Calendar;

public class DonateFragment extends Fragment {

    private FragmentDonateBinding binding;
    private TextView textSelectedTime;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDonateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textSelectedTime = binding.textSelectedTime;
        Button buttonCollectionTime = binding.buttonCollectionTime;

        buttonCollectionTime.setOnClickListener(v -> showDateTimePicker());

        return root;
    }

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        new DatePickerDialog(getContext(), (view, year, monthOfYear, dayOfMonth) -> {
            date.set(year, monthOfYear, dayOfMonth);
            new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);
                textSelectedTime.setText(date.getTime().toString());
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}