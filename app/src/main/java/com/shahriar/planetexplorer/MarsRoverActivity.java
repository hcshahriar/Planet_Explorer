package com.shahriar.planetexplorer;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MarsRoverActivity extends AppCompatActivity {

    private ImageView imgRoverPhoto;
    private TextView txtRoverName;
    private TextView txtCameraInfo;
    private TextView txtEarthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_rover);

        ImageView btnBackMars = findViewById(R.id.btnBackMars);
        imgRoverPhoto = findViewById(R.id.imgRoverPhoto);
        txtRoverName = findViewById(R.id.txtRoverName);
        txtCameraInfo = findViewById(R.id.txtCameraInfo);
        txtEarthDate = findViewById(R.id.txtEarthDate);

        if (btnBackMars != null) {
            btnBackMars.setOnClickListener(v -> finish());
        }

        fetchMarsRoverData();
    }

    private void fetchMarsRoverData() {
        // Fetch photos from Curiosity Sol 1000
        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray photos = response.getJSONArray("photos");
                            if (photos.length() > 0) {
                                JSONObject firstPhoto = photos.getJSONObject(0);

                                // Image URL (convert http to https for Android security)
                                String imgSrc = firstPhoto.getString("img_src").replace("http://", "https://");
                                String earthDate = firstPhoto.getString("earth_date");

                                JSONObject camera = firstPhoto.getJSONObject("camera");
                                String cameraFullName = camera.getString("full_name");

                                JSONObject rover = firstPhoto.getJSONObject("rover");
                                String roverName = rover.getString("name");

                                txtRoverName.setText("Rover: " + roverName);
                                txtCameraInfo.setText("Camera: " + cameraFullName);
                                txtEarthDate.setText("Earth Date: " + earthDate);

                                if (!isFinishing() && !isDestroyed()) {
                                    Glide.with(MarsRoverActivity.this)
                                            .load(imgSrc)
                                            .placeholder(android.R.drawable.ic_menu_gallery)
                                            .error(android.R.drawable.ic_dialog_alert)
                                            .into(imgRoverPhoto);
                                }
                            }
                        } catch (JSONException e) {
                            Log.e("MARS_ROVER", "JSON Parsing error: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MARS_ROVER", "Volley error: " + error.toString());
                    }
                }
        );

        queue.add(jsonObjectRequest);
    }
}