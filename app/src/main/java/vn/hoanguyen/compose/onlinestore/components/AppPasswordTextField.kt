package vn.hoanguyen.compose.onlinestore.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    supportingText: String = ""
) {
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    AppTextField(
        modifier = modifier,
        label = label,
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        placeholder = placeholder,
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility.value = !passwordVisibility.value
            }) {
                Icon(
                    if (passwordVisibility.value) Icons.Outlined.Visibility
                    else Icons.Outlined.VisibilityOff, "eye_icon"
                )
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        isError = isError,
        supportingText = supportingText
    )
}

@Preview
@Composable
private fun AppPasswordTextFieldPrev() {
    Surface {
        AppPasswordTextField(
            value = "12",
            label = "Password",
            placeholder = "Input",
            onValueChange = {}
        )
    }
}