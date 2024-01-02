package com.example.assinment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import java.util.List;

public class MovieInfoActivity extends AppCompatActivity {
    TextView txtMovieInfo;
    Button btnShowMovie;
    EditText edtEnterMovieTitle;

    private RequestQueue queue;
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        setUpViews();
    }

    public void setUpViews (){
        queue = Volley.newRequestQueue(this);
        txtMovieInfo = findViewById(R.id.txtMovieInfo);
        btnShowMovie = findViewById(R.id.btnShowMovie);
        edtEnterMovieTitle = findViewById(R.id.edtEnterMovieTitle);
    }

    public void btnShowMovieOnClick(View view){

        String URL = "https://freetestapi.com/api/v1/movies";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String title = edtEnterMovieTitle.getText().toString();
                boolean movieFound = false;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Movie movie = new Movie(
                                obj.getInt("id"),
                                obj.getString("title"),
                                obj.getInt("year"),
                                parseStringList(obj.getJSONArray("genre")),
                                obj.getDouble("rating"),
                                obj.getString("director"),
                                parseStringList(obj.getJSONArray("actors")),
                                obj.getString("plot"),
                                obj.getString("poster"),
                                obj.getString("trailer"),
                                obj.getInt("runtime"),
                                obj.getString("awards"),
                                obj.getString("country"),
                                obj.getString("language"),
                                obj.getString("boxOffice"),
                                obj.getString("production"),
                                obj.getString("website")

                        );
                        movies.add(movie);
                    }catch(JSONException exception){
                        Log.d("volley_error", exception.toString());
                    }
                }

                for (int i = 0; i < movies.size(); i++) {
                    Movie movie = movies.get(i);
                    if (title.equalsIgnoreCase(movie.getTitle())) {
                        txtMovieInfo.setText(movie.toString());
                        movieFound = true;
                        break;
                    }
                }
                if (!movieFound) {
                    txtMovieInfo.setText("Movie with this title does not exist!");
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);


    }

    private List<String> parseStringList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

}