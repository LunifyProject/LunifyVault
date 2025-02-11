#!/bin/bash

# Unzipping
echo "~ Making directories ~"

# Create directories silently (no error message if directory already exists)
mkdir -p libs/arm64-v8a
if [ $? -eq 0 ]; then
    echo "Created libs/arm64-v8a directory successfully!"
else
    echo "Failed to create libs/arm64-v8a directory!"
fi

mkdir -p libs/armeabi-v7a
if [ $? -eq 0 ]; then
    echo "Created libs/armeabi-v7a directory successfully!"
else
    echo "Failed to create libs/armeabi-v7a directory!"
fi

mkdir -p libs/x86_64
if [ $? -eq 0 ]; then
    echo "Created libs/x86_64 directory successfully!"
else
    echo "Failed to create libs/x86_64 directory!"
fi

# Unzipping
echo
echo "~ Unzipping archives ~"

# Unzipping WalletAPI ARM v8.zip
tar -xf "WalletAPI ARM v8.zip" -C "libs/arm64-v8a"
if [ $? -eq 0 ]; then
    echo "Unzipped WalletAPI ARM v8.zip successfully!"
    rm -f "WalletAPI ARM v8.zip"
    echo "Deleted WalletAPI ARM v8.zip"
else
    echo "Unzipped WalletAPI ARM v8.zip failed!"
fi

# Unzipping WalletAPI ARM v7.zip
tar -xf "WalletAPI ARM v7.zip" -C "libs/armeabi-v7a"
if [ $? -eq 0 ]; then
    echo "Unzipped WalletAPI ARM v7.zip successfully!"
    rm -f "WalletAPI ARM v7.zip"
    echo "Deleted WalletAPI ARM v7.zip"
else
    echo "Unzipped WalletAPI ARM v7.zip failed!"
fi

# Unzipping WalletAPI x86_64 Linux.zip
tar -xf "WalletAPI x86_64 Linux.zip" -C "libs/x86_64"
if [ $? -eq 0 ]; then
    echo "Unzipped WalletAPI x86_64 Linux.zip successfully!"
    rm -f "WalletAPI x86_64 Linux.zip"
    echo "Deleted WalletAPI x86_64 Linux.zip"
else
    echo "Unzipped WalletAPI x86_64 Linux.zip failed!"
fi

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
