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

package xyz.lunify.vault.data;

import android.net.Uri;

import xyz.lunify.vault.model.Wallet;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class BarcodeData {

    public static final String LFI_SCHEME = "lunify:";
    public static final String LFI_PAYMENTID = "tx_payment_id";
    public static final String LFI_AMOUNT = "tx_amount";
    public static final String LFI_DESCRIPTION = "tx_description";

    public enum Asset {
        LFI
    }

    public enum Security {
        NORMAL
    }

    public interface Listener {
        void onParseComplete(BarcodeData data);
    }

    final public Asset asset;
    final public String address;
    final public String addressName;
    final public String amount;
    final public String description;
    final public Security security;
    final public String bip70;

    public BarcodeData(Asset asset, String address) {
        this(asset, address, null, null, null, Security.NORMAL);
    }

    public BarcodeData(Asset asset, String address, String amount) {
        this(asset, address, null, null, amount, Security.NORMAL);
    }

    public BarcodeData(Asset asset, String address, String amount, String description, Security security) {
        this(asset, address, null, description, amount, security);
    }

    public BarcodeData(Asset asset, String address, String paymentId, String description, String amount) {
        this(asset, address, null, description, amount, Security.NORMAL);
    }

    public BarcodeData(Asset asset, String address, String addressName, String description, String amount, Security security) {
        this(asset, address, addressName, null, description, amount, security);
    }

    public BarcodeData(Asset asset, String address, String addressName, String bip70, String description, String amount, Security security) {
        this.asset = asset;
        this.address = address;
        this.bip70 = bip70;
        this.addressName = addressName;
        this.description = description;
        this.amount = amount;
        this.security = security;
    }

    public Uri getUri() {
        return Uri.parse(getUriString());
    }

    public String getUriString() {
        if (asset != Asset.LFI) throw new IllegalStateException("We can only do LFI stuff!");
        StringBuilder sb = new StringBuilder();
        sb.append(BarcodeData.LFI_SCHEME).append(address);
        boolean first = true;
        if ((description != null) && !description.isEmpty()) {
            sb.append("?");
            first = false;
            sb.append(BarcodeData.LFI_DESCRIPTION).append('=').append(Uri.encode(description));
        }
        if ((amount != null) && !amount.isEmpty()) {
            sb.append(first ? "?" : "&");
            sb.append(BarcodeData.LFI_AMOUNT).append('=').append(amount);
        }
        return sb.toString();
    }

    static public void fromString(String qrCode, Listener listener) {
        BarcodeData bcData = parseLunifyUri(qrCode);
        if (bcData == null) {
            // maybe it's naked?
            bcData = parseLunifyNaked(qrCode);
        } else {
            listener.onParseComplete(bcData);
        }
    }

    /**
     * Parse and decode a lunify scheme string. It is here because it needs to validate the data.
     *
     * @param uri String containing a lunify URL
     * @return BarcodeData object or null if uri not valid
     */

    static public BarcodeData parseLunifyUri(String uri) {
        Timber.d("parseLunifyUri=%s", uri);

        if (uri == null) return null;

        if (!uri.startsWith(LFI_SCHEME)) return null;

        String noScheme = uri.substring(LFI_SCHEME.length());
        Uri lunify = Uri.parse(noScheme);
        Map<String, String> parms = new HashMap<>();
        String query = lunify.getEncodedQuery();
        if (query != null) {
            String[] args = query.split("&");
            for (String arg : args) {
                String[] namevalue = arg.split("=");
                if (namevalue.length == 0) {
                    continue;
                }
                parms.put(Uri.decode(namevalue[0]).toLowerCase(),
                        namevalue.length > 1 ? Uri.decode(namevalue[1]) : "");
            }
        }
        String address = lunify.getPath();

        String paymentId = parms.get(LFI_PAYMENTID);
        // no support for payment ids!
        if (paymentId != null) {
            Timber.e("no support for payment ids!");
            return null;
        }

        String description = parms.get(LFI_DESCRIPTION);
        String amount = parms.get(LFI_AMOUNT);
        if (amount != null) {
            try {
                Double.parseDouble(amount);
            } catch (NumberFormatException ex) {
                Timber.d(ex.getLocalizedMessage());
                return null;
            }
        }

        if (!Wallet.isAddressValid(address)) {
            Timber.d("address invalid");
            return null;
        }
        return new BarcodeData(Asset.LFI, address, paymentId, description, amount);
    }

    static public BarcodeData parseLunifyNaked(String address) {
        Timber.d("parseLunifyNaked=%s", address);

        if (address == null) return null;

        if (!Wallet.isAddressValid(address)) {
            Timber.d("address invalid");
            return null;
        }

        return new BarcodeData(Asset.LFI, address);
    }
}