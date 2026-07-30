package com.shahriar.planetexplorer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.shahriar.planetexplorer.data.PlanetData;
import com.shahriar.planetexplorer.model.Planet;

import java.util.ArrayList;
import java.util.List;

public class CompareActivity extends AppCompatActivity {

    private ImageView imgPlanet1, imgPlanet2;
    private TextView txtGravity1, txtGravity2;
    private TextView txtMoons1, txtMoons2;
    private TextView txtDistance1, txtDistance2;
    private TextView txtTemp1, txtTemp2;

    private List<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        // Bind Back Button
        ImageView btnBack = findViewById(R.id.btnBackCompare);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Bind Views
        imgPlanet1 = findViewById(R.id.imgPlanet1);
        imgPlanet2 = findViewById(R.id.imgPlanet2);

        txtGravity1 = findViewById(R.id.txtGravity1);
        txtGravity2 = findViewById(R.id.txtGravity2);

        txtMoons1 = findViewById(R.id.txtMoons1);
        txtMoons2 = findViewById(R.id.txtMoons2);

        txtDistance1 = findViewById(R.id.txtDistance1);
        txtDistance2 = findViewById(R.id.txtDistance2);

        txtTemp1 = findViewById(R.id.txtTemp1);
        txtTemp2 = findViewById(R.id.txtTemp2);

        Spinner spinnerPlanet1 = findViewById(R.id.spinnerPlanet1);
        Spinner spinnerPlanet2 = findViewById(R.id.spinnerPlanet2);

        // Fetch planet dataset
        planetList = PlanetData.getPlanets();

        // Extract planet names for the spinners
        List<String> planetNames = new ArrayList<>();
        for (Planet p : planetList) {
            planetNames.add(p.getName());
        }

        // Setup Spinner Adapters
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                planetNames
        );

        spinnerPlanet1.setAdapter(adapter);
        spinnerPlanet2.setAdapter(adapter);

        // Set default selections (e.g., Earth vs Mars)
        spinnerPlanet1.setSelection(2); // Earth
        spinnerPlanet2.setSelection(3); // Mars

        // Listener for Spinner 1
        spinnerPlanet1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePlanet1UI(planetList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Listener for Spinner 2
        spinnerPlanet2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePlanet2UI(planetList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void updatePlanet1UI(Planet planet) {
        imgPlanet1.setImageResource(planet.getImage());
        txtGravity1.setText(planet.getGravity());
        txtMoons1.setText(planet.getMoons());
        txtDistance1.setText(planet.getDistance());
        txtTemp1.setText(planet.getTemperature());
    }

    private void updatePlanet2UI(Planet planet) {
        imgPlanet2.setImageResource(planet.getImage());
        txtGravity2.setText(planet.getGravity());
        txtMoons2.setText(planet.getMoons());
        txtDistance2.setText(planet.getDistance());
        txtTemp2.setText(planet.getTemperature());
    }
}