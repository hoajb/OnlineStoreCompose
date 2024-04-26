package vn.hoanguyen.compose.onlinestore.features.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.hoanguyen.compose.onlinestore.components.AppPasswordTextField
import vn.hoanguyen.compose.onlinestore.components.AppTextField
import vn.hoanguyen.compose.onlinestore.components.dialog.AppDialog
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.isValidEmail
import vn.hoanguyen.compose.onlinestore.utils.isValidPassword


@Composable
fun LoginScreen(
    loginViewmodel: LoginViewmodel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToForgotPassword: () -> Unit,
) {
    var emailText by rememberSaveable { loginViewmodel.emailText }
    var passText by rememberSaveable { loginViewmodel.passText }
    val loading by rememberSaveable { loginViewmodel.loading }
    val error = loginViewmodel.error

    LoginBody(
        emailText = emailText,
        passText = passText,
        isLoading = loading,
        onEmailValueChange = { emailText = it },
        onPasswordValueChange = { passText = it },
        onActionLogin = { loginViewmodel.login(navigateToHome) },
        onNavigateToRegister = navigateToRegister,
        onNavigateToForgotPassword = navigateToForgotPassword,
    )

    when {
        error.value != "" -> {
            AppDialog(onDismissRequest = { error.value = "" }, onConfirmation = {
                error.value = ""
                println("Confirmation registered")
            }, dialogTitle = "Error", dialogText = error.value, icon = Icons.Default.Error
            )
        }
    }
}

@Composable
private fun LoginBody(
    emailText: String,
    passText: String,
    isLoading: Boolean = false,
    onEmailValueChange: (String) -> Unit = {},
    onPasswordValueChange: (String) -> Unit = {},
    onActionLogin: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Login to your account", fontWeight = FontWeight.Bold, fontSize = 28.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "It's great to see you again.", textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(30.dp))
        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            label = "Email",
            value = emailText,
            singleLine = true,
            onValueChange = onEmailValueChange,
            placeholder = "Enter your email address",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )
        Spacer(modifier = Modifier.size(10.dp))
        AppPasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            label = "Password",
            value = passText,
            onValueChange = onPasswordValueChange,
            placeholder = "Enter your password",
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onActionLogin.invoke()
            }),
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text("Forgot your password? ", fontSize = 14.sp)
            Text(
                "Reset your password",
                modifier = Modifier.clickable {
                    onNavigateToForgotPassword()
                },
                style = LocalTextStyle.current.copy(
                    color = Color.Black,
                     fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
        Spacer(modifier = Modifier.size(30.dp))

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else Button(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            content = { Text("Login") },
            enabled = emailText.trim().isValidEmail() && passText.trim().isValidPassword(),
            onClick = onActionLogin
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
//            RoundedImageButton(imageRes = R.drawable.ic_google, onClick = {
//                loginViewmodel.onLoginByGoogle()
//            })
//            RoundedImageButton(imageRes = R.drawable.ic_facebook, onClick = {
//                loginViewmodel.onLoginByFacebook()
//            })
//            RoundedImageButton(imageRes = R.drawable.ic_apple, onClick = {
//                loginViewmodel.onLoginByApple()
//            })
        }
        Spacer(modifier = Modifier.weight(weight = 1f))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have a account? ")
            Text(
                "Sign up", modifier = Modifier.clickable {
                    onNavigateToRegister()
                }, style = LocalTextStyle.current.copy(color = Color.Blue)
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPagePreview() {
    OnlineStoreComposeTheme {
        LoginBody(
            emailText = "abc@email.com", passText = ""
        )
    }
}