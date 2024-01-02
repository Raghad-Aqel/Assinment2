package com.example.assinment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseAPIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_api);
    }

    public void movieApiOnCLick (View view){
        Intent intent = new Intent(this, MoviesApiActivity.class);
        startActivity(intent);
    }

    public void bookApiOnCLick(View view){
        Intent intent = new Intent(this, BooksApiActivity.class);
        startActivity(intent);

    }
}