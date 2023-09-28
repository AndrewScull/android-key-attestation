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

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Utils for fetching and caching resources from android.googleapis.com.
 */
class AndroidGoogleApisCache {

  private static final URL ROOT_URL = makeUrl("https://android.googleapis.com/attestation/root");
  private static final URL STATUS_URL = makeUrl("https://android.googleapis.com/attestation/status");
  private static final String CACHE_PATH = "httpcache";
  private static final Cache CACHE = new Cache(new File(CACHE_PATH), 10 * 1024 * 1024);
  private static final OkHttpClient CLIENT = new OkHttpClient.Builder().cache(CACHE).build();

  private AndroidGoogleApisCache() {}

  private static URL makeUrl(String url) {
    try {
      return new URL(url);
    } catch (MalformedURLException e) {
      throw new AssertionError("\"" + url + "\" is not a valid URL", e);
    }
  }

  private static Reader fetch(URL url) throws IOException {
    Request request = new Request.Builder().url(url).build();

    try (Response response = CLIENT.newCall(request).execute()) {
      return response.body().charStream();
    }
  }

  static Reader fetchRoot() throws IOException {
    return fetch(ROOT_URL);
  }

  static Reader fetchStatus() throws IOException {
    return fetch(STATUS_URL);
  }

}
