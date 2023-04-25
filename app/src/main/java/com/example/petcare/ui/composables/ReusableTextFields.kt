package com.example.petcare.ui.composables

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petcare.ui.theme.md_theme_light_error
import com.example.petcare.ui.theme.md_theme_light_surfaceTint

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String = "",
    leadingIconImageVector: ImageVector,
    leadingIconDescription: String = "",
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityChange: (Boolean) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showError: Boolean = false,
    errorMessage: String = ""
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 10.dp),
                label = { Text(label) },
                leadingIcon = {
                    Icon(
                        imageVector = leadingIconImageVector,
                        contentDescription = leadingIconDescription,
                        tint = if (showError) md_theme_light_error else md_theme_light_surfaceTint
                    )
                },
                isError = showError,
                trailingIcon = {
                    if (showError && !isPasswordField) Icon(imageVector = Icons.Filled.Error,
                    contentDescription = "Mostrar ícono de error")
                    if (isPasswordField){
                        IconButton(onClick = { onVisibilityChange(!isPasswordVisible) }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else
                                    Icons.Default.VisibilityOff,
                                contentDescription = "Mostrar contraseña"
                            )
                        }
                    }
                },
                visualTransformation = when{
                    isPasswordField && isPasswordVisible -> VisualTransformation.None
                    isPasswordField -> PasswordVisualTransformation()
                    else -> VisualTransformation.None
                },
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,

            )

    }
}
