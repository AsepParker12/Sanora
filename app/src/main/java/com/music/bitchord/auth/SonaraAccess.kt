package com.music.bitchord.auth

import android.content.Context
import java.security.MessageDigest

/**
 * The access gate: SONARA is a personal app, shared only with people its
 * owner gives a code to.
 *
 * Only the SHA-256 hashes of the two codes live in the app — never the codes
 * themselves. The owner code and the guest code open the same app; the level
 * is kept so the two stay tellable apart. A granted code is stored in
 * encrypted prefs (see [EncryptedPrefs]) as the hash that matched, and that
 * hash is checked against the ones this build carries on every launch, so an
 * update shipping new hashes quietly retires the old codes. The store is
 * kept out of backups, in backup_rules.xml and data_extraction_rules.xml:
 * a grant belongs to the device it was typed on.
 */
object SonaraAccess {

    enum class AccessLevel { OWNER, GUEST }

    private const val OWNER_HASH =
        "bfa7ef6011ff6e6b969dc86a0487edfe0e4c66dda181aa70f040db61f221a4e6"
    private const val GUEST_HASH =
        "8524f1fc065addadbf71933ac445e959324cafa990ddf0da274419e4adfc9393"

    private const val PREFS_NAME = "sonara_access"
    private const val PREFS_PLAIN_NAME = "sonara_access_plain"
    private const val KEY_LEVEL = "access_level"
    private const val KEY_HASH = "access_hash"

    /** The level [code] opens, or null when it matches neither code. */
    fun check(code: String): AccessLevel? {
        if (code.isBlank()) return null
        return levelForHash(hashOf(code))
    }

    /** SHA-256 of the normalized [code], as lowercase hex. */
    fun hashOf(code: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
            .digest(normalize(code).toByteArray(Charsets.UTF_8))
        return digest.joinToString("") { "%02x".format(it) }
    }

    /** Codes are typed by hand, so case and stray spaces never matter. */
    fun normalize(code: String): String = code.trim().uppercase()

    /**
     * The level an earlier unlock granted on this device, re-checked against
     * the codes this build carries; null when nothing valid is stored.
     */
    fun grantedLevel(context: Context): AccessLevel? {
        val prefs = EncryptedPrefs.open(context, PREFS_NAME, PREFS_PLAIN_NAME)
        val hash = prefs.getString(KEY_HASH, null) ?: return null
        return levelForHash(hash)
    }

    /**
     * Tries [code]: when it opens the app, the grant is stored and its level
     * returned. A wrong code stores nothing and returns null.
     */
    fun grant(context: Context, code: String): AccessLevel? {
        val level = check(code) ?: return null
        EncryptedPrefs.open(context, PREFS_NAME, PREFS_PLAIN_NAME).edit()
            .putString(KEY_LEVEL, level.name)
            .putString(KEY_HASH, hashOf(code))
            .apply()
        return level
    }

    /** Forgets the grant on this device; the gate asks for a code again. */
    fun revoke(context: Context) {
        EncryptedPrefs.open(context, PREFS_NAME, PREFS_PLAIN_NAME).edit().clear().apply()
    }

    private fun levelForHash(hash: String): AccessLevel? {
        val candidate = hash.toByteArray(Charsets.UTF_8)
        return when {
            MessageDigest.isEqual(candidate, OWNER_HASH.toByteArray(Charsets.UTF_8)) ->
                AccessLevel.OWNER
            MessageDigest.isEqual(candidate, GUEST_HASH.toByteArray(Charsets.UTF_8)) ->
                AccessLevel.GUEST
            else -> null
        }
    }
}
