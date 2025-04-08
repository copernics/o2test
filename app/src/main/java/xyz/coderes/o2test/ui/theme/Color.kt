@file:Suppress("unused")

package xyz.coderes.o2test.ui.theme

import androidx.compose.ui.graphics.Color

object AppColors {

    object Content {
        val OnNeutralLow = Color(0xFFC9C9CE)     // core/gray/300
        val OnNeutralMedium = Color(0xFF8C8C9A)  // core/gray/500
        val OnNeutralXxHigh = Color(0xFF2C2C31)  // core/gray/950
        val OnNeutralDanger = Color(0xFFDC2828)  // core/red/600
        val OnNeutralWarning = Color(0xFFA56315) // core/yellow/700
    }

    object Surface {
        val XLow = Color(0xFFFFFFFF)             // core/gray/00
        val XHigh = Color(0xFF2C2C31)            // assuming reuse
        val Brand = Color(0xFF0050FF)            // o2/blue/500
        val Danger = Color(0xFFDC2828)           // core/red/600
        val Warning = Color(0xFFA56315)          // core/yellow/700
        val DangerVariant = Color(0xFFFFDCDC)    // core/red/100
        val WarningVariant = Color(0xFFFAF1B6)   // core/yellow/100
    }

    object State {
        val Focus = Color(0x1A1A1ACC)  // 80% black
        val Hover = Color(0x1A1A1A0F)  // 6% black
    }

    object Alpha {
        val Dim800 = Color(0xCC1A1A1A) // parsed from 80%
        val Dim50 = Color(0x0F1A1A1A)  // parsed from 6%
    }
}