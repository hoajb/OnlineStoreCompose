package vn.hoanguyen.compose.onlinestore.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import vn.hoanguyen.compose.onlinestore.R

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_light, FontWeight.W100),
    Font(R.font.poppins_lightitalic, FontWeight.W100, FontStyle.Italic),
    Font(R.font.poppins_medium, FontWeight.W500),
    Font(R.font.poppins_mediumitalic, FontWeight.W500, FontStyle.Italic),
    Font(R.font.poppins_semibold, FontWeight.W600),
    Font(R.font.poppins_semibolditalic, FontWeight.W600, FontStyle.Italic),
    Font(R.font.poppins_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.poppins_extrabold, FontWeight.W800),
    Font(R.font.poppins_extrabolditalic, FontWeight.W800, FontStyle.Italic),
    Font(R.font.poppins_extralight, FontWeight.W200),
    Font(R.font.poppins_extralightitalic, FontWeight.W200, FontStyle.Italic),
    Font(R.font.poppins_thin, FontWeight.W100),
    Font(R.font.poppins_thinitalic, FontWeight.W100, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

