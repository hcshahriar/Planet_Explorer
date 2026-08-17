# 🪐 Planet Explorer

<p align="center">
  <img src="https://img.shields.io/badge/Android-Application-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-Programming-orange?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/XML-UI-1572B6?style=for-the-badge&logo=xml&logoColor=white" />
  <img src="https://img.shields.io/badge/Android%20Studio-IDE-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white" />
</p>

<p align="center">
  <strong> Explore the Solar System. Discover new worlds. </strong>
</p>

<p align="center">
  A modern Android application for exploring the planets of our Solar System
  through interactive information, scientific data, and fascinating space facts.
</p>

---

##  About the Project

**Planet Explorer** is an Android application created to provide a simple, interactive,
and educational way to explore the **8 planets of our Solar System**.

Users can browse through the planets and open a dedicated details page containing
scientific information, space mission information, and interesting facts.

The project is being developed as a **university Android application project**
using **Java, XML, Android Studio, and Android SDK**.

---

## ✨ Features

### 🪐 Explore the Planets

The application currently includes all eight planets:

| Planet | Classification |
|:---:|:---|
|  Mercury | Terrestrial Planet |
|  Venus   | Terrestrial Planet |
|  Earth   | Terrestrial Planet |
|  Mars    | Terrestrial Planet |
|  Jupiter | Gas Giant |
| Saturn   | Gas Giant |
| Uranus   | Ice Giant |
|  Neptune | Ice Giant |

---

###  Scientific Information

Each planet provides useful information such as:

- 🌍 Planet type
- 📏 Distance from the Sun
- 🧲 Gravity
- 🌙 Number of moons
- 🌡️ Temperature

---

###  Space Mission Report

Each planet also contains a short space mission section describing
its exploration or mission-related information.

---

###  Interesting Facts

Every planet includes a short and easy-to-understand fact to help users
learn something interesting about that world.

---

##  Application Flow

