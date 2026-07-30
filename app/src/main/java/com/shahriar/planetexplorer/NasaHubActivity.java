package com.shahriar.planetexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class NasaHubActivity extends AppCompatActivity {

    private ImageView imgApod;
    private TextView txtApodTitle;
    private TextView txtApodExplanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_hub);

        ImageView btnBack = findViewById(R.id.btnBack);
        imgApod = findViewById(R.id.imgApod);
        txtApodTitle = findViewById(R.id.txtApodTitle);
        txtApodExplanation = findViewById(R.id.txtApodExplanation);

        // Find Mars Rover Card
        CardView cardMarsRover = findViewById(R.id.cardMarsRover);

        // Back button finishes activity
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Open MarsRoverActivity when the Mars Rover Card is clicked
        if (cardMarsRover != null) {
            cardMarsRover.setOnClickListener(v -> {
                Intent intent = new Intent(NasaHubActivity.this, MarsRoverActivity.class);
                startActivity(intent);
            });
        }

        // Fetch NASA APOD details
        fetchNasaApod();
    }

    private void fetchNasaApod() {
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String title = response.getString("title");
                            String explanation = response.getString("explanation");
                            String imageUrl = response.getString("url");

                            if (txtApodTitle != null) txtApodTitle.setText(title);
                            if (txtApodExplanation != null) txtApodExplanation.setText(explanation);

                            if (imgApod != null && !isFinishing() && !isDestroyed()) {
                                Glide.with(NasaHubActivity.this)
                                        .load(imageUrl)
                                        .placeholder(android.R.drawable.ic_menu_gallery)
                                        .error(android.R.drawable.ic_dialog_alert)
                                        .into(imgApod);
                            }

                        } catch (JSONException e) {
                            Log.e("NASA_HUB", "JSON parsing error: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("NASA_HUB", "Volley request error: " + error.toString());
                    }
                }
        );

        queue.add(jsonObjectRequest);
    }
}