package net.kasasbeh.islamicunits.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.kasasbeh.islamicunits.icons.ContentCopy

@Composable
fun CopyButton(text: String, enabled: Boolean = true) {
    val context = LocalContext.current
    IconButton(
        enabled = enabled,
        onClick = {
            if (text.isNotEmpty()) {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Unit", text)
                clipboard.setPrimaryClip(clip)
            }
        }
    ) {
        Icon(
            imageVector = ContentCopy,
            contentDescription = "Copy value"
        )
    }
}