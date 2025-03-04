#!/bin/bash

# Copy to boost
echo
echo "~ Copy to boost ~"

# Copying libboost files
cp -v libs/arm64-v8a/libboost* boost/lib/arm64-v8a/
cp -v libs/armeabi-v7a/libboost* boost/lib/armeabi-v7a/
cp -v libs/x86_64/libboost* boost/lib/x86_64/

# Copy to Lunify
echo
echo "~ Copy to Lunify ~"

# Copying all files from libs to Lunify
cp -v libs/arm64-v8a/* lunify/lib/arm64-v8a/
cp -v libs/armeabi-v7a/* lunify/lib/armeabi-v7a/
cp -v libs/x86_64/* lunify/lib/x86_64/

# Deleting libs directory
echo
echo "~ Deleting libs directory ~"
rm -rf libs
if [ $? -eq 0 ]; then
    echo "libs directory deleted successfully!"
else
    echo "Failed to delete libs directory!"
fi
