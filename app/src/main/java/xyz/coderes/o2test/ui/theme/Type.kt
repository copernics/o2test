package xyz.coderes.o2test.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.em
import xyz.coderes.o2test.R


//We can use Inter font from here, and download it on-fly
//But I downloaded it in local Font directory ...


//val fontName = GoogleFont("Inter")
//
//val provider = GoogleFont.Provider(
//    providerAuthority = "com.google.android.gms.font",
//    providerPackage = "com.google.android.gms",
//    certificates = R.array.com_google_android_gms_fonts_certs
//)


//val fontFamily = FontFamily(
//    Font(googleFont = fontName, fontProvider = provider)
//)

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val AppTypography = Typography(
    bodyMedium = TextStyle( // Body M
        fontFamily = Inter,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.01.em
    ),
    labelMedium = TextStyle( // Label M
        fontFamily = Inter,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.16.em
    ),
    labelSmall = TextStyle( // Label S
        fontFamily = Inter,
        fontWeight = FontWeight(550),// FontWeight.W600 is more standard
        fontSize = 14.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.16.em
    ),
)

val LocalTypography = staticCompositionLocalOf { AppTypography }

