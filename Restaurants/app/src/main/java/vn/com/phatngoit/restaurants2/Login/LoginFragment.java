package vn.com.phatngoit.restaurants2.Login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import vn.com.phatngoit.restaurants2.CitySelect.CitySelectActivity;
import vn.com.phatngoit.restaurants2.MainActivity;
import vn.com.phatngoit.restaurants2.Option.OptionActivity;
import vn.com.phatngoit.restaurants2.R;
import vn.com.phatngoit.restaurants2.databinding.FragmentLoginBinding;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by HV on 6/10/2018.
 */

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private SharedPreferences sharedPreferences;
    public UserClass userClass;
    private CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        callbackManager = CallbackManager.Factory.create();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
//        updateUI(account);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("login", MODE_PRIVATE);
        getSharesPreference();

        if (userClass != null && !TextUtils.isEmpty(userClass.email)) {
            binding.etEmail.setText(userClass.email);
        }
        if (userClass != null && !TextUtils.isEmpty(userClass.password)) {
            binding.etPassword.setText(userClass.password);
        }
        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.showRegister(view);
            }
        });
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etEmail.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.Loi);
                    builder.setMessage(R.string.ChuaNhapEmail);
                    builder.setPositiveButton("OK", null);
                    builder.setCancelable(true);
                    builder.create();
                    builder.show();
                    return;
                }
                if (binding.etPassword.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.Loi);
                    builder.setMessage(R.string.ChuaNhapMatKhau);
                    builder.setPositiveButton("OK", null);
                    builder.setCancelable(true);
                    builder.create();
                    builder.show();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("User");

                reference.orderByChild("email").equalTo(binding.etEmail.getText().toString()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        UserClass userClass = dataSnapshot.getValue(UserClass.class);
                        if (userClass.getPassword().equals(binding.etPassword.getText().toString())) {
                            setSharedPreferences();
                            Intent intent = new Intent(container.getContext(), OptionActivity.class);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle(R.string.Loi);
                            builder.setMessage(R.string.EmailMatKhauKhongDung);
                            builder.setPositiveButton("OK", null);
                            builder.setCancelable(true);
                            builder.create();
                            builder.show();
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        loginFacebook(container);
        logiGmail(container);

//        if (isLoggedIn) {
//            Intent intent = new Intent(container.getContext(), CitySelectActivity.class);
//            startActivity(intent);
//        }
        return binding.getRoot();
    }

    //region Login Facebook
    void loginFacebook(final ViewGroup container) {
        binding.loginButton.setFragment(this);
        binding.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Đang tải dữ liệu...");
                progressDialog.show();
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
                        Intent intent = new Intent(container.getContext(), CitySelectActivity.class);
                        startActivity(intent);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location, password"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
                //binding.loginButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("onError");
                Log.v("LoginActivity", error.getCause().toString());
            }
        });
    }

    private Bundle getFacebookData(JSONObject object) {
        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        } catch (JSONException e) {
            Log.d("JSON", "Error parsing JSON");
        }
        return null;
    }

    //endregion

    //region Login Gmail
    void logiGmail(final ViewGroup container) {
        binding.signInButton.setSize(SignInButton.SIZE_STANDARD);
        binding.signInButton.setColorScheme(SignInButton.COLOR_LIGHT);
        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 2);
    }
    //endregion

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                callbackManager.onActivityResult(requestCode, resultCode, data);
                break;
            case 2:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Gmail", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }

    void setSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", binding.etEmail.getText().toString());
        editor.putString("password", binding.etPassword.getText().toString());
        editor.apply();
    }

    void getSharesPreference() {
        binding.etEmail.setText(sharedPreferences.getString("email", "").toString());
        binding.etPassword.setText(sharedPreferences.getString("password", "").toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
    }
}
