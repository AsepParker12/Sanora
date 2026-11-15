package com.music.bitchord

import com.music.bitchord.auth.SonaraAccess
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * The access gate's negative space: codes that must not open SONARA, and the
 * hash the check is built on. Positive cases are deliberately absent — the
 * real codes are not test data, and a test that carried one would hand it to
 * anyone who reads the source.
 */
class SonaraAccessTest {

    @Test
    fun `an unknown code opens nothing`() {
        assertNull(SonaraAccess.check("ZZZZZZZZZZ"))
        assertNull(SonaraAccess.check("not-a-code"))
    }

    @Test
    fun `a well-formed but wrong code opens nothing`() {
        // Right shape, right alphabet, no match.
        assertNull(SonaraAccess.check("A2B3C4D5E6"))
        assertNull(SonaraAccess.check("a2b3c4d5e6"))
    }

    @Test
    fun `blank codes open nothing`() {
        assertNull(SonaraAccess.check(""))
        assertNull(SonaraAccess.check("   "))
    }

    @Test
    fun `hashOf is the SHA-256 of the normalized code`() {
        // SHA-256 of "ABC", the standard test vector.
        assertEquals(
            "b5d4045c3f466fa91fe2cc6abe79232a1a57cdf104f7a26e716e0a1e2789df78",
            SonaraAccess.hashOf("ABC"),
        )
    }

    @Test
    fun `hashOf ignores case and surrounding space`() {
        assertEquals(SonaraAccess.hashOf("ABC"), SonaraAccess.hashOf(" abc "))
        assertEquals(SonaraAccess.hashOf("ABC"), SonaraAccess.hashOf("abc"))
    }

    @Test
    fun `hashOf is deterministic`() {
        assertEquals(SonaraAccess.hashOf("SONARA"), SonaraAccess.hashOf("SONARA"))
    }
}
