package vn.hoanguyen.compose.onlinestore.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography

private val ErrorColor = Color.Red

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = AppTypography.bodyMedium,
    label: String = "",
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: String = "",
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    Column {
        Text(
            label, modifier = Modifier.padding(bottom = 8.dp),
            style = AppTypography.bodyMedium
        )

        OutlinedTextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                errorIndicatorColor = ErrorColor
            ),
            shape = RoundedCornerShape(12.dp),
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            placeholder = { Text(placeholder) },
            leadingIcon = leadingIcon,
            trailingIcon = {
                if (isError) {
                    Icon(Icons.Rounded.ErrorOutline, tint = ErrorColor, contentDescription = "")
                } else {
                    trailingIcon?.invoke()
                }
            },
            prefix = prefix,
            suffix = suffix,
            supportingText = {
                if (supportingText.isNotEmpty()) {
                    Text(
                        text = supportingText,
                        style = AppTypography.bodySmall.copy(
                            color = ErrorColor,
                        ),
                    )
                }
            },
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppTextFieldPrev() {
    Surface {
        AppTextField(
            value = "abc@gmail.com",
            label = "Email",
            placeholder = "Input",
            onValueChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun AppTextFieldEmptyPrev() {
    Surface {
        AppTextField(
            value = "",
            label = "Email",
            placeholder = "Input",
            onValueChange = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun AppTextFieldErrorPrev() {
    Surface {
        AppTextField(
            value = "",
            label = "Email",
            placeholder = "Input",
            isError = true,
            supportingText = "Please enter valid email",
            onValueChange = {})
    }
}

