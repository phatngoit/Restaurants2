package vn.com.phatngoit.restaurants2.Login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.com.phatngoit.restaurants2.MainActivity;
import vn.com.phatngoit.restaurants2.R;
import vn.com.phatngoit.restaurants2.databinding.FragmentRegisterBinding;

/**
 * Created by HV on 6/10/2018.
 */

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isError = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setPositiveButton("OK", null);
                builder.setTitle(R.string.Loi);
                if (binding.etFullName.getText().toString().equals("")) {
                    isError = true;
                    builder.setMessage(R.string.ChuaNhapHoTen);
                    builder.create();
                    builder.show();
                } else if (binding.etEmail.getText().toString().equals("")) {
                    isError = true;
                    builder.setMessage(R.string.ChuaNhapEmail);
                    builder.create();
                    builder.show();
                } else if (!isEmailValid(binding.etEmail.getText().toString())) {
                    isError = true;
                    builder.setMessage(R.string.EmailKhongHopLe);
                    builder.create();
                    builder.show();
                } else if (binding.etPhoneNumber.getText().toString().equals("")) {
                    isError = true;
                    builder.setMessage(R.string.ChuaNhapMatKhau);
                    builder.create();
                    builder.show();
                } else if (binding.etPassword.getText().toString().equals("")) {
                    isError = true;
                    builder.setMessage(R.string.ChuaNhapHoTen);
                    builder.create();
                    builder.show();
                }
                if (isError) return;
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                String fullName = binding.etFullName.getText().toString();
                String phoneNumber = binding.etPhoneNumber.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("User");

                UserClass userClass = new UserClass(email, password, fullName, phoneNumber);
                String id = UUID.randomUUID().toString();
                myRef.child(id).setValue(userClass);

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.showLogin(view, email, password);
            }
        });
        return binding.getRoot();
    }

    private Dialog onCreateDialog(EditText editText) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo");
        if (editText.getId() == binding.etEmail.getId()) {
            if (binding.etEmail.getText().toString().equals("")) {
                builder.setMessage(R.string.ChuaNhapEmail);
            } else if (isEmailValid(binding.etEmail.getText().toString())) {
                builder.setMessage(R.string.EmailKhongHopLe);
            }
        } else if (editText.getId() == binding.etFullName.getId()) {
            if (binding.etFullName.getText().toString().equals("")) {
                builder.setMessage(R.string.ChuaNhapHoTen);
            }
        } else if (editText.getId() == binding.etPassword.getId()) {
            if (binding.etPassword.getText().toString().equals("")) {
                builder.setMessage(R.string.ChuaNhapHoTen);
            }
        } else if (editText.getId() == binding.etPhoneNumber.getId()) {
            if (binding.etPhoneNumber.getText().toString().equals("")) {
                builder.setMessage(R.string.ChuaNhapMatKhau);
            }
        }
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
