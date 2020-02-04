package com.example.assigment4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RequestQueue que;
    private Button button;
    private adapteri adapteri;
    private List<lista> list;
    private ListView listaview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button = findViewById(R.id.button);
        this.que = Volley.newRequestQueue(this);
        this.listaview = findViewById(R.id.listaview);
        list = new ArrayList<lista>();

        adapteri = new adapteri(this,R.layout.adapteri, list);
        listaview.setAdapter(adapteri);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String osoite = "https://jsonplaceholder.typicode.com/posts";

                JsonArrayRequest request = new JsonArrayRequest(osoite, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        lista lista;
                        ArrayList<lista> listas = new ArrayList<lista>();

                        Type type = new TypeToken<ArrayList<lista>>(){}.getType();
                        Gson gson = new Gson();
                        listas = gson.fromJson(response.toString(), type);

                        adapteri.addAll(listas);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.toString();
                    }
                });
                que.add(request);
                button.setVisibility(View.GONE);
            }
        });

        listaview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "ID: " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
