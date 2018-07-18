package vn.com.phatngoit.restaurants2;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import vn.com.phatngoit.restaurants2.Login.UserClass;
import vn.com.phatngoit.restaurants2.Login.LoginFragment;
import vn.com.phatngoit.restaurants2.Login.RegisterFragment;
import vn.com.phatngoit.restaurants2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    //SharedPreferences sharedPreferences;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Intent intent = getIntent();
        //String email = intent.getStringExtra("email");
        //String pass = intent.getStringExtra("password");
        //binding.etEmail.setText(email);
        //binding.etPassword.setText(pass);

//        getFontsAssets();

        //sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        //getSharedPreference();

        fragmentManager = getSupportFragmentManager();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this.getApplication());
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "vn.com.phatngoit.restaurants2",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        showLogin(null, null, null);
    }

//    void getSharedPreference()
//    {
//        binding.etEmail.setText(sharedPreferences.getString("email", "").toString());
//        binding.etPassword.setText(sharedPreferences.getString("pwd", "").toString());
//    }

//    void setSharedPreferences()
//    {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("email", binding.etEmail.getText().toString());
//        editor.putString("password", binding.etPassword.getText().toString());
//        editor.apply();
//    }

//    void getFontsAssets()
//    {
//        AssetManager assetManager = getAssets();
//        Typeface typeface = Typeface.createFromAsset(assetManager, "fonts/Bungasai.ttf");
//        binding.tvLogin.setTypeface(typeface);
//    }

//    public void OpenSignUp(View view) {
//        Intent intent = new Intent(this, RegisterActivity.class);
//        //startActivity(intent);
//        startActivityForResult(intent, 1);
//
//    }

    public void showLogin(View view, String email, String password)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        LoginFragment fragmentLogin = new LoginFragment();
        fragmentLogin.userClass = new UserClass();
        fragmentLogin.userClass.email = email;
        fragmentLogin.userClass.password = password;
        transaction.replace(R.id.container, fragmentLogin);
        transaction.commit();
    }

    public void showRegister(View view){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        transaction.replace(R.id.container, registerFragment);
        transaction.commit();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK)
//        {
//            if (requestCode == 1)
//            {
//                String email = data.getStringExtra("email");
//                String pass = data.getStringExtra("password");
//                binding.etEmail.setText(email);
//                binding.etPassword.setText(pass);
//            }
//        }
//    }

    public void openCitySelect(View view) {
        Log.d("abcd", "abc");
//        if (binding.etEmail.getText().toString().equals(""))
//        {
//            Toast.makeText(getApplication(), "Chưa nhập email", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (binding.etPassword.getText().toString().equals(""))
//        {
//            Toast.makeText(getApplication(), "Chưa nhập mật khẩu", Toast.LENGTH_LONG).show();
//            return;
//        }
//        setSharedPreferences();
//
//        Intent intent = new Intent(this, CitySelectActivity.class);
//        startActivity(intent);
//        //startActivityForResult(intent, 2);
    }
}
