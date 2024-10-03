package app.ativa_recife.pages

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.ativa_recife.R
import app.ativa_recife.db.fb.FBDatabase
import app.ativa_recife.ui.theme.Blue50
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.utils.components.CardComponent
import app.ativa_recife.utils.components.PhotoUser
import app.ativa_recife.viewmodel.MainViewModel

@Preview(showBackground = true)
@Composable
fun HomePage(modifier: Modifier = Modifier, viewModel: MainViewModel, fbDatabase: FBDatabase) {
    val uiColor = if (isSystemInDarkTheme())  Blue50 else Color.White
    Surface( modifier = Modifier.fillMaxSize(), color = uiColor) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(
                top = 10.dp, end = 10.dp
            )) {
            LocalizationTopBar(viewModel)
            PhotoUser()
        }

        Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 50.dp)

        ) {

            Text(text = "Próximos eventos perto de você", modifier = Modifier.padding(top = 30.dp))
            ContentCardsHomePage(viewModel, fbDatabase)
        }

    }


}

@Composable
private fun ContentCardsHomePage(viewModel: MainViewModel, fbDatabase: FBDatabase) {
    val activity = LocalContext.current as? Activity

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        var list = viewModel.eventsNear

        items(list) { event ->
            CardComponent(
                registration = true,
                buttonLabel = "Inscreva-se",
                onClick = {
                    fbDatabase.subscribeEvent(event)
                    Toast.makeText(activity,"Inscrição realizada com sucesso", Toast.LENGTH_SHORT).show()
                },
                dateEvent = event.data,
                startTimeEvent = event.startTime ,
                sizeRouteEvent = event.sizeRoute,
                managerEvent = event.manager,
                titleEvent = event.title,
                addressEvent = event.address
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun LocalizationTopBar(viewModel: MainViewModel){
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
                    text = "Recife-PE",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            }
        }
    }

}

