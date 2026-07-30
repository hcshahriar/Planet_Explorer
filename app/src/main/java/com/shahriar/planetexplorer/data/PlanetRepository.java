package com.shahriar.planetexplorer.data;

import com.shahriar.planetexplorer.R;

import java.util.ArrayList;
import java.util.List;
import com.shahriar.planetexplorer.model.Planet;

public class PlanetRepository {

    public static List<Planet> getPlanets() {
        List<Planet> planets = new ArrayList<>();

        planets.add(new Planet(
                "mercury",
                "Mercury",
                "Smallest planet in the Solar System.",
                "Mercury is the smallest planet in the Solar System and the closest to the Sun. Its orbit around the Sun takes 87.97 Earth days.",
                R.drawable.mercury
        ));

        planets.add(new Planet(
                "venus",
                "Venus",
                "Hottest planet in our solar system.",
                "Venus is the second planet from the Sun. It has a thick, toxic atmosphere that traps heat, making it the hottest planet in our solar system.",
                R.drawable.venus
        ));

        planets.add(new Planet(
                "earth",
                "Earth",
                "Our home planet.",
                "Earth is the third planet from the Sun and the only astronomical object known to harbor life.",
                R.drawable.earth
        ));

        planets.add(new Planet(
                "mars",
                "Mars",
                "The Red Planet.",
                "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System, being larger than only Mercury.",
                R.drawable.mars
        ));

        planets.add(new Planet(
                "jupiter",
                "Jupiter",
                "Largest planet in the Solar System.",
                "Jupiter is the fifth planet from the Sun and the largest in the Solar System. It is a gas giant with a mass more than two and a half times that of all the other planets combined.",
                R.drawable.jupiter
        ));

        return planets;
    }
}