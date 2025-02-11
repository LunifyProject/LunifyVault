@echo off

rem Unzipping
echo ~ Making directories ~
mkdir libs 2>nul
IF %ERRORLEVEL% EQU 0 (
    echo Created libs directory successfully!
) ELSE (
    echo Failed to create libs directory!
)

mkdir libs\arm64-v8a 2>nul
IF %ERRORLEVEL% EQU 0 (
    echo Created libs\arm64-v8a directory successfully!
) ELSE (
    echo Failed to create libs\arm64-v8a directory!
)

mkdir libs\armeabi-v7a 2>nul
IF %ERRORLEVEL% EQU 0 (
    echo Created libs\armeabi-v7a directory successfully!
) ELSE (
    echo Failed to create libs\armeabi-v7a directory!
)

mkdir libs\x86_64 2>nul
IF %ERRORLEVEL% EQU 0 (
    echo Created libs\x86_64 directory successfully!
) ELSE (
    echo Failed to create libs\x86_64 directory!
)

rem Unzipping
echo.
echo ~ Unzipping archives ~
tar -xf "WalletAPI ARM v8.zip" -C "libs\arm64-v8a"
IF %ERRORLEVEL% EQU 0 (
    echo Unzip WalletAPI ARM v8.zip successfully!
    del /Q "WalletAPI ARM v8.zip"
    echo Deleted WalletAPI ARM v8.zip
) ELSE (
    echo Unzip WalletAPI ARM v8.zip failed!
)

tar -xf "WalletAPI ARM v7.zip" -C "libs\armeabi-v7a"
IF %ERRORLEVEL% EQU 0 (
    echo Unzip WalletAPI ARM v7.zip successfully!
    del /Q "WalletAPI ARM v7.zip"
    echo Deleted WalletAPI ARM v7.zip
) ELSE (
    echo Unzip WalletAPI ARM v7.zip failed!
)

tar -xf "WalletAPI x86_64 Linux.zip" -C "libs\x86_64"
IF %ERRORLEVEL% EQU 0 (
    echo Unzip WalletAPI x86_64 Linux.zip successfully!
    del /Q "WalletAPI x86_64 Linux.zip"
    echo Deleted WalletAPI x86_64 Linux.zip
) ELSE (
    echo Unzip WalletAPI x86_64 Linux.zip failed!
)

rem Copy to boost
echo.
echo ~ Copy to boost ~
xcopy "libs\arm64-v8a\libboost*" "boost\lib\arm64-v8a\" /Y /Q
xcopy "libs\armeabi-v7a\libboost*" "boost\lib\armeabi-v7a\" /Y /Q
xcopy "libs\x86_64\libboost*" "boost\lib\x86_64\" /Y /Q

rem Copy to Lunify
echo.
echo ~ Copy to Lunify ~
xcopy "libs\arm64-v8a\*" "lunify\lib\arm64-v8a\" /Y /Q
xcopy "libs\armeabi-v7a\*" "lunify\lib\armeabi-v7a\" /Y /Q
xcopy "libs\x86_64\*" "lunify\lib\x86_64\" /Y /Q

rem Deleting libs directory
echo.
echo ~ Deleting libs directory ~
rmdir /S /Q libs
IF %ERRORLEVEL% EQU 0 (
    echo libs directory deleted successfully!
) ELSE (
    echo Failed to delete libs directory!
)