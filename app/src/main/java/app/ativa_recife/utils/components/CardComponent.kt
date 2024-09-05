package app.ativa_recife.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ativa_recife.activity.ui.theme.bluePastel
import app.ativa_recife.activity.ui.theme.yellowPastel
import app.ativa_recife.model.Address
import app.ativa_recife.model.User
import app.ativa_recife.ui.theme.labelMediumBlack
import java.util.Date

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    registration: Boolean,
    buttonLabel : String,
    onClick: () -> Unit,
    dateEvent: Date,
    startTimeEvent: Date,
    sizeRouteEvent: String,
    managerEvent: User,
    titleEvent: String,
    addressEvent : Address
) {

    val uiColor = if (isSystemInDarkTheme())  yellowPastel else bluePastel
    val uiColorText = if (isSystemInDarkTheme())  Color.Black else Color.Black
    Box(
        modifier = Modifier
            .width(500.dp)
            .height(200.dp)
            .background(uiColor)

        ) {
        Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
            Text(text = titleEvent, style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = uiColorText
            ))
            Text(text = dateEvent.toString(), style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))
            Text(text = addressEvent.street + " " + addressEvent.district + " " + addressEvent.number + " " + addressEvent.city + " " + addressEvent.state, style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))
            Text(text = startTimeEvent.toString(), style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))
            Text(text = "Tamanho do percurso:" + sizeRouteEvent, style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))
            Row(horizontalArrangement = Arrangement.End) {
                Text(text = "Organizador: " + managerEvent.name, style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = uiColorText
                ))
            }

        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, top = 10.dp)
        ) {
            ButtonCustomComponent(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                borderRadius = 20, style = labelMediumBlack,
                onClick =  onClick ,
                label = buttonLabel,
                color = Color.White
                //label = if(registration) "Inscreva-se" else "Inscrito",
                //color = if(registration) Color.White else greenPastel

            )
        }
    }
}