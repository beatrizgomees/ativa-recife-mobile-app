package app.ativa_recife.pages

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ativa_recife.utils.components.ButtonCustomComponent
import app.ativa_recife.utils.components.PhotoUser
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.ui.theme.configButton

@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as? Activity


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Configurações",
            fontSize = 30.sp,

        )
        Spacer(modifier = Modifier.size(40.dp))
        Box(
            modifier = Modifier
                .width(450.dp)
                .height(300.dp)
                .background(Orange50),
            contentAlignment = Alignment.Center
        ) {
            PhotoUser(size = 250.dp)
        }
        Spacer(modifier = Modifier.size(40.dp))
        ButtonCustomComponent(
            onClick = { Toast.makeText(activity, "Editando Perfil", Toast.LENGTH_SHORT).show() },
            label = "Editar Perfil",
            style = configButton,
            modifier = Modifier
                        .width(450.dp)
                        .height(70.dp)
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            borderRadius = 20,
            color = Color.Cyan
        )
        Spacer(modifier = Modifier.size(40.dp))
        ButtonCustomComponent(
            onClick = { Toast.makeText(activity, "Editando localização", Toast.LENGTH_SHORT).show() },
            label = "Editar localização",
            style = configButton,
            modifier = Modifier
                .width(450.dp)
                .height(70.dp)
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            borderRadius = 20,
            color = Color.Cyan
        )
        Spacer(modifier = Modifier.size(40.dp))
        ButtonCustomComponent(
            onClick = { activity?.finish() },
            label = "Sair",
            style = configButton,
            modifier = Modifier
                .width(450.dp)
                .height(70.dp)
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            borderRadius = 20,
            color = Color.Cyan
        )
    }
}