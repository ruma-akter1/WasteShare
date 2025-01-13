package com.software.donate.ui.claim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.software.donate.databinding.FragmentClaimBinding;

public class ClaimFragment extends Fragment {

    private FragmentClaimBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentClaimBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        if (bundle != null) {
            String description = bundle.getString("description");
            String location = bundle.getString("location");
            String quantity = bundle.getString("quantity");
            String donationType = bundle.getString("donationType");
            String collectionTime = bundle.getString("collectionTime");

            binding.textClaim.setText("Donation Details:\n" +
                    "Description: " + description + "\n" +
                    "Location: " + location + "\n" +
                    "Quantity: " + quantity + "\n" +
                    "Donation Type: " + donationType + "\n" +
                    "Collection Time: " + collectionTime);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}