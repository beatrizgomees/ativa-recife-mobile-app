package app.ativa_recife.di.modules

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import app.ativa_recife.R
import app.ativa_recife.di.components.ButtonCustomComponent
import app.ativa_recife.ui.theme.Blue50
import app.ativa_recife.ui.theme.Inter
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.ui.theme.RobotoRegular
import app.ativa_recife.ui.theme.White
import app.ativa_recife.ui.theme.Yellow20
import app.ativa_recife.ui.theme.labelMediumBlack
import com.github.beatrizgomees.weatherapp.components.InputTextCustom

@Composable
fun LoginInputModule(modifier: Modifier = Modifier) {
    val uiColor = if (isSystemInDarkTheme()) Blue50 else Orange50
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginSection(email, password)
        Spacer(modifier = Modifier.height(30.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Or Continue with ", style = MaterialTheme.typography.labelMedium.copy(color = Color.White))

        Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

                SocialMediaLoginModule(icon = R.drawable.googleicon, text = "Google" ) { }
                Spacer(modifier = Modifier.width(10.dp))
                SocialMediaLoginModule(icon = R.drawable.facebook, text = "Facebook" ) {}

            }
        }
    }
}

@Composable
private fun LoginSection(email: String, password: String) {
    var email1 = email
    var password1 = password
    InputTextCustom(
        style = labelMediumBlack,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        borderRadius = 20,
        label = "Email", onValueChange = { email1 = it }, value = email1
    )

    Spacer(modifier = Modifier.size(20.dp))

    InputTextCustom(
        style = labelMediumBlack,
        borderRadius = 20,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        label = "Password", onValueChange = { password1 = it }, value = password1
    )
    Spacer(modifier = Modifier.padding(top = 10.dp))

    ButtonCustomComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
        borderRadius = 20,
        onClick = { /*TODO*/ }, label = "Log in", style = labelMediumBlack

    )




}


