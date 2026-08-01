#!/usr/bin/env bash
set -euo pipefail

SDK_ROOT="${ANDROID_HOME:-${ANDROID_SDK_ROOT:-$HOME/android-sdk}}"
TOOLS_VERSION="11076708"
TOOLS_URL="https://dl.google.com/android/repository/commandlinetools-linux-${TOOLS_VERSION}_latest.zip"

echo "Installing Android SDK into: ${SDK_ROOT}"
mkdir -p "${SDK_ROOT}/cmdline-tools"
TMP_ZIP="$(mktemp -t android-commandlinetools.XXXXXX.zip)"
trap 'rm -f "${TMP_ZIP}"' EXIT

curl -fL "${TOOLS_URL}" -o "${TMP_ZIP}"
unzip -q -o "${TMP_ZIP}" -d "${SDK_ROOT}/cmdline-tools"
rm -rf "${SDK_ROOT}/cmdline-tools/latest"
mv "${SDK_ROOT}/cmdline-tools/cmdline-tools" "${SDK_ROOT}/cmdline-tools/latest"

export ANDROID_HOME="${SDK_ROOT}"
export ANDROID_SDK_ROOT="${SDK_ROOT}"
export PATH="${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools:${PATH}"

yes | sdkmanager --licenses >/dev/null
sdkmanager \
  "platform-tools" \
  "platforms;android-35" \
  "build-tools;35.0.0"

echo "Android SDK ready. Add these to your shell:"
echo "export ANDROID_HOME=${ANDROID_HOME}"
echo "export ANDROID_SDK_ROOT=${ANDROID_SDK_ROOT}"
echo 'export PATH="$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH"'
