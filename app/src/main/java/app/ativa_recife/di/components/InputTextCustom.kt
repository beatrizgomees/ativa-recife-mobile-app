package app.ativa_recife.di.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import app.ativa_recife.ui.theme.focusedTextFiled
import app.ativa_recife.ui.theme.textFieldContainer
import app.ativa_recife.ui.theme.unfocusedTextField

@Composable
fun InputTextCustom(label: String,
                    modifier: Modifier = Modifier,
                    onValueChange: (String) -> Unit,
                    value: String,
                    isPassword: Boolean = false,
                    borderRadius: Int,
                    style: TextStyle,

) {
        Column(modifier = modifier) {
            OutlinedTextField(
                modifier = modifier,
                value = value,
                label = { Text(label, style = style) },
                shape = RoundedCornerShape(borderRadius),
                onValueChange = onValueChange,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextField,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFiled,
                    unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
                    focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer
                ),
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
            )
        }
}
