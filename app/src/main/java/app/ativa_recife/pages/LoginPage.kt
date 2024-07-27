package app.ativa_recife.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ativa_recife.R
import app.ativa_recife.di.components.ButtonCustomComponent
import app.ativa_recife.di.modules.LoginInputModule
import app.ativa_recife.ui.theme.Blue50
import app.ativa_recife.ui.theme.Inter
import app.ativa_recife.ui.theme.RobotoRegular
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.ui.theme.Rubik
import app.ativa_recife.ui.theme.Typography
import com.github.beatrizgomees.weatherapp.components.InputTextCustom

@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {

    val uiColor = if (isSystemInDarkTheme())  Blue50 else Orange50
    Surface( modifier = Modifier.fillMaxSize(), color = uiColor) {
        Column(modifier = Modifier.fillMaxSize()) {

            TopSection()
            Spacer(modifier = Modifier.height(36.dp))
            LoginInputModule()




        }
    }
}

@Composable
private fun TopSection() {

    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier
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
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.40f)
                .padding(top = 50.dp, start = 20.dp, end = 20.dp),
            painter = painterResource(id = R.drawable.famillyrun),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        /*
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .align(alignment = Alignment.Center),
            ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                text = stringResource(id = R.string.login),
                style = Typography.headlineLarge,
                color = Color.White,
                fontFamily = Inter,
                fontSize = 28.sp
            )
        }

         */



        }


}