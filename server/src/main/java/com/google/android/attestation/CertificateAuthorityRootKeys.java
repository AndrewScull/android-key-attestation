/* Copyright 2023, The Android Open Source Project, Inc.
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

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class CertificateAuthorityRootKeys {

  private final ImmutableList<PublicKey> rootKeys;

  private CertificateAuthorityRootKeys(ImmutableList<PublicKey> rootKeys) {
    this.rootKeys = rootKeys;
  }

  public static CertificateAuthorityRootKeys fetchAll() throws IOException {
    CertificateFactory certificateFactory;
    try {
      certificateFactory = CertificateFactory.getInstance("X.509");
    } catch (CertificateException e) {
      throw new AssertionError(e);
    }

    JsonArray roots =
        JsonParser.parseReader(AndroidGoogleApisCache.fetchRoot()).getAsJsonArray();
    ImmutableList<PublicKey> rootKeys = roots.asList().stream().map(root -> {
          byte[] pemBytes = root.getAsString().getBytes(UTF_8);
          ByteArrayInputStream inputStream = new ByteArrayInputStream(pemBytes);
          try {
            return certificateFactory.generateCertificate(inputStream).getPublicKey();
          } catch (CertificateException e) {
            throw new IllegalStateException(e);
          }
        }
    ).collect(toImmutableList());

    return new CertificateAuthorityRootKeys(rootKeys);
  }

  public boolean contains(PublicKey publicKey) {
    return rootKeys.contains(publicKey);
  }

}
