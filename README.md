# terms-create-and-revise
Android app for creating your vocabularies with terms and practising them.

### Quick start:

```bash
git clone https://github.com/SvichkarevAnatoly/terms-create-and-revise.git
cd terms-create-and-revise/
# create .apk
./gradlew assembleDebug
# install on device
adb install -r "full/path/to/terms-create-and-revise/app/build/outputs/apk/app-debug.apk"
# launch on device
adb shell am start -a android.intent.action.MAIN -n com.example.anatoly.myapplication/.MainActivity
```