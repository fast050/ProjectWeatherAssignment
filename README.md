# Weather App

A simple weather application built with Jetpack Compose, designed to fetch weather data from a remote API and store it locally. The application is structured using a modular architecture with clear separation of concerns between different layers.


## Project Structure

The application is organized into four distinct modules:

### 1. `data` module
This module handles the data layer, which is responsible for fetching weather data from a **remote source** using Retrofit and storing it locally using **SharedPreferences**.

#### Key Responsibilities:
- Making network requests using Retrofit.
- Caching data locally using SharedPreferences.
- Providing data models for the other layers to consume.

#### Dependencies:
- Retrofit
- Gson
- SharedPreferences

### 2. `domain` module
The domain module contains all the business logic and use cases related to the weather data. It serves as the bridge between the **presentation layer** and the **data layer**, allowing the app to retrieve and process weather data.

#### Key Responsibilities:
- Containing use cases that encapsulate the logic for getting the weather data.
- Defining interfaces for the data layer.
- Processing or transforming data if needed before passing it to the presentation layer.

#### Dependencies:
- `data` module (depends on the `data` module to access remote and local data sources)

### 3. `presentation` module
The presentation module is where the **UI** is implemented using **Jetpack Compose**. It handles the user interface components, displays weather data, and responds to user actions (such as entering a city name to search for weather).

#### Key Responsibilities:
- Displaying weather data on the screen.
- Handling user interactions.
- Coordinating with the **domain module** to retrieve data.
  
#### Dependencies:
- `domain` module (depends on the `domain` module to interact with use cases and retrieve data)

### 4. `common` module
This is a shared module that contains common data models and utilities used across all other modules. It is independent of any Android-specific components and can be reused in other applications or projects.

#### Key Responsibilities:
- Defining shared data models like `CurrentWeather`, `WeatherForecast`, etc.
- Utility classes or helper functions that are shared across modules.


## Dependency Diagram

```plaintext
+-----------------+        +----------------+        +-------------------+       
|   Presentation  |  ----> |     Domain     |  ----> |      Data         | 
+-----------------+        +----------------+        +-------------------+ 
             |                     |                   |
             +----------------------------------------+--
             |                 Common                  |
             +----------------------------------------+--
```

- **`Presentation`** depends on **`Domain`** to access use cases and business logic.
- **`Domain`** depends on **`Data`** to interact with remote APIs and local storage.
- All modules depend on **`Common`** to use shared data models and utilities.

---

## How to Run the App

1. **Clone the repository**:
   ```bash
   git clone https://github.com/fast050/weather-app.git
   cd weather-app
   ```

2. Add your API Key:

Open the local.properties file located in the root of your project.
Add the following line:
properties
```bash
API_KEY=your_api_key_here
```
Replace your_api_key_here with your actual API key from the weather API provider.

3. **Build and Run**:
   - Select your target device/emulator.
   - Press **Run** (green play button) in Android Studio.

---

## Libraries and Technologies Used

- **Jetpack Compose**: For building the UI in a declarative manner.
- **Retrofit**: For making network requests to fetch weather data.
- **SharedPreferences**: For storing simple key-value pairs locally.
- **Hilt**: For dependency injection across modules.
- **Kotlin Coroutines**: For asynchronous operations like network requests.
- **Coil**: For image loading.
---

## Architecture

The application follows a **Clean Architecture** pattern, with clearly defined layers:

1. **Data Layer**: Responsible for fetching and storing data.
2. **Domain Layer**: Contains the use cases and business logic.
3. **Presentation Layer**: Responsible for the UI and user interactions.

This architecture ensures that the app is scalable, maintainable, and easy to test by isolating concerns in different modules.

---
