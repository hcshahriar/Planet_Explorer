package com.shahriar.planetexplorer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shahriar.planetexplorer.FavoritesManager;
import com.shahriar.planetexplorer.PlanetDetailActivity;
import com.shahriar.planetexplorer.R;
import com.shahriar.planetexplorer.model.Planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {

    private final Context context;
    private List<Planet> planetList;
    private final FavoritesManager favoritesManager;

    public PlanetAdapter(Context context, List<Planet> planetList) {
        this.context = context;
        this.planetList = new ArrayList<>(planetList);
        this.favoritesManager = new FavoritesManager(context);

        // Sync initial favorited state from SharedPreferences
        for (Planet planet : this.planetList) {
            boolean isSaved = favoritesManager.isFavorite(planet.getName());
            planet.setFavorited(isSaved);
        }
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planet, parent, false);
        return new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder holder, int position) {
        Planet planet = planetList.get(position);

        holder.txtPlanetName.setText(planet.getName());
        holder.txtPlanetDescription.setText(planet.getFact());
        holder.imgPlanet.setImageResource(planet.getImage());

        // Update heart icon UI
        updateHeartIcon(holder.imgHeart, planet.isFavorited());

        // Heart Icon Click Listener
        holder.imgHeart.setOnClickListener(v -> {
            boolean isFav = !planet.isFavorited();
            planet.setFavorited(isFav);

            if (isFav) {
                favoritesManager.addFavorite(planet.getName());
            } else {
                favoritesManager.removeFavorite(planet.getName());
            }

            animateHeart(holder.imgHeart, isFav);
        });

        // Card Click Listener -> Open Detail Screen
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlanetDetailActivity.class);
            intent.putExtra("planet_name", planet.getName());
            intent.putExtra("planet_type", planet.getType());
            intent.putExtra("planet_distance", planet.getDistance());
            intent.putExtra("planet_gravity", planet.getGravity());
            intent.putExtra("planet_moons", planet.getMoons());
            intent.putExtra("planet_temp", planet.getTemperature());
            intent.putExtra("planet_status", planet.getMissionStatus());
            intent.putExtra("planet_fact", planet.getFact());
            intent.putExtra("planet_image", planet.getImage());
            intent.putExtra("planet_is_fav", planet.isFavorited());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }

    public void filterList(List<Planet> newList) {
        this.planetList = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    private void updateHeartIcon(ImageView heartView, boolean isFavorited) {
        if (isFavorited) {
            heartView.setImageResource(R.drawable.ic_heart_filled);
        } else {
            heartView.setImageResource(R.drawable.ic_heart_outline);
        }
    }

    private void animateHeart(ImageView heartView, boolean isFavorited) {
        heartView.animate()
                .scaleX(1.3f)
                .scaleY(1.3f)
                .setDuration(150)
                .withEndAction(() -> {
                    updateHeartIcon(heartView, isFavorited);
                    heartView.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .setDuration(150)
                            .start();
                })
                .start();
    }

    public static class PlanetViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPlanet;
        final ImageView imgHeart;
        final TextView txtPlanetName;
        final TextView txtPlanetDescription;

        public PlanetViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPlanet = itemView.findViewById(R.id.imgPlanet);
            imgHeart = itemView.findViewById(R.id.imgHeart);
            txtPlanetName = itemView.findViewById(R.id.txtPlanetName);
            txtPlanetDescription = itemView.findViewById(R.id.txtPlanetDescription);
        }
    }
}