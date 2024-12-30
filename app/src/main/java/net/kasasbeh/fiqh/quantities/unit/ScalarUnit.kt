package net.kasasbeh.fiqh.quantities.unit

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

interface ScalarUnit {

    @Composable
    fun localizedName(): String {
        val context = LocalContext.current
        return localizedName(context)
    }

    fun localizedName(context: Context): String

}