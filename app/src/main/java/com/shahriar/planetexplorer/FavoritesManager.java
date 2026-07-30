package com.shahriar.planetexplorer;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {

    private static final String PREF_NAME = "planet_favorites_pref";
    private static final String KEY_FAVORITES = "favorite_planets";
    private final SharedPreferences prefs;

    public FavoritesManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // Save a planet as favorite
    public void addFavorite(String planetName) {
        Set<String> favorites = getFavorites();
        favorites.add(planetName);
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }

    // Remove a planet from favorites
    public void removeFavorite(String planetName) {
        Set<String> favorites = getFavorites();
        favorites.remove(planetName);
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }

    // Check if a planet is favorited
    public boolean isFavorite(String planetName) {
        return getFavorites().contains(planetName);
    }

    // Retrieve all favorited planet names
    public Set<String> getFavorites() {
        Set<String> set = prefs.getStringSet(KEY_FAVORITES, new HashSet<>());
        return new HashSet<>(set); // Return editable copy
    }
}