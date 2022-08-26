# SpaceX App - Applifting interview task
This project has been developed entirely from scratch and solely by Jan Růžička as part of an **interview** process for **applying for an Android developer position at Applifting.**

It is an Android application written in Kotlin and Jetpack Compose that connects to open-source SpaceX API, fetches data from it, and displays it to the user.

## 📱 Features
The app implements displaying data from 2 sections of the SpaceX REST API. On the start screen, it displays **information about SpaceX** such as valuation, current CEO... There is also another screen whose purpose is to show **rockets**. To switch between the screens, feel free to use the **navigation drawer**.

When starting the application for the first time, it displays a loading indicator as there is no data in the cache to display and the API request takes some time to finish. As soon as a response from the API is received, the app saves data to the local cache (implemented as a JSON file in the internal storage) and displays the data to the user.

Next time, the app shows you cached data straight away and initiates an API request in the background trying to fetch more up-to-date information. If the request fails, the app notifies you about it via a toast telling you that you are in **offline mode**. The app is, however, fully functional in the offline mode as well since it caches all the data received from the API.

If the API request fails and there is no data in the cache, then an **error message** is displayed to the user informing them about the issues with fetching data and offering them a button to try to fetch the data again.

The app is designed to fully support **the light** as well as **the dark theme** and it works both in **portrait** and **landscape** orientation.

## 🏗 Architecture
The app is built using the **MVVM** architecture. There are basically 3 layers: data, viewModels and UI (corresponding to the app package structure).

**The data layer** contains model classes representing the domain logic as well as classes responsible for handling data management. That includes code used for fetching data from the REST API, caching it locally, and handling data operations (repositories) initiated by the viewModel layer. The majority of the data layer should not be used elsewhere (in a different layer). The repositories are the intermediaries between the data and the rest of the application so the code from other layers interacts with the data via repositories (and model classes) only. Repositories are responsible for providing data and interface for data operations and for managing all the data sources (in this case the local cache and the remote REST API).

**The UI layer** is the home of all the UI-related code including @Composable functions, navigation logic, and theme logic. The code inside of this layer is responsible only for displaying data to the user. It depends on the viewModel layer as the screen-level composables ask for an instance of a particular viewModel to get data from.

**The viewModels layer** is the bridge between the data and the UI layer. ViewModel's job is to communicate with the data layer via repositories, get data from them and expose it in an observable way (in this case viewModels expose the data either in the form of compose state or SharedFlow). The UI layer should not have to manipulate the data much before it is displayed. ViewModels are supposed to prepare the data in such a way that the UI will not have to do any (or very little) work before it can display the data to the user.

## 🛠 Tools and frameworks used
- Language - Kotlin
- UI - Jetpack Compose
- Navigation - Jetpack Navigation Component for Compose
- Dependency Injection - Hilt
- REST API communication - Retrofit
- JSON parsing - Moshi
- Loading images - Coil
- Testing - JUnit
- Assertions in tests - Google Truth

## 📝 Assignment details
- Handle communication via a Space-X API ✅
  - The app communicates with the API with the help of Retrofit and fetches data from it.
- Present at least 2 separate Space-X API sections in your application (e.g. Company
Data, Rocket Data or Past Launches/Upcoming Launches) ✅
  - The app displays information about rockets and the company.
- Use at least 1 Space-X API section with a list/detail and present it this way in the
application (one screen with a list view of available data and a different screen with
the detail on tap). ✅
  - The rockets section is implemented this way. You see a list of rocket cards and on tap on each one of them you are navigated to a separate detail screen containing more information about that particular rocket.
- Use a navigation drawer to switch sections ✅
  - It can be opened either by clicking the hamburger icon in the top bar or by swiping right anywhere on the screen.
- Incorporate filtering by various parameters (e.g. allow filtering by launch_year and
rocket_id in Past Launches). ✅
  - Rockets can be filtered by activity (show only active / only inactive / all) and by minimum success rate (show only rockets with higher or equal success rate).
- Employ persistent memory (save and load data, e.g. cache start screen data into
persistent memory to be able to show it immediately after start without waiting for the
API request fo finish) ✅
  - The app caches data fetched from the API locally as a JSON file saved in the internal storage.
- Use one unit and one instrumentation test as an example of these features ✅
  - There are 2 unit tests (tests in the "test" source set running on JVM) and 2 instrumentation tests (tests in the "androidTest" source set running on an emulator or real Android device). One of the instrumentation tests is a test of a class that just requires android context and the other is a Compose UI test.
- Deadline: I promised to submit the assignment in 10-12 days. The initial meeting with Anička took place on Tuesday 16th, which means I am supposed to hand in the task between Friday 26th and Sunday 28th.

## ❗️ Important
This app has been developed by Jan Růžička as an **interview task for a job application at Applifting.**

The app or its author is not affiliated, associated, authorized, endorsed by, or in any way officially connected with Space Exploration Technologies Corp (SpaceX), or any of its subsidiaries or its affiliates. The names SpaceX as well as related names, marks, emblems and images are registered trademarks of their respective owners.
