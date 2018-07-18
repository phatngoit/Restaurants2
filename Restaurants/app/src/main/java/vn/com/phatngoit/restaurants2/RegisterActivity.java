package vn.com.phatngoit.restaurants2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import vn.com.phatngoit.restaurants2.databinding.RegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    RegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.register);
    }


    public void OpenLoginClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", binding.etEmail.getText().toString());
        intent.putExtra("password", binding.etPassword.getText().toString());

        setResult(RESULT_OK, intent);
        finish();

        startActivityForResult(intent, 1);
        //startActivity(intent);
    }
}
