package app.ativa_recife.pages

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import app.ativa_recife.ui.theme.Orange50
import app.ativa_recife.ui.theme.focusedTextFiled
import app.ativa_recife.ui.theme.labelMediumBlack
import app.ativa_recife.ui.theme.textFieldContainer
import app.ativa_recife.ui.theme.unfocusedTextField
import app.ativa_recife.viewmodel.MainViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun SearchPage(modifier: Modifier = Modifier,
               viewModel: MainViewModel,
               context: Context
) {
    var search by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    val camPosState = rememberCameraPositionState ()
    val hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange50),
        horizontalAlignment = Alignment.CenterHorizontally
        
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(20),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextField,
                focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFiled,
                unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer
            ),
            value = search,
            label = {Text("Search",style=labelMediumBlack)},
            trailingIcon = {
                IconButton(onClick = {
                    Toast.makeText(activity, "Procurando", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
                    }
            },
            leadingIcon = {
                IconButton(onClick = { Toast.makeText(activity, "Abrindo os filtros", Toast.LENGTH_SHORT).show()}) {
                    Icon(imageVector = Icons.Filled.List, contentDescription = "Filter icon")
                }
            },
            onValueChange = {search = it},
            )
        GoogleMap (
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = camPosState,
            properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
            uiSettings = MapUiSettings(myLocationButtonEnabled = true)
        ) {}
    }
}