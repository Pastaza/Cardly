Cardly Manual Input - Android project

This is a simple Android Studio project for the Cardly app using manual input (no camera).
Features:
- Main screen: list of saved cards
- Add card screen: Brand, Expiry Date (DatePicker), Notes
- Local persistent storage using SharedPreferences (JSON via Gson)

How to build:
1. Install Android Studio and SDK (API 34).
2. Open this folder as a project in Android Studio.
3. Let Gradle sync, install any missing SDK components.
4. Build -> Build Bundle(s) / APK(s) -> Build APK(s)
5. The generated APK will be in app/build/outputs/apk/debug/

Notes:
- This is an MVP skeleton. Add input validation, deletion/editing, and backups as needed.
- For production, add proper app signing, privacy policy, and data export options.
