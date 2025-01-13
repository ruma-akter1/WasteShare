package com.software.donate.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.software.donate.R;
import com.software.donate.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();

        EditText editTextEmail = binding.editTextEmail;
        EditText editTextPassword = binding.editTextPassword;
        Button buttonLogin = binding.buttonLogin;
        Button buttonRegister = binding.buttonRegister;

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            loginUser(email, password);
        });

        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            registerUser(email, password);
        });

        return root;
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getContext(), "Login successful.", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Login successful");
                        // Navigate to the dashboard or another fragment
                    } else {
                        Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Authentication failed");
                    }
                });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getContext(), "Registration successful.", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Registration successful");
                        // Navigate to the dashboard or another fragment
                    } else {
                        Toast.makeText(getContext(), "Registration failed.", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Registration failed");
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}