```text
                PLANET EXPLORER
                       │
                       ▼
                ┌───────────────┐
                │  Main Screen  │
                └───────┬───────┘
                        │
                        ▼
                ┌───────────────┐
                │ Planet List   │
                │ RecyclerView  │
                └───────┬───────┘
                        │
                 Select a Planet
                        │
                        ▼
              ┌───────────────────┐
              │ Planet Details    │
              └─────────┬─────────┘
                        │
          ┌─────────────┼─────────────┐
          ▼             ▼             ▼
         Data        Mission        Facts


Technologies:

| Technology             | Purpose             |
| ---------------------- | ------------------- |
| ☕ Java                 | Application logic   |
| 🧩 XML                 | User interface      |
| 🤖 Android Studio      | Development         |
| 📦 Android SDK         | Android development |
| 📋 RecyclerView        | Planet list         |
| 🔗 Intent              | Screen navigation   |
| 🖼️ Drawable Resources  | Planet images       |
| ⚙️ Gradle              | Build system        |
| 📱 Android Emulator    | Testing             |


Project Structure:

Planet_Explorer/
│
├── app/
│   │
│   └── src/
│       └── main/
│           │
│           ├── java/
│           │   └── com/
│           │       └── shahriar/
│           │           └── planetexplorer/
│           │               │
│           │               ├── MainActivity.java
│           │               ├── PlanetDetailActivity.java
│           │               │
│           │               ├── adapter/
│           │               │   └── PlanetAdapter.java
│           │               │
│           │               ├── model/
│           │               │   └── Planet.java
│           │               │
│           │               └── data/
│           │                   └── PlanetData.java
│           │
│           ├── res/
│           │   │
│           │   ├── drawable/
│           │   │   ├── mercury.png
│           │   │   ├── venus.png
│           │   │   ├── earth.png
│           │   │   ├── mars.png
│           │   │   ├── jupiter.png
│           │   │   ├── saturn.png
│           │   │   ├── uranus.png
│           │   │   └── neptune.png
│           │   │
│           │   ├── layout/
│           │   │   ├── activity_main.xml
│           │   │   ├── activity_planet_detail.xml
│           │   │   └── item_planet.xml
│           │   │
│           │   └── values/
│           │       ├── colors.xml
│           │       ├── strings.xml
│           │       └── themes.xml
│           │
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md


How the Application Works
The basic process is:

PlanetData
     │
     ▼
Planet Model
     │
     ▼
PlanetAdapter
     │
     ▼
RecyclerView
     │
     ▼
User selects planet
     │
     ▼
Intent
     │
     ▼
PlanetDetailActivity
     │
     ▼
Detailed Planet Information



Development Progress.....
Phase 1 — Project Setup
Android Studio setup
Android SDK configuration
Android Emulator setup
Planet Explorer project creation
Initial application testing

Phase 2 — Basic User Interface
Main Activity
XML layout
Basic application interface
Android project structure

Phase 3 — Planet List
RecyclerView implementation
Planet model
Planet data
Planet adapter
Planet cards
8 planet images
Planet selection

Phase 4 — Planet Details
Planet Detail Activity
Intent navigation
Clickable planet cards
Detailed planet screen

Phase 4.5 — Scientific Information

Added:

Planet type
Distance from the Sun
Gravity
Number of moons
Temperature
Space mission status
Interesting facts
Improved dark space-themed UI
Phase 5 — Planned Improvements

Future development may include:

🔐 Login and Registration
🔎 Planet Search
❤️ Favorite Planets
⚖️ Planet Comparison
🧠 Space Quiz
🪐 Planet Animations
🌌 Interactive Solar System
🛰️ Space Mission Information
⚙️ Application Settings
💻 Requirements

To run Planet Explorer_you need:

Android Studio
Java / JDK
Android SDK
Android SDK Platform Tools
Android SDK Build Tools
Android Emulator or Android device
Git

Installation:

1. Clone the Repository
git clone https://github.com/hcshahriar/Planet_Explorer.git
2. Open in Android Studio

Open the downloaded project folder using Android Studio.

3. Wait for Gradle Sync

Allow Android Studio to download and configure the required dependencies.

4. Start an Emulator

Open:

Android Studio
→ Device Manager
→ Start Emulator

5. Run the Application

Click:
▶ Run

The application should launch on the Android Emulator.

Cross-Platform Support

The project can be developed on different operating systems.

🍎 macOS

Android Studio + Android SDK + Emulator

🪟 Windows

Android Studio + Android SDK + Emulator

The project source code remains the same.

Only operating-system-specific SDK paths may be different.

Resource Organization
Java Files
app/src/main/java/
XML Layouts
app/src/main/res/layout/
Planet Images
app/src/main/res/drawable/
Strings
app/src/main/res/values/strings.xml
Colors & Themes
app/src/main/res/values/

This organization keeps the project clean and easy to maintain.

Testing

The application is tested using the Android Emulator.

Testing includes:

Application launch
Planet list display
Planet images
Planet selection
Detail screen navigation
Scientific information
Screen scrolling
Dark theme
Different planet data


Learning Objectives:

This project helps develop practical skills in:

Java programming
Android application development
XML layouts
RecyclerView
Activities
Intents
Android resources
Gradle
Android SDK
Emulator testing
Git
GitHub
UI/UX design
Project organization


Collaboration:

Planet Explorer can be developed collaboratively using Git and GitHub.

Recommended workflow:

Clone Repository
       ↓
Pull Latest Changes
       ↓
Create / Modify Code
       ↓
Test Application
       ↓
Commit Changes
       ↓
Push to GitHub

This makes it possible for developers using macOS, Windows, or Linux
to work on the same Android project.

Future Vision:

The goal of Planet Explorer is to become a complete educational
Solar System application.

Future versions may provide:

🌌 Interactive Solar System
        ↓
🪐 Explore Planets
        ↓
🔬 Scientific Information
        ↓
🚀 Space Missions
        ↓
🧠 Learn Through Quizzes
        ↓
⭐ Save Favorite Worlds
