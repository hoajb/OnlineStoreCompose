package vn.hoanguyen.compose.onlinestore.components.phonetextfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import vn.hoanguyen.compose.onlinestore.components.ErrorColor
import vn.hoanguyen.compose.onlinestore.components.phonetextfield.component.TogiCountryCodePicker
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

//https://github.com/jump-sdk/jetpack_compose_country_code_picker_emoji/tree/master
@Composable
fun AppPhoneTextField(
    modifier: Modifier = Modifier,
) {
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var fullPhoneNumber by rememberSaveable { mutableStateOf("") }
    var isNumberValid: Boolean by rememberSaveable { mutableStateOf(false) }

    TogiCountryCodePicker(
        modifier = modifier
            .fillMaxWidth(),
        onValueChange = { (code, phone), isValid ->
            phoneNumber = phone
            fullPhoneNumber = code + phone
            isNumberValid = isValid
        },
        label = "Phone Number",
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.LightGray,
            unfocusedPlaceholderColor = Color.LightGray,
            errorIndicatorColor = ErrorColor
        ),
        showError = false,
        autoDetectCode = true,
        textStyle = AppTypography.bodyMedium
    )
}

@Preview
@Composable
private fun PhoneTextFieldPrev() {
    OnlineStoreComposeTheme {
        AppPhoneTextField()
    }
}