@file:Suppress("unused")

package xyz.coderes.o2test.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class AppSpacing(
    val xs: Int = 8,
    val s: Int = 12,
    val m: Int = 16,
    val l: Int = 20,
) {
    val extraSmall = xs.dp
    val small = s.dp
    val medium = m.dp
    val large = l.dp
}

val LocalSpacing = staticCompositionLocalOf { AppSpacing() }
