/*
 * Copyright (c) 2017 m2049r
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ////////////////
 *
 * Copyright (c) 2025 Lunify
 *
 * Please see the included LICENSE file for more information.*/

package xyz.lunify.vault;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import xyz.lunify.vault.util.Helper;
import xyz.lunify.vault.util.LocaleHelper;

import static android.view.WindowManager.LayoutParams;

public abstract class SecureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Helper.preventScreenshot()) {
            getWindow().setFlags(LayoutParams.FLAG_SECURE, LayoutParams.FLAG_SECURE);
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.setLocale(context, LocaleHelper.getLocale(context)));
    }
}