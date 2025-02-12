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

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNotes {
    public String txNotes = "";
    public String note = "";
    public String lfitoKey = null;
    public String lfitoAmount = null; // could be a double - but we are not doing any calculations
    public String lfitoDestination = null;

    public UserNotes(final String txNotes) {
        if (txNotes == null) {
            return;
        }
        this.txNotes = txNotes;
        Pattern p = Pattern.compile("^\\{(lfito-\\w{6}),([0-9.]*)BTC,(\\w*)\\} ?(.*)");
        Matcher m = p.matcher(txNotes);
        if (m.find()) {
            lfitoKey = m.group(1);
            lfitoAmount = m.group(2);
            lfitoDestination = m.group(3);
            note = m.group(4);
        } else {
            note = txNotes;
        }
    }

    public void setNote(String newNote) {
        note = Objects.requireNonNullElse(newNote, "");
        txNotes = buildTxNote();
    }

    private String buildTxNote() {
        StringBuilder sb = new StringBuilder();
        if (lfitoKey != null) {
            if ((lfitoAmount == null) || (lfitoDestination == null))
                throw new IllegalArgumentException("Broken notes");
            sb.append("{");
            sb.append(lfitoKey);
            sb.append(",");
            sb.append(lfitoAmount);
            sb.append("BTC,");
            sb.append(lfitoDestination);
            sb.append("}");
            if ((note != null) && (!note.isEmpty()))
                sb.append(" ");
        }
        sb.append(note);
        return sb.toString();
    }
}