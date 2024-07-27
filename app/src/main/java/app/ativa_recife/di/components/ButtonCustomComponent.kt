package app.ativa_recife.di.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import app.ativa_recife.R
import app.ativa_recife.ui.theme.RobotoRegular
import app.ativa_recife.ui.theme.Yellow20

@Composable
fun ButtonCustomComponent(
    onClick: () -> Unit, label: String,
    style: TextStyle,
    modifier: Modifier,
    borderRadius: Int
) {


    Button(onClick = { onClick() },

        modifier = modifier,
        colors = ButtonDefaults.buttonColors(Yellow20),
        shape = RoundedCornerShape(borderRadius),


    ) {
        Row {
            Text(label, color = Color.Black, fontFamily = RobotoRegular, style = style)

        }
    }
}