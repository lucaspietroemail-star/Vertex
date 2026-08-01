# Android SDK setup

Use `scripts/setup_android_sdk.sh` to install command-line tools, accept licenses, and install the Android 35 platform/build tools expected by this project.

```bash
scripts/setup_android_sdk.sh
```

The current CI/container must allow these endpoints:

- `https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip`
- Android SDK package repositories used by `sdkmanager`
- Gradle plugin/dependency repositories: Google Maven, Maven Central, Gradle Plugin Portal

After setup, run:

```bash
gradle :app:assembleDebug
gradle :core:security:testDebugUnitTest
gradle :feature_chat:testDebugUnitTest
```
