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
import app.ativa_recife.activity.ui.theme.Pink40
import app.ativa_recife.utils.components.ButtonCustomComponent
import app.ativa_recife.utils.components.PhotoUser
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.ui.theme.configButton
import app.ativa_recife.viewmodel.MainViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun SettingsPage(modifier: Modifier = Modifier, viewModel: MainViewModel) {
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
        Spacer(modifier = Modifier.size(20.dp))
        ButtonCustomComponent(
            onClick = {  Firebase.auth.signOut() },
            label = "Sair",
            style = configButton,
            modifier = Modifier
                .width(450.dp)
                .height(50.dp)
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            borderRadius = 20,
            color = Pink40
        )
    }
}