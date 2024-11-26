package net.kasasbeh.fiqh.quantities.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import net.kasasbeh.fiqh.quantities.R
import net.kasasbeh.fiqh.quantities.data.School.entries

enum class School {

    HANBALI,
    SHAFII,
    MALIKI,
    HANAFI
}

private val names = entries.associateBy { it.name }

fun schoolOf(name: String): School {
    return names[name.uppercase()] ?: error("Invalid school: $name.")
}

fun schools() = names.values

@Composable
fun School.localizedName(): String {
    return when (this) {
        School.HANBALI -> stringResource(R.string.hanbali)
        School.SHAFII -> stringResource(R.string.shafii)
        School.MALIKI -> stringResource(R.string.maliki)
        School.HANAFI -> stringResource(R.string.hanafi)
    }
}