package app.ativa_recife.di.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ativa_recife.activity.HomeActivity
import app.ativa_recife.activity.ui.theme.bluePastel
import app.ativa_recife.activity.ui.theme.greenPastel
import app.ativa_recife.activity.ui.theme.yellowPastel
import app.ativa_recife.ui.theme.Blue50
import app.ativa_recife.ui.theme.Yellow20
import app.ativa_recife.ui.theme.labelMediumBlack

@Composable
fun CardComponent(modifier: Modifier = Modifier, registration: Boolean ) {

    val activity = LocalContext.current as? Activity
    val uiColor = if (isSystemInDarkTheme())  yellowPastel else bluePastel
    val uiColorText = if (isSystemInDarkTheme())  Color.Black else Color.Black
    Box(
        modifier = Modifier
            .width(500.dp)
            .height(200.dp)
            .background(uiColor)

        ) {
        Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
            Text(text = "AUTISMO RUN", style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = uiColorText
            ))
            Text(text = "Data: 07/04/2024", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))
            Text(text = "Local: 2º Jardim de Boa Viagem", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))
            Text(text = "Largada: 7:00h.", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))
            Text(text = "Percurso: 5 km", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))
            Text(text = "Caminhada  2km", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = uiColorText
            ))

            Row(horizontalArrangement = Arrangement.End) {
                Text(text = "Organizador: ACOPE ", style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = uiColorText
                ))

                if(registration){
                ButtonCustomComponent(
                    modifier = Modifier
                        .width(200.dp).height(50.dp)
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    borderRadius = 20,
                    onClick = {
                        activity?.startActivity(
                            Intent(activity, HomeActivity::class.java).setFlags(
                                Intent.FLAG_ACTIVITY_SINGLE_TOP
                            )
                        )
                    }, label = "Inscreva-se",
                    style = labelMediumBlack,
                    color = Color.White,

                )
            }else{
                    ButtonCustomComponent(
                        modifier = Modifier
                            .width(200.dp).height(50.dp)
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        borderRadius = 20,
                        onClick = {
                            activity?.startActivity(
                                Intent(activity, HomeActivity::class.java).setFlags(
                                    Intent.FLAG_ACTIVITY_SINGLE_TOP
                                )
                            )
                        }, label = "Inscrito",
                        style = labelMediumBlack,
                        color = greenPastel,


                        )
                }
                }

        }
    }
}