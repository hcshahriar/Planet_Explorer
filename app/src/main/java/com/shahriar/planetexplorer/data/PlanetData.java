package com.shahriar.planetexplorer.data;

import com.shahriar.planetexplorer.R;

import java.util.ArrayList;
import com.shahriar.planetexplorer.model.Planet;

public class PlanetData {

    public static ArrayList<Planet> getPlanets() {

        ArrayList<Planet> planets = new ArrayList<>();

        planets.add(new Planet(
                "Mercury",
                "Terrestrial Planet",
                "57.9 million km",
                "3.7 m/s²",
                "0",
                "167°C",
                "EXTREME HEAT",
                "Mercury is the smallest planet and the closest planet to the Sun.",
                R.drawable.mercury
        ));

        planets.add(new Planet(
                "Venus",
                "Terrestrial Planet",
                "108.2 million km",
                "8.87 m/s²",
                "0",
                "464°C",
                "DANGEROUS",
                "Venus rotates in the opposite direction compared to most planets.",
                R.drawable.venus
        ));

        planets.add(new Planet(
                "Earth",
                "Terrestrial Planet",
                "149.6 million km",
                "9.8 m/s²",
                "1",
                "15°C",
                "SAFE",
                "Earth is the only known planet that supports life.",
                R.drawable.earth
        ));

        planets.add(new Planet(
                "Mars",
                "Terrestrial Planet",
                "227.9 million km",
                "3.71 m/s²",
                "2",
                "-63°C",
                "EXPLORATION READY",
                "Mars contains Olympus Mons, the largest volcano in the Solar System.",
                R.drawable.mars
        ));

        planets.add(new Planet(
                "Jupiter",
                "Gas Giant",
                "778.5 million km",
                "24.79 m/s²",
                "95",
                "-145°C",
                "LANDING IMPOSSIBLE",
                "Jupiter is the largest planet in our Solar System.",
                R.drawable.jupiter
        ));

        planets.add(new Planet(
                "Saturn",
                "Gas Giant",
                "1.43 billion km",
                "10.44 m/s²",
                "146",
                "-178°C",
                "RING HAZARD",
                "Saturn's spectacular rings are made mostly of ice and rock.",
                R.drawable.saturn
        ));

        planets.add(new Planet(
                "Uranus",
                "Ice Giant",
                "2.87 billion km",
                "8.69 m/s²",
                "28",
                "-224°C",
                "EXTREME COLD",
                "Uranus rotates on its side unlike any other planet.",
                R.drawable.uranus
        ));

        planets.add(new Planet(
                "Neptune",
                "Ice Giant",
                "4.50 billion km",
                "11.15 m/s²",
                "16",
                "-214°C",
                "STORM WARNING",
                "Neptune has the fastest winds in the Solar System.",
                R.drawable.neptune
        ));

        return planets;
    }
}