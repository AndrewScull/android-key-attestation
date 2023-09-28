/* Copyright 2019, The Android Open Source Project, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.attestation;

import com.google.common.base.Ascii;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Utils for fetching and decoding attestation certificate status.
 */
public class CertificateRevocationStatus {

  public final Status status;
  public final Reason reason;
  public final String comment;
  public final String expires;

  public static HashMap<String, CertificateRevocationStatus> fetchAllEntries() throws IOException {
    return getEntryToStatusMap(AndroidGoogleApisCache.fetchStatus());
  }

  public static HashMap<String, CertificateRevocationStatus> loadAllEntriesFromFile(String filePath)
      throws IOException {
    return getEntryToStatusMap(Files.newBufferedReader(Path.of(filePath)));
  }

  private static HashMap<String, CertificateRevocationStatus> getEntryToStatusMap(
      Reader statusListReader) {
    JsonObject entries =
            JsonParser.parseReader(statusListReader).getAsJsonObject().getAsJsonObject("entries");

    HashMap<String, CertificateRevocationStatus> serialNumberToStatus = new HashMap<>();
    for (String serialNumber : entries.keySet()) {
      serialNumberToStatus.put(
              serialNumber,
              new Gson().fromJson(entries.get(serialNumber), CertificateRevocationStatus.class));
    }

    return serialNumberToStatus;
  }

  public static CertificateRevocationStatus loadStatusFromFile(BigInteger serialNumber,
      String filePath)
      throws IOException {
    return loadStatusFromFile(serialNumber.toString(16), filePath);
  }

  public static CertificateRevocationStatus loadStatusFromFile(String serialNumber, String filePath)
      throws IOException {
    return decodeStatus(serialNumber, Files.newBufferedReader(Path.of(filePath)));
  }


  public static CertificateRevocationStatus fetchStatus(BigInteger serialNumber)
      throws IOException {
    return fetchStatus(serialNumber.toString(16));
  }

  public static CertificateRevocationStatus fetchStatus(String serialNumber) throws IOException {
    return decodeStatus(serialNumber, AndroidGoogleApisCache.fetchStatus());
  }

  private static CertificateRevocationStatus decodeStatus(String serialNumber,
      Reader statusListReader) {
    if (serialNumber == null) {
      throw new IllegalArgumentException("serialNumber cannot be null");
    }
    serialNumber = Ascii.toLowerCase(serialNumber);

    JsonObject entries = JsonParser.parseReader(statusListReader)
        .getAsJsonObject()
        .getAsJsonObject("entries");

    if (!entries.has(serialNumber)) {
      return null;
    }

    return new Gson().fromJson(entries.get(serialNumber), CertificateRevocationStatus.class);
  }

  public enum Status {
    REVOKED, SUSPENDED
  }

  public enum Reason {
    UNSPECIFIED, KEY_COMPROMISE, CA_COMPROMISE, SUPERSEDED, SOFTWARE_FLAW
  }

  public CertificateRevocationStatus() {
    status = Status.REVOKED;
    reason = Reason.UNSPECIFIED;
    comment = null;
    expires = null;
  }
}
