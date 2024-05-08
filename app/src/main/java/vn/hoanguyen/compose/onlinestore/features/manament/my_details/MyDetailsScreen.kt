@file:OptIn(ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.manament.my_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.components.AppDateTimeInput
import vn.hoanguyen.compose.onlinestore.components.AppDropdownInput
import vn.hoanguyen.compose.onlinestore.components.AppTextField
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.components.phonetextfield.AppPhoneTextField
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import java.util.Calendar

@Composable
fun MyDetailsScreen(
    onBack: () -> Unit,
) {
    val focusRequester = FocusRequester()

    val (fullName, updateFullName) = remember { mutableStateOf("Bill Gate") }
    val (email, updateEmail) = remember { mutableStateOf("bill@email.com") }


    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "My Details", onBack = onBack)
    }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                label = "Full Name",
                value = fullName,
                singleLine = true,
                onValueChange = updateFullName,
                placeholder = "Enter your full name",
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                label = "Email",
                value = email,
                singleLine = true,
                onValueChange = updateEmail,
                placeholder = "Enter your email",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            AppDateTimeInput(
                label = "Date of Birth",
                inputDefault = Calendar.getInstance().time,
                onSetValue = {

                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppDropdownInput(
                label = "Gender",
                listItems = listOf("Male", "Female"),
                defaultItem = "Male",
                onSetValue = {

                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            AppPhoneTextField()

            Spacer(modifier = Modifier.height(30.dp))

            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                content = { Text("Update") },
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun PaymentManagementScreenPrev() {
    OnlineStoreComposeTheme {
        MyDetailsScreen(onBack = {})
    }
}