package app.ativa_recife.di.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import app.ativa_recife.ui.theme.RobotoRegular

@Composable
fun ButtonCustomComponent(
    onClick: () -> Unit, label: String,
    style: TextStyle,
    modifier: Modifier,
    borderRadius: Int,
    color: Color,
    enabled: Boolean = true
) {


    Button(onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(borderRadius),
        enabled = enabled
    ) {
        Row {
            Text(label,
                color = Color.Black,
                fontFamily = RobotoRegular,
                style = style)

        }
    }
}