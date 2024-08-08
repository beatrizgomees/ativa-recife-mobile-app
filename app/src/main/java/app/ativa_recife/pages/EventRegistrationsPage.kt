package app.ativa_recife.pages

import android.annotation.SuppressLint
import android.app.Activity
import android.view.Surface
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ativa_recife.di.components.CardComponent
import app.ativa_recife.di.components.PhotoUser
import app.ativa_recife.ui.theme.Blue50

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun EventRegistrationPage(modifier: Modifier = Modifier) {
    val uiColor = if (isSystemInDarkTheme()) Blue50 else Color.White
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = uiColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .align(Alignment.TopEnd) // Alinha o PhotoUser no canto superior esquerdo

            ) {
                PhotoUser()
            }
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 20.dp)
        ) {

            Text(
                text = "Suas Inscrições",
                fontSize = 20.sp,
            )
            ContentCardsEventsRegistration()
        }
    }

}

@Composable
private fun ContentCardsEventsRegistration() {
    val activity = LocalContext.current as? Activity
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Top
    ) {

        val list = (0..40).map { it.toString() }
        items(count = list.size) {
            CardComponent(registration = false, buttonLabel = "Desinscrever-se", onClick = {Toast.makeText(activity, "Inscrição removida", Toast.LENGTH_SHORT).show()} )
            Spacer(modifier = Modifier.padding(15.dp))
        }

    }
}