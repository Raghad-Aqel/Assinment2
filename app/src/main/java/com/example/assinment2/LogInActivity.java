package com.example.assinment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Arrays;


public class LogInActivity extends AppCompatActivity {

    public static final String DATA = "DATA";
    private EditText edtNameLogIn;
    private EditText edtPasswordLogIn;
    private CheckBox chkRememberMe;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button signUPButton;
    private Gson gson;
    private boolean rememberMeChecked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
        setupSharedPrefs();
        chkRememberMe.setOnCheckedChangeListener((buttonView, isChecked) -> rememberMeChecked = isChecked);

    }

    public void setupViews() {
        edtNameLogIn = findViewById(R.id.edtNameLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);
        chkRememberMe = findViewById(R.id.chkRememberMe);
        signUPButton = findViewById(R.id.btnSignUp);
        gson = new Gson();

    }

    public void setupSharedPrefs() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    public void btnLoginOnClick1(View view) {
        String userName = edtNameLogIn.getText().toString();
        String password = edtPasswordLogIn.getText().toString();
        User user = new User(userName, password);

        gson = new Gson();
        String str = preferences.getString(DATA, "");
        Log.d("Login", "Saved data: " + str);

        if (!str.equals("")) {
            User[] users = gson.fromJson(str, User[].class);

            for (int i = 0; i < users.length; i++) {
                if (users[i].equals(user)) {
                    Log.d("Login", "Logged in successfully");
                    for (int j = 0; j < users.length; j++) {
                        if (chkRememberMe.isChecked()) {
                            if (i == j) {
                                users[j] = user;
                                users[j].setRememberMe(true);
                            } else {
                                users[j].setRememberMe(false);
                            }
                        }
                    }

                    if (chkRememberMe.isChecked()) {
                        users[i] = user;
                        users[i].setRememberMe(true);
                        editor.putString(DATA, gson.toJson(users));
                        editor.putBoolean("RememberMeChecked", rememberMeChecked);
                        editor.apply();
                    } else {
                        users[i] = user;
                        users[i].setRememberMe(false);
                        editor.putString(DATA, gson.toJson(users));
                        editor.remove("RememberMeChecked");
                        editor.apply();
                    }

                    Intent intent = new Intent(this, ChooseAPIActivity.class);
                    startActivity(intent);
                    Log.d("HI", Arrays.toString(users));
                    return;
                }
                Log.d("users", users.toString());
            }
            Log.d("Login", "Username or password incorrect! Please try again!");
            Toast.makeText(this, "Username or password incorrect! Please try again", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "No Saved Data! Try to Sign up!", Toast.LENGTH_SHORT).show();
            Log.d("Login", "No users in stored data");
        }
    }

    public void btnSignUpOnClick(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String str = preferences.getString(DATA, "");
        boolean savedRememberMeChecked = preferences.getBoolean("RememberMeChecked", false);
        if (!str.isEmpty()) {
            User[] users = gson.fromJson(str, User[].class);
            for (int i = 0; i < users.length; i++) {
                if (users[i].isRememberMe()) {
                    edtNameLogIn.setText(users[i].getUsername());
                    edtPasswordLogIn.setText(users[i].getPassword());
                    chkRememberMe.setChecked(true);
                    break;
                }
            }
        }
    }
}

