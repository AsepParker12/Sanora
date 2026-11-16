package com.music.bitchord.playback

import android.content.Intent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * A request from outside the app to land on a given tab.
 *
 * The launcher shortcuts (registered dynamically by
 * [MainActivity][com.music.bitchord.MainActivity]) carry the tab they want in
 * an intent extra, and there is nothing to hand that intent to: which tab is
 * showing is a `rememberSaveable`d int inside `BitChordApp`, which only exists
 * once the composition is running and which the activity cannot reach. Because
 * the activity is `singleTask`, the intent also arrives down two different
 * paths depending on whether the app happened to be alive — `onCreate` for a
 * cold launch, `onNewIntent` for every other time — so both relay through here
 * and the composition watches this. Same shape as [PlayerDeepLink], for the
 * same reasons.
 */
object TabDeepLink {

    /** Set on a shortcut's intent, holding the tab index it asks for. */
    const val EXTRA_TARGET_TAB = "sonara.targetTab"

    private val _pending = MutableStateFlow(-1)

    /** The tab a shortcut asked for, or -1 when no request is outstanding. */
    val pending: StateFlow<Int> = _pending.asStateFlow()

    /** Reads an incoming intent, and reports whether it asked for a tab. */
    fun consume(intent: Intent?): Boolean {
        if (intent == null || !intent.hasExtra(EXTRA_TARGET_TAB)) return false
        val tab = intent.getIntExtra(EXTRA_TARGET_TAB, -1)
        // Cleared off the intent as well. A singleTask activity keeps the intent
        // that launched it, and getIntent() returns the same one after every
        // configuration change — left in place, a shortcut tap would re-apply
        // its tab each time the device changed theme or font size.
        intent.removeExtra(EXTRA_TARGET_TAB)
        if (tab < 0) return false
        _pending.value = tab
        return true
    }

    /**
     * Called once the requested tab has actually been selected.
     *
     * The request has to be cleared by whoever acts on it, not by whoever set
     * it: this object outlives the composition, so a request left standing
     * would be served again by the next composition.
     */
    fun handled() {
        _pending.value = -1
    }
}
