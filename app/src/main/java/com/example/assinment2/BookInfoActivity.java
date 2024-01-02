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


public class BookInfoActivity extends AppCompatActivity {

    TextView txtBookInfo;
    Button btnShowBook;
    EditText edtEnterBookTitle;
    private RequestQueue queue;
    ArrayList<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        setUpViews();
    }

    public void setUpViews (){
        queue = Volley.newRequestQueue(this);
        txtBookInfo = findViewById(R.id.txtBookInfo);
        btnShowBook = findViewById(R.id.btnShowBook);
        edtEnterBookTitle = findViewById(R.id.edtEnterBookTitle);
    }

    public void btnShowBookOnClick(View view){

        String URL = "https://freetestapi.com/api/v1/books";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String title = edtEnterBookTitle.getText().toString();
                boolean bookFound = false;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Book book = new Book(
                                obj.getInt("id"),
                                obj.getString("title"),
                                obj.getString("author"),
                                obj.getInt("publication_year"),
                                parseStringList(obj.getJSONArray("genre")),
                                obj.getString("description"),
                                obj.getString("cover_image")
                        );
                        books.add(book);
                    }catch(JSONException exception){
                        Log.d("volley_error", exception.toString());
                    }
                }

                for (int i = 0; i < books.size(); i++) {
                    Book book = books.get(i);
                    if (title.equalsIgnoreCase(book.getTitle())) {
                        txtBookInfo.setText(book.toString());
                        bookFound = true;
                        break;
                    }
                }
                if (!bookFound) {
                    txtBookInfo.setText("Book with this title does not exist!");
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