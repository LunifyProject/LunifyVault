/*
 * Copyright (c) 2019 m2049r et al.
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

package xyz.lunify.vault.service.exchange.ecb;

import androidx.annotation.NonNull;

import xyz.lunify.vault.service.exchange.api.ExchangeRate;

import java.util.Date;

class ExchangeRateImpl implements ExchangeRate {
    private final Date date;
    private final String baseCurrency = "EUR";
    private final String quoteCurrency;
    private final double rate;

    @Override
    public String getServiceName() {
        return "ecb.europa.eu";
    }

    @Override
    public String getBaseCurrency() {
        return baseCurrency;
    }

    @Override
    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    @Override
    public double getRate() {
        return rate;
    }

    ExchangeRateImpl(@NonNull final String quoteCurrency, double rate, @NonNull final Date date) {
        super();
        this.quoteCurrency = quoteCurrency;
        this.rate = rate;
        this.date = date;
    }
}
