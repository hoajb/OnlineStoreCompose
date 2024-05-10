package vn.hoanguyen.compose.onlinestore.features.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.hoanguyen.compose.onlinestore.components.AppTextField
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onNavigationBack: () -> Unit,
    onNavigateInputCode: () -> Unit,
) {
    Scaffold(
        topBar = { AppTopAppBar(onBack = onNavigationBack) }, containerColor = Color.White
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 20.dp)
            ) {
                val focusRequester = FocusRequester()
                val (emailText, setEmailText) = remember { mutableStateOf("example@gmail.com") }
                val focusManager = LocalFocusManager.current
                val onSendCode: () -> Unit = {
                    focusManager.clearFocus()
                    onNavigateInputCode.invoke()
                }

                Text("Forgot password", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Enter your email for the verification process.\n" +
                            "We will send 4 digits code to your mail."
                )
                Spacer(modifier = Modifier.size(20.dp))

                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    label = "Email",
                    value = emailText,
                    singleLine = true,
                    onValueChange = setEmailText,
                    placeholder = "Enter your email address",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        onSendCode.invoke()
                    }),
                )
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp),
                    enabled = emailText.trim().isValidEmail(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                    ),
                    shape = RoundedCornerShape(12.dp),
                    content = { Text("Login") },
                    onClick = onSendCode
                )
            }
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordScreenPrev() {
    ForgotPasswordScreen(
        onNavigationBack = {},
        onNavigateInputCode = {}
    )
}