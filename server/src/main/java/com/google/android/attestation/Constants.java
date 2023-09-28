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

/** Key Attestation constants */
public class Constants {

  static final String KEY_DESCRIPTION_OID = "1.3.6.1.4.1.11129.2.1.17";
  static final int ATTESTATION_VERSION_INDEX = 0;
  static final int ATTESTATION_SECURITY_LEVEL_INDEX = 1;
  static final int KEYMASTER_VERSION_INDEX = 2;
  static final int KEYMASTER_SECURITY_LEVEL_INDEX = 3;
  static final int ATTESTATION_CHALLENGE_INDEX = 4;
  static final int UNIQUE_ID_INDEX = 5;
  static final int SW_ENFORCED_INDEX = 6;
  static final int TEE_ENFORCED_INDEX = 7;
  // Authorization list tags. The list is in this AOSP file:
  // hardware/libhardware/include/hardware/keymaster_defs.h
  static final int KM_TAG_PURPOSE = 1;
  static final int KM_TAG_ALGORITHM = 2;
  static final int KM_TAG_KEY_SIZE = 3;
  static final int KM_TAG_DIGEST = 5;
  static final int KM_TAG_PADDING = 6;
  static final int KM_TAG_EC_CURVE = 10;
  static final int KM_TAG_RSA_PUBLIC_EXPONENT = 200;
  static final int KM_TAG_ROLLBACK_RESISTANCE = 303;
  static final int KM_TAG_ACTIVE_DATE_TIME = 400;
  static final int KM_TAG_ORIGINATION_EXPIRE_DATE_TIME = 401;
  static final int KM_TAG_USAGE_EXPIRE_DATE_TIME = 402;
  static final int KM_TAG_NO_AUTH_REQUIRED = 503;
  static final int KM_TAG_USER_AUTH_TYPE = 504;
  static final int KM_TAG_AUTH_TIMEOUT = 505;
  static final int KM_TAG_ALLOW_WHILE_ON_BODY = 506;
  static final int KM_TAG_TRUSTED_USER_PRESENCE_REQUIRED = 507;
  static final int KM_TAG_TRUSTED_CONFIRMATION_REQUIRED = 508;
  static final int KM_TAG_UNLOCKED_DEVICE_REQUIRED = 509;
  static final int KM_TAG_ALL_APPLICATIONS = 600;
  static final int KM_TAG_APPLICATION_ID = 601;
  static final int KM_TAG_CREATION_DATE_TIME = 701;
  static final int KM_TAG_ORIGIN = 702;
  static final int KM_TAG_ROLLBACK_RESISTANT = 703;
  static final int KM_TAG_ROOT_OF_TRUST = 704;
  static final int KM_TAG_OS_VERSION = 705;
  static final int KM_TAG_OS_PATCH_LEVEL = 706;
  static final int KM_TAG_ATTESTATION_APPLICATION_ID = 709;
  static final int KM_TAG_ATTESTATION_ID_BRAND = 710;
  static final int KM_TAG_ATTESTATION_ID_DEVICE = 711;
  static final int KM_TAG_ATTESTATION_ID_PRODUCT = 712;
  static final int KM_TAG_ATTESTATION_ID_SERIAL = 713;
  static final int KM_TAG_ATTESTATION_ID_IMEI = 714;
  static final int KM_TAG_ATTESTATION_ID_MEID = 715;
  static final int KM_TAG_ATTESTATION_ID_MANUFACTURER = 716;
  static final int KM_TAG_ATTESTATION_ID_MODEL = 717;
  static final int KM_TAG_VENDOR_PATCH_LEVEL = 718;
  static final int KM_TAG_BOOT_PATCH_LEVEL = 719;
  static final int KM_TAG_DEVICE_UNIQUE_ATTESTATION = 720;
  static final int KM_TAG_IDENTITY_CREDENTIAL_KEY = 721;
  static final int KM_TAG_ATTESTATION_ID_SECOND_IMEI = 723;
  static final int ROOT_OF_TRUST_VERIFIED_BOOT_KEY_INDEX = 0;
  static final int ROOT_OF_TRUST_DEVICE_LOCKED_INDEX = 1;
  static final int ROOT_OF_TRUST_VERIFIED_BOOT_STATE_INDEX = 2;
  static final int ROOT_OF_TRUST_VERIFIED_BOOT_HASH_INDEX = 3;
  static final int ATTESTATION_APPLICATION_ID_PACKAGE_INFOS_INDEX = 0;
  static final int ATTESTATION_APPLICATION_ID_SIGNATURE_DIGESTS_INDEX = 1;
  static final int ATTESTATION_PACKAGE_INFO_PACKAGE_NAME_INDEX = 0;
  static final int ATTESTATION_PACKAGE_INFO_VERSION_INDEX = 1;
  // Some security values. The complete list is in this AOSP file:
  // hardware/libhardware/include/hardware/keymaster_defs.h
  static final int KM_SECURITY_LEVEL_SOFTWARE = 0;
  static final int KM_SECURITY_LEVEL_TRUSTED_ENVIRONMENT = 1;
  static final int KM_SECURITY_LEVEL_STRONG_BOX = 2;
  static final int KM_VERIFIED_BOOT_STATE_VERIFIED = 0;
  static final int KM_VERIFIED_BOOT_STATE_SELF_SIGNED = 1;
  static final int KM_VERIFIED_BOOT_STATE_UNVERIFIED = 2;
  static final int KM_VERIFIED_BOOT_STATE_FAILED = 3;
  // Unsigned max value of 32-bit integer, 2^32 - 1
  static final long UINT32_MAX = (((long) Integer.MAX_VALUE) << 1) + 1;

  private Constants() {}
}
