package com.example.assinment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MoviesApiActivity extends AppCompatActivity {
    private RequestQueue queue;
    private ListView lstMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_api);

        setupViews();
    }

    public void setupViews(){
        queue = Volley.newRequestQueue(this);
        lstMovies = findViewById(R.id.lstMovies);
    }

    public void btnViewMovieInfoOnClick(View view){
        Intent intent = new Intent(MoviesApiActivity.this, MovieInfoActivity.class);
        startActivity(intent);
    }

    public void btnGetMoviesOnClick(View view){

        String URL = "https://freetestapi.com/api/v1/movies";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> moviesTitle = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        moviesTitle.add(obj.getString("title"));
                    }catch(JSONException exception){
                        Log.d("volley_error", exception.toString());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MoviesApiActivity.this,
                        android.R.layout.simple_list_item_1, moviesTitle);
                lstMovies.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);
    }


}

