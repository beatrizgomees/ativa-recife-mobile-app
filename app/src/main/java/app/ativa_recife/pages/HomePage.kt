package app.ativa_recife.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import app.ativa_recife.R
import app.ativa_recife.activity.ui.theme.yellowPastel
import app.ativa_recife.di.components.CardComponent
import app.ativa_recife.ui.theme.Blue50
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.di.components.PhotoUser

@Preview(showBackground = true)
@Composable
fun HomePage(modifier: Modifier = Modifier) {
    val uiColor = if (isSystemInDarkTheme())  Blue50 else Color.White
    Surface( modifier = Modifier.fillMaxSize(), color = uiColor) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(
                top = 10.dp, end = 10.dp
            )) {
            LocalizationTopBar()
            PhotoUser()
        }


        Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 50.dp)

        ) {

            Text(text = "Próximos eventos perto de você", modifier = Modifier.padding(top = 30.dp))

            ContentCardsHomePage()
        }


    }


}



@Composable
private fun ContentCardsHomePage() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Top
    ) {

        val list = (0..40).map { it.toString() }
        items(count = list.size) {
            CardComponent(registration = true)
            Spacer(modifier = Modifier.padding(15.dp))
        }

    }
}

@Composable
fun LocalizationTopBar(){
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 10.dp, end = 10.dp)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.pino),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(20.dp)
                    .background(Orange50),
            ) {

                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Recife - PE",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            }
        }
    }

}

