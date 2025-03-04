
## Table of Contents

These instructions are tailored to building or downloading ```wallep_api``` and the requred libraries. These instructions are for all supported architectures: ```'armeabi-v7a', 'arm64-v8a', 'x86_64'```.

- [Building libraries](#building-libraries)
  - [Install all packages](#install-all-packages)
  - [Clone the `Lunify` repository](#clone-the-lunify-repository)
  - [Building ARMv7](#building-armv7)
  - [Building ARMv8](#building-armv8)
  - [Building x86_64](#building-x86_64)
  - [Make all the folders](#make-all-the-folders)
  - [Gather all files for ARMv7](#gather-all-files-for-armv7)
  - [Gather all files for ARMv8](#gather-all-files-for-armv8)
  - [Gather all files for x86_64](#gather-all-files-for-x86_64)
  - [Go back to `external-libs` folder](#go-back-to-external-libs-folder)
  - [Distribute the files](#distribute-the-files)
- [Use precompile libraries](#use-precompile-libraries)
  - [Unzip the files](#unzip-the-files)

# Building libraries

_The `-j4` is the amount of threads you want to use._

### Install all packages
```Shell
sudo apt -y install build-essential cmake miniupnpc libunbound-dev graphviz doxygen libunwind8-dev pkg-config libssl-dev libzmq3-dev libsodium-dev libhidapi-dev libnorm-dev libusb-1.0-0-dev libpgm-dev libprotobuf-dev protobuf-compiler ccache python3 python3-zmq libdbus-1-dev libharfbuzz-dev gperf g++-arm-linux-gnueabihf g++-aarch64-linux-gnu
```

### Clone the `Lunify` repository
```Shell
git clone https://github.com/LunifyProject/Lunify lunifyCode
cd lunifyCode
```

### Building ARMv7
```Shell
make depends target=arm-linux-android -j4
make release-static-android-armv7-wallet_api -j4
```

### Building ARMv8
```Shell
make depends target=aarch64-linux-android -j4
make release-static-android-armv8-wallet_api -j4
```

### Building x86_64
```Shell
make depends target=x86_64-linux-android -j4
make release-static-android-x86_64-wallet_api -j4
```

### Make all the folders
```Shell
mkdir ../libs
mkdir ../libs/armeabi-v7a
mkdir ../libs/arm64-v8a
mkdir ../libs/x86_64
```

### Gather all files for ARMv7
```Shell
find ./contrib/depends/${{ matrix.toolchain.make_command_depends }}/lib -type f -name "libboost*.a" -exec cp {} ../libs/armeabi-v7a/ \;
find ./build/Linux/main/release/ -type f -name "*.a" -exec cp {} libs/armeabi-v7a/ \;
```

### Gather all files for ARMv8
```Shell
find ./contrib/depends/${{ matrix.toolchain.make_command_depends }}/lib -type f -name "libboost*.a" -exec cp {} ../libs/arm64-v8a/ \;
find ./build/Linux/main/release/ -type f -name "*.a" -exec cp {} libs/arm64-v8a/ \;
```

### Gather all files for x86_64
```Shell
find ./contrib/depends/${{ matrix.toolchain.make_command_depends }}/lib -type f -name "libboost*.a" -exec cp {} ../libs/x86_64/ \;
find ./build/Linux/main/release/ -type f -name "*.a" -exec cp {} libs/x86_64/ \;
```

### Go back to `external-libs` folder
```Shell
cd ..
```

### Distribute the files

Distribute all the files to the `lunify` and `libboost` folder.

```Shell
./distribute_files.sh
```

<br>

# Use precompile libraries
Download the latest libraries from the [Github Actions](https://github.com/LunifyProject/Lunify/actions/workflows/wallet-api.yml) page and put them in the `external-libs` folder.

### Unzip the files

Unzip all the files to the `lunify` folder and `libboost`.

```Shell
./parse_libs.sh
```