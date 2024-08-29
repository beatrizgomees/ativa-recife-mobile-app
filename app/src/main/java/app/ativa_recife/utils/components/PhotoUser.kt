package app.ativa_recife.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.ativa_recife.R

@Composable
fun PhotoUser(modifier: Modifier = Modifier, size: Dp = 50.dp) {
    Image(
        painter = painterResource(id = R.drawable.woman),
        contentDescription = "Person",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .graphicsLayer {
                this.shape = RoundedCornerShape(20.dp)
                this.clip = true
                this.ambientShadowColor = Color.Black
                this.spotShadowColor = Color.Black
            }
            .size(size)

    )
}