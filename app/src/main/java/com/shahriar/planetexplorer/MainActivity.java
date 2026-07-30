package com.shahriar.planetexplorer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Local Model & Adapter Imports
import com.shahriar.planetexplorer.adapter.PlanetAdapter;
import com.shahriar.planetexplorer.model.Planet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Planet> planetList;
    private PlanetAdapter adapter;
    private boolean isShowingOnlyFavorites = false;
    private String currentSearchQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);
        ImageView btnNasaHub = findViewById(R.id.btnNasaHub);
        ImageView btnFavFilter = findViewById(R.id.btnFavFilter);
        ImageView btnCompare = findViewById(R.id.btnCompare);
        ImageView btnQuiz = findViewById(R.id.btnQuiz);
        ImageView btnLogout = findViewById(R.id.btnLogout);

        // --- NASA Hub Twinkle Animation & Navigation ---
        if (btnNasaHub != null) {
            // Load and start twinkling star animation
            try {
                Animation twinkleAnim = AnimationUtils.loadAnimation(this, R.anim.star_twinkle);
                btnNasaHub.startAnimation(twinkleAnim);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Open NasaHubActivity on click
            btnNasaHub.setOnClickListener(v -> {
                // Clear active animation before transitioning to prevent glitches
                btnNasaHub.clearAnimation();

                Intent intent = new Intent(MainActivity.this, NasaHubActivity.class);
                startActivity(intent);

                // Safe custom transition execution
                try {
                    overridePendingTransition(R.anim.starlight_expand, android.R.anim.fade_out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Style Search Text & Icons for dark theme
        if (searchView != null) {
            EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
            if (searchEditText != null) {
                searchEditText.setTextColor(Color.WHITE);
                searchEditText.setHintTextColor(Color.GRAY);
            }

            ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
            if (searchIcon != null) {
                searchIcon.setColorFilter(Color.WHITE);
            }

            // Search Filter Listener
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    currentSearchQuery = newText;
                    applyFilters();
                    return true;
                }
            });
        }

        // Load Planet Data
        loadPlanetData();

        // Setup Adapter
        if (recyclerView != null) {
            adapter = new PlanetAdapter(this, planetList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

        // Favorites Filter Button Click Listener
        if (btnFavFilter != null) {
            btnFavFilter.setOnClickListener(v -> {
                isShowingOnlyFavorites = !isShowingOnlyFavorites;

                if (isShowingOnlyFavorites) {
                    btnFavFilter.setImageResource(R.drawable.ic_heart_filled);
                } else {
                    btnFavFilter.setImageResource(R.drawable.ic_heart_outline);
                }

                applyFilters();
            });
        }

        // Compare Button Click Listener
        if (btnCompare != null) {
            btnCompare.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CompareActivity.class);
                startActivity(intent);
            });
        }

        // Quiz Button Click Listener
        if (btnQuiz != null) {
            btnQuiz.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            });
        }

        // Logout Button Click Listener
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                SharedPreferences pref = getSharedPreferences("UserSession", MODE_PRIVATE);
                pref.edit().clear().apply();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }

    private void applyFilters() {
        if (planetList == null || adapter == null) return;
        List<Planet> filteredList = new ArrayList<>();

        for (Planet planet : planetList) {
            boolean matchesSearch = planet.getName().toLowerCase().contains(currentSearchQuery.toLowerCase()) ||
                    planet.getFact().toLowerCase().contains(currentSearchQuery.toLowerCase());

            boolean matchesFavorite = !isShowingOnlyFavorites || planet.isFavorited();

            if (matchesSearch && matchesFavorite) {
                filteredList.add(planet);
            }
        }

        adapter.filterList(filteredList);
    }

    private void loadPlanetData() {
        planetList = new ArrayList<>();

        planetList.add(new Planet("Mercury", "Terrestrial", "57.9M km", "3.7 m/s²", "0", "167°C", "Visited", "Smallest planet in the Solar System.", R.drawable.mercury));
        planetList.add(new Planet("Venus", "Terrestrial", "108.2M km", "8.87 m/s²", "0", "464°C", "Visited", "Hottest planet in our solar system.", R.drawable.venus));
        planetList.add(new Planet("Earth", "Terrestrial", "149.6M km", "9.8 m/s²", "1", "15°C", "Home", "Our home planet and the only known life oasis.", R.drawable.earth));
        planetList.add(new Planet("Mars", "Terrestrial", "227.9M km", "3.71 m/s²", "2", "-65°C", "Active Rovers", "Known as the Red Planet.", R.drawable.mars));
        planetList.add(new Planet("Jupiter", "Gas Giant", "778.5M km", "24.79 m/s²", "95", "-110°C", "Jupiter Active", "Largest planet with a Great Red Spot storm.", R.drawable.jupiter));
        planetList.add(new Planet("Saturn", "Gas Giant", "1.4B km", "10.44 m/s²", "146", "-140°C", "Cassini Mission", "Famous for its extensive and complex ring system.", R.drawable.saturn));
        planetList.add(new Planet("Uranus", "Ice Giant", "2.9B km", "8.69 m/s²", "28", "-195°C", "Voyager 2 Flyby", "Rotates on its side with extreme axial tilt.", R.drawable.uranus));
        planetList.add(new Planet("Neptune", "Ice Giant", "4.5B km", "11.15 m/s²", "16", "-200°C", "Voyager 2 Flyby", "Dark, cold, and whipped by supersonic winds.", R.drawable.neptune));
    }
}