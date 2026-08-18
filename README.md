# AndroidApp1 — Daily Motivation

**Course:** MWD3B — Android Development  
**Assignment:** Assignment 3  
**Student:** Ahmad Wahidi  
**Software:** Android Studio and Git

## Project summary

Daily Motivation is a one-button Android application. When the user presses **Inspire Me**, the app presents a new positive message and emoji, changes the background colour theme, updates the inspiration counter, and animates the message card.

The project applies the same beginner Android concepts demonstrated in the TimeFighter app: an Activity, XML layout, view references, a button click listener, state variables, resource files, and screen updates.

## Features

- One clear **Inspire Me** button
- Eight motivational messages with matching emoji
- A different gradient theme for each message
- Non-repeating random message selection
- Tap counter with correct singular and plural text
- Fade-and-scale animation after every tap
- Light haptic feedback on supported devices
- State restoration after screen rotation or Activity recreation
- Scrollable, accessible layout for different phone sizes
- Content descriptions for the changing emoji
- Unit tests for the non-repeating selection logic

## Project structure

```text
AndroidApp1/
├── app/src/main/java/com/ahmadwahidi/androidapp1/
│   ├── MainActivity.kt
│   └── MotivationSelector.kt
├── app/src/main/res/
│   ├── drawable/          # Gradient, card, button, and icon resources
│   ├── layout/            # Main screen XML
│   └── values/            # Strings, colours, and themes
├── app/src/test/          # MotivationSelector unit tests
├── docs/                  # Assignment checklist
└── SUBMISSION_MESSAGE.txt
```

## Requirements

- Android Studio Quail or a compatible recent version
- Android SDK 36
- JDK 17
- Android device or emulator running Android 7.0 (API 24) or newer

## Open and run

1. Extract the project ZIP.
2. Open Android Studio.
3. Select **Open** and choose the `AndroidApp1` folder.
4. Allow Gradle Sync to finish and install SDK 36 if Android Studio requests it.
5. Select an emulator or connected Android phone.
6. Click **Run app**.

## Run the unit tests

In Android Studio, right-click `MotivationSelectorTest` and select **Run**. You can also run:

```bash
./gradlew test
```

## Manual testing checklist

1. Launch the app and confirm the welcome message is visible.
2. Tap **Inspire Me** and confirm the message, emoji, background, and counter change.
3. Tap several times and confirm the same message never appears twice in a row.
4. Confirm the message card animates on each tap.
5. Rotate the emulator and confirm the current message and counter remain unchanged.
6. Relaunch the app and verify that it remains responsive on a small phone emulator.

## GitHub repository

The configured origin is:

<https://github.com/awahidi17/AndroidApp1.git>

After creating an empty repository named `AndroidApp1` in the `awahidi17` GitHub account, push the committed project with:

```bash
git push -u origin main
```

## Rubric alignment

| Criterion | Evidence in this project |
| --- | --- |
| Features | Complete one-button experience with messages, themes, animation, counter, haptics, and state restoration |
| Functionality | Button logic is implemented in `MainActivity`; selection behaviour is isolated and unit tested |
| Commenting | Kotlin classes, functions, important state, XML sections, and resource choices are clearly commented |

Created by Ahmad Wahidi.
