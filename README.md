# WeatherApp

WeatherApp is a Java Swing desktop application that fetches and displays current weather data for any city that users can search for, using the OpenWeatherMap API.

## Features

- Search weather by city name
- Shows current temperature in Celsius
- Shows "feels like" temperature
- Displays weather description, cloud cover, visibility, wind speed, and wind direction
- Downlaods and display the matching weather icon

## Tech Stack

- Java
- Swing UI
- OpenWeatherMap API
- `json-simple` for JSON parsing

## How It Works

The app sends a request to OpenWeatherMap for the city entered by the user, parses the JSON response into a custom weather data structure, and updates the UI with the results.

## Build and Run

This project was created as a NetBeans Java application.

### Oepn Weather Map API Key

- Go to https://openweathermap.org/api
- Get your personla API Key for free
- Then, go to the CMainForm.java file
- Find the function called getInfo()
- Paste your key in the variable called apiKey
- Ready to proceed

### From NetBeans

1. Open the project in NetBeans.
2. Run the `WeatherApp` project.
3. Enter a city name and click `Go`, or press Enter.

### From the command line

If you already have the compiled distribution in `dist/`, run:

```bash
java -jar dist/WeatherApp.jar
```

## Project Structure

- `src/data` - weather data parsing and custom collection classes
- `src/UX` - Swing user interface
- `src/icons` - helper for loading and resizing weather icons
- `src/Libs` - third-party libraries

## Notes

- The app relies on an OpenWeatherMap API key to fetch live weather data.
- A weather icon is downloaded locally as `weather_icon.png` when results are loaded.

