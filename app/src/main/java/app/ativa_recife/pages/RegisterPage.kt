package app.ativa_recife.pages

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ativa_recife.R
import app.ativa_recife.utils.components.ButtonCustomComponent
import app.ativa_recife.utils.modules.SocialMediaLoginModule
import app.ativa_recife.ui.theme.Blue50
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.ui.theme.RobotoRegular
import app.ativa_recife.ui.theme.Yellow20
import app.ativa_recife.ui.theme.labelMediumBlack
import app.ativa_recife.utils.components.InputTextCustom
import app.ativa_recife.viewmodel.RegisterViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    val uiColor = if (isSystemInDarkTheme())  Blue50 else Orange50
    Surface(modifier = Modifier.fillMaxSize(), color = uiColor) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopSection()
            RegisterSection()

        }
    }
}

@Composable
private fun TopSection() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
    ) {
        Text(
            text = stringResource(id = R.string.the_title_app),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontFamily = RobotoRegular,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

@Composable
private fun RegisterSection() {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity

    Column(modifier = Modifier.padding(top = 10.dp)) {
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Name", onValueChange = { name = it }, value = name
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Email", onValueChange = { email = it }, value = email
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Password", onValueChange = { password = it }, value = password, isPassword = true
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Confirm Password", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Spacer(modifier = Modifier.padding(top = 50.dp))
        ButtonCustomComponent(
            style = labelMediumBlack, color = Yellow20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            borderRadius = 20,
            enabled = email.isNotEmpty() && name.isNotEmpty() &&
                    password.isNotEmpty() && confirmPassword.isNotEmpty()
                    && password.equals(confirmPassword),
            label = "Register",
            onClick = {
               RegisterActionFirebase(email = email, password = password, activity = activity)
            }
        )
        RegisterInputModule()
    }
}
@Composable
private fun RegisterInputModule() {
    Spacer(modifier = Modifier.height(30.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Or Register with ",
            style = MaterialTheme.typography.labelMedium.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            SocialMediaLoginModule(icon = R.drawable.googleicon, text = "Google") { }
            Spacer(modifier = Modifier.width(10.dp))
            SocialMediaLoginModule(icon = R.drawable.facebook, text = "Facebook") {}

        }
    }

}

private fun RegisterActionFirebase(email: String, password: String, activity: Activity?){
    Firebase.auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(activity!!) { task ->
            if (task.isSuccessful) {
                Toast.makeText(activity,
                    "Registro OK!", Toast.LENGTH_LONG).show()
                activity.finish()
            } else {
                Toast.makeText(activity,
                    "Registro FALHOU!", Toast.LENGTH_LONG).show()
            }
        }
}

