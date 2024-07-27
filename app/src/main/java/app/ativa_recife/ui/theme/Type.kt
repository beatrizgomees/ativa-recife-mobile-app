package app.ativa_recife.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.ativa_recife.R

val RobotoRegular = FontFamily(
    listOf(
        Font(resId = R.font.roboto_regular,
            weight = FontWeight.Normal)
    )
)

val Rubik = FontFamily(
    listOf(
        Font(resId = R.font.rubik_bold, weight = FontWeight.Bold)
    )
)

val Inter = FontFamily(
    listOf(
        Font(resId = R.font.inter_medium, weight = FontWeight.Medium)
    )
)


// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,

    )

)

val headlineMedium = TextStyle(
        fontFamily = RobotoRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,

        )

val titleMedium = TextStyle(
        fontFamily = RobotoRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    )

val labelMediumBlack = TextStyle(
    fontFamily = RobotoRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp,
    color = Color.Black
)

