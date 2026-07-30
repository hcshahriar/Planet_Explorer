package com.shahriar.planetexplorer;

import com.shahriar.planetexplorer.model.Planet;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PlanetDetailActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private boolean isTtsReady = false;
    private String textToSpeak = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_detail);

        // Initialize Text-To-Speech Engine
        tts = new TextToSpeech(this, this);

        // Bind Views using exact XML IDs
        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView btnSpeak = findViewById(R.id.btnSpeak);
        ImageView detailImage = findViewById(R.id.detailImage);
        TextView detailName = findViewById(R.id.detailName);
        TextView detailType = findViewById(R.id.detailType);
        TextView detailDistance = findViewById(R.id.detailDistance);
        TextView detailGravity = findViewById(R.id.detailGravity);
        TextView detailMoons = findViewById(R.id.detailMoons);
        TextView detailTemperature = findViewById(R.id.detailTemperature);
        TextView detailMissionStatus = findViewById(R.id.detailMissionStatus);
        TextView detailFact = findViewById(R.id.detailFact);

        // Set Back Button Listener to close activity
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Handle Planet object passed from MainActivity
        if (getIntent() != null && getIntent().hasExtra("selected_planet")) {
            Planet planet;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                planet = getIntent().getSerializableExtra("selected_planet", Planet.class);
            } else {
                planet = (Planet) getIntent().getSerializableExtra("selected_planet");
            }

            if (planet != null) {
                if (detailName != null) detailName.setText(planet.getName());
                if (detailType != null) detailType.setText(planet.getType());
                if (detailDistance != null) detailDistance.setText(planet.getDistance());
                if (detailGravity != null) detailGravity.setText(planet.getGravity());
                if (detailMoons != null) detailMoons.setText(planet.getMoons());
                if (detailTemperature != null) detailTemperature.setText(planet.getTemperature());
                if (detailMissionStatus != null) detailMissionStatus.setText(planet.getMissionStatus());
                if (detailFact != null) detailFact.setText(planet.getFact());
                if (detailImage != null) detailImage.setImageResource(planet.getImage());

                textToSpeak = planet.getName() + ". " + planet.getFact();
            }
        }
        // Fallback: Handle individual Intent Extras
        else if (getIntent() != null) {
            String name = getIntent().getStringExtra("planet_name");
            String type = getIntent().getStringExtra("planet_type");
            String distance = getIntent().getStringExtra("planet_distance");
            String gravity = getIntent().getStringExtra("planet_gravity");
            String moons = getIntent().getStringExtra("planet_moons");
            String temp = getIntent().getStringExtra("planet_temp");
            String status = getIntent().getStringExtra("planet_status");
            String fact = getIntent().getStringExtra("planet_fact");
            int imageRes = getIntent().getIntExtra("planet_image", R.drawable.earth);

            if (detailName != null && name != null) detailName.setText(name);
            if (detailType != null && type != null) detailType.setText(type);
            if (detailDistance != null && distance != null) detailDistance.setText(distance);
            if (detailGravity != null && gravity != null) detailGravity.setText(gravity);
            if (detailMoons != null && moons != null) detailMoons.setText(moons);
            if (detailTemperature != null && temp != null) detailTemperature.setText(temp);
            if (detailMissionStatus != null && status != null) detailMissionStatus.setText(status);
            if (detailFact != null && fact != null) detailFact.setText(fact);
            if (detailImage != null) detailImage.setImageResource(imageRes);

            textToSpeak = java.util.Objects.requireNonNullElse(name, "") + ". " + 
                         java.util.Objects.requireNonNullElse(fact, "");
        }

        // Speaker Button Click Listener
        if (btnSpeak != null) {
            btnSpeak.setOnClickListener(v -> speakPlanetInfo());
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            switch (result) {
                case TextToSpeech.LANG_MISSING_DATA:
                case TextToSpeech.LANG_NOT_SUPPORTED:
                    Log.e("TTS", "Language not supported");
                    break;
                default:
                    isTtsReady = true;
                    break;
            }
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }

    private void speakPlanetInfo() {
        if (!isTtsReady) {
            Toast.makeText(this, "Audio engine loading...", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tts.isSpeaking()) {
            tts.stop();
        } else {
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, "PlanetTTS");
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}