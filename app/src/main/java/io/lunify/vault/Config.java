// Copyright (c) 2019, Mine2Gether.com
//
// Please see the included LICENSE file for more information.
//
// Copyright (c) 2025 Lunify
//
// Please see the included LICENSE file for more information.

package xyz.lunify.vault;

import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Objects;

public class Config {
    private static Config mSettings;
    private SharedPreferences preferences;

    public static final String CONFIG_KEY_CONFIG_VERSION = "config_version";
    public static final String CONFIG_KEY_HIDE_HOME_WIZARD = "hide_home_wizard";
    public static final String CONFIG_KEY_USER_SELECTED_NODE = "user_selected_node";
    public static final String CONFIG_KEY_SELECTED_ADDRESS = "addressbook_selected";
    public static final String CONFIG_SEND_DEBUG_INFO = "send_debug_info";

    public static final String version = "1";

    private final HashMap<String,String> mConfigs = new HashMap<String, String>();

    static void initialize(SharedPreferences preferences) {
        mSettings = new Config();
        mSettings.preferences = preferences;
    }

    public static void write(String key, String value) {
        if(!key.startsWith("system:")) {
            mSettings.preferences.edit().putString(key, value).apply();
        }

        if(value.isEmpty()) {
            return;
        }

        mSettings.mConfigs.put(key, value);
    }

    public static void clear() {
        mSettings.preferences.edit().clear().apply();
        mSettings.mConfigs.clear();
    }

    public static String read(String key) {
        return read(key, "");
    }

    public static String read(String key, String fallback) {
        if(!key.startsWith("system:")) {
            return mSettings.preferences.getString(key, fallback);
        }

        if(!mSettings.mConfigs.containsKey(key) || Objects.requireNonNull(mSettings.mConfigs.get(key)).isEmpty()) {
            return fallback;
        }

        return mSettings.mConfigs.get(key);
    }

    public Config() {
    }
}
