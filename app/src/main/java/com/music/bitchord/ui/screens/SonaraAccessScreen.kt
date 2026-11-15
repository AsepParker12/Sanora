package com.music.bitchord.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.music.bitchord.auth.SonaraAccess
import com.music.bitchord.ui.theme.SonaraCyan
import com.music.bitchord.ui.theme.SonaraViolet

/**
 * The first thing SONARA shows on a device that has not been unlocked: the
 * wordmark in the aurora gradient, and one field for the access code its
 * owner hands out. Nothing of the app is composed behind it — the gate is
 * the whole screen until a code opens it (see [SonaraAccess]).
 */
@Composable
fun SonaraAccessScreen(
    onUnlocked: (SonaraAccess.AccessLevel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var code by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val tryUnlock = {
        val level = SonaraAccess.grant(context, code)
        if (level != null) onUnlocked(level) else showError = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 360.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "SONARA",
                style = MaterialTheme.typography.displayMedium.merge(
                    TextStyle(
                        brush = Brush.linearGradient(listOf(SonaraViolet, SonaraCyan)),
                        fontWeight = FontWeight.Bold,
                    ),
                ),
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Your Music. Your World.",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Enter your access code to continue.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = code,
                onValueChange = {
                    code = it
                    showError = false
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Access code") },
                isError = showError,
                supportingText = if (showError) {
                    { Text("Access code not recognized") }
                } else {
                    null
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(onDone = { tryUnlock() }),
            )
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = tryUnlock,
                modifier = Modifier.fillMaxWidth(),
                enabled = code.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = SonaraViolet),
            ) {
                Text("Unlock")
            }
        }
    }
}
