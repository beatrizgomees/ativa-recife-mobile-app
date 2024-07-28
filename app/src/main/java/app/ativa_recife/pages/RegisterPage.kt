package app.ativa_recife.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ativa_recife.R
import app.ativa_recife.ui.theme.RobotoRegular

@Composable
fun RegisterPage(modifier: Modifier = Modifier) {


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