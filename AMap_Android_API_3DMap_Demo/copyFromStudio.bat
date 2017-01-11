cp -af android_studio/AMap3DDemo/app/src/main/java/ eclipse/AMap3DDemo/src
cp -af android_studio/AMap3DDemo/app/src/main/res/ eclipse/AMap3DDemo/res
cp -af android_studio/AMap3DDemo/app/src/main/AndroidManifest.xml eclipse/AMap3DDemo/AndroidManifest.xml
rm -rf eclipse/AMap3DDemo/libs
cp -af android_studio/AMap3DDemo/app/libs eclipse/AMap3DDemo/libs
