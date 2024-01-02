package com.example.assinment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

public class SignUpActivity extends AppCompatActivity {
    public static final String DATA ="DATA";
    private EditText edtNameSignUp;
    private EditText edtPasswordSingUp;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private User[] users;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupViews();
        setupSharedPrefs();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveUserData();
    }

    private void saveUserData() {
        String userString = gson.toJson(users);
        editor.putString(DATA, userString);
        editor.apply();
        Log.d("data", userString);
    }
    public void setupViews(){
        edtNameSignUp = findViewById(R.id.edtNameSignUp);
        edtPasswordSingUp = findViewById(R.id.edtPasswordSignUp);
    }

    public void setupSharedPrefs(){
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }
    public void btnSignUpOnClick2(View view) {
        String userName = edtNameSignUp.getText().toString();
        String password = edtPasswordSingUp.getText().toString();

        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        String str = preferences.getString(DATA,"");
        users = gson.fromJson(str,User[].class);
        if (users == null){
            users = new User[1];
            users[0]= new User(userName, password);
            Log.d("data", "First user added");
        }else {
            User user = new User(userName, password);
            for(int i =0 ; i< users.length;i++){
                if (users[i].equals(user)){
                    Toast.makeText(this, "User Exists!", Toast.LENGTH_SHORT).show();
                    Log.d("data", "User Exists!");
                    return;
                }
            }
            int oldLength = users.length;
            User [] tempUsers = new User[oldLength +1];
            for (int i=0;i<oldLength;i++){
                tempUsers[i] = users[i];
            }
            tempUsers[oldLength] = user;
            Log.d("data", "new user added ");
            users = tempUsers;

        }

        saveUserData();
        Intent intent = new Intent(this, ChooseAPIActivity.class);
        startActivity(intent);
    }
}
