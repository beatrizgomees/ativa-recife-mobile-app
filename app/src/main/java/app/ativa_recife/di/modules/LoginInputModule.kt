package app.ativa_recife.di.modules

import android.app.Activity
import android.content.Intent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.ativa_recife.R
import app.ativa_recife.activity.HomeActivity
import app.ativa_recife.activity.RegisterActivity
import app.ativa_recife.activity.ui.theme.yellowPastel
import app.ativa_recife.di.components.ButtonCustomComponent
import app.ativa_recife.di.components.InputTextCustom
import app.ativa_recife.ui.theme.labelMediumBlack

@Composable
fun LoginInputModule(modifier: Modifier = Modifier) {
//    val uiColor = if (isSystemInDarkTheme()) Blue50 else Orange50
    val activity = LocalContext.current as? Activity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (activity != null) { //?
            LoginSection(activity)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Or Continue with ", style = MaterialTheme.typography.labelMedium.copy(color = Color.White))

        Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

                SocialMediaLoginModule(icon = R.drawable.googleicon, text = "Google" ) { }
                Spacer(modifier = Modifier.width(10.dp))
                SocialMediaLoginModule(icon = R.drawable.facebook, text = "Facebook" ) {}

            }
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(onClick = {
                activity?.startActivity(
                Intent(activity, RegisterActivity::class.java).setFlags(
                    Intent.FLAG_ACTIVITY_SINGLE_TOP
                )
            ) }) {
                Row {
                    Text(text = "Don't have account? ", color = Color.White)
                    Text(text = "Create now", fontWeight = FontWeight.ExtraBold, color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun LoginSection(activity: Activity) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    InputTextCustom(
        style = labelMediumBlack,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        borderRadius = 20,
        label = "Email", onValueChange = { email = it }, value = email
    )
    Spacer(modifier = Modifier.size(20.dp))
    InputTextCustom(
        style = labelMediumBlack, borderRadius = 20,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        label = "Password", onValueChange = { password = it }, value = password, isPassword = true
    )
    Spacer(modifier = Modifier.padding(top = 10.dp))
    ButtonCustomComponent(
        style = labelMediumBlack, color = yellowPastel,  borderRadius = 20,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
        onClick = {
            activity?.startActivity(
                Intent(activity, HomeActivity::class.java).setFlags(
                    Intent.FLAG_ACTIVITY_SINGLE_TOP
                )
            )
        },
        label = "Log in", enabled = email.isNotEmpty() && password.isNotEmpty()
    )
}


