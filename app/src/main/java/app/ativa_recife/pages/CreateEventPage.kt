package app.ativa_recife.pages

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.ativa_recife.ui.theme.Yellow20
import app.ativa_recife.ui.theme.labelMediumBlack
import app.ativa_recife.utils.components.ButtonCustomComponent
import app.ativa_recife.utils.components.InputTextCustom

@Composable
fun CreateEventPage(){
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    Column( modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Text(text = "Criar Evento", Modifier.padding(start = 20.dp))

        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Name", onValueChange = { name = it }, value = name
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Text(text = "Informe o Endereço do Evento", Modifier.padding(start = 20.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Street", onValueChange = { email = it }, value = email
        )
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Neighborhood", onValueChange = { password = it }, value = password, isPassword = true
        )
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "number", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "City", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Cep", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Event Date", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Start Time", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Manager", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Route", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        InputTextCustom(
            style = labelMediumBlack, borderRadius = 20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            label = "Size of Route", onValueChange = { confirmPassword = it }, value = confirmPassword, isPassword = true
        )
        ButtonCustomComponent(
            style = labelMediumBlack, color = Yellow20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            borderRadius = 20,
            enabled = email.isNotEmpty() && name.isNotEmpty() &&
                    password.isNotEmpty() && confirmPassword.isNotEmpty()
                    && password.equals(confirmPassword),
            label = "Save",
            onClick = {

            }

        )
        Spacer(modifier = Modifier.padding(end = 50.dp))


    }

}