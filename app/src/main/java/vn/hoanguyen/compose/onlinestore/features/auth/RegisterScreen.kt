package vn.hoanguyen.compose.onlinestore.features.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.hoanguyen.compose.onlinestore.R
import vn.hoanguyen.compose.onlinestore.components.AppPasswordTextField
import vn.hoanguyen.compose.onlinestore.components.AppTextField
import vn.hoanguyen.compose.onlinestore.components.dialog.AppDialog
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.isValidEmail
import vn.hoanguyen.compose.onlinestore.utils.isValidPassword


@Composable
fun RegisterScreen(
    viewmodel: RegisterViewmodel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {
    var fullNameText by rememberSaveable { viewmodel.fullNameText }
    var emailText by rememberSaveable { viewmodel.emailText }
    var passText by rememberSaveable { viewmodel.passText }
    var rePassText by rememberSaveable { viewmodel.rePassText }
    val loading by rememberSaveable { viewmodel.loading }
    val error = viewmodel.error

    RegisterBody(
        fullNameText = fullNameText,
        emailText = emailText,
        passText = passText,
        rePassText = rePassText,
        isLoading = loading,
        onFullNameValueChange = { fullNameText = it },
        onEmailValueChange = { emailText = it },
        onPasswordValueChange = { passText = it },
        onRePasswordValueChange = { rePassText = it },
        onNavigateToLogin = navigateToLogin,
        onActionRegisterEmail = { viewmodel.register(navigateToHome) },
        onActionRegisterBySocialNetwork = { viewmodel.onLoginBySocialNetwork(navigateToHome) },
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
private fun RegisterBody(
    fullNameText: String,
    emailText: String,
    passText: String,
    rePassText: String,
    isLoading: Boolean = false,
    onFullNameValueChange: (String) -> Unit = {},
    onEmailValueChange: (String) -> Unit = {},
    onPasswordValueChange: (String) -> Unit = {},
    onRePasswordValueChange: (String) -> Unit = {},
    onActionRegisterEmail: () -> Unit = {},
    onActionRegisterBySocialNetwork: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    Surface(
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Create an account",
                style = AppTypography.headlineMedium,
            )
            Text(
                text = "Let's create your account.",
                style = AppTypography.bodyMedium.copy(
                    color = Color.Gray
                ),
            )
            Spacer(modifier = Modifier.size(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    label = "Full Name",
                    value = fullNameText,
                    singleLine = true,
                    onValueChange = onFullNameValueChange,
                    placeholder = "Enter your full name",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                )

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
                        onActionRegisterEmail.invoke()
                    }),
                )

                AppPasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    label = "Password",
                    value = rePassText,
                    onValueChange = onRePasswordValueChange,
                    placeholder = "Enter your password",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                        onActionRegisterEmail.invoke()
                    }),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        "By signing up you agree to our Terms, Privacy Policy, and Cookie Use ",
                        style = AppTypography.bodyMedium.copy(
                            color = Color.Gray
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
                    content = { Text("Register") },
                    enabled = emailText.trim().isValidEmail() && passText.trim().isValidPassword(),
                    onClick = onActionRegisterEmail
                )

                Spacer(modifier = Modifier.size(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(Modifier.weight(1f), color = Color.Black.copy(alpha = 0.1f))
                    Text(
                        "Or", modifier = Modifier.padding(horizontal = 8.dp),
                        style = AppTypography.bodyMedium.copy(color = Color.Gray)
                    )
                    Divider(Modifier.weight(1f), color = Color.Black.copy(alpha = 0.1f))
                }

                Spacer(modifier = Modifier.size(20.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    shape = RoundedCornerShape(12.dp),
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp),
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = "google"
                            )
                            Text(
                                "Login with Google",
                                style = AppTypography.bodyMedium.copy(
                                    color = Color.Black
                                )
                            )
                        }
                    },
                    onClick = onActionRegisterBySocialNetwork
                )
                Spacer(modifier = Modifier.size(20.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xff3975ea)),
                    shape = RoundedCornerShape(12.dp),
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Outlined.Facebook, contentDescription = "facebook")
                            Text(
                                text = "Login with Facebook",
                                style = AppTypography.bodyMedium
                            )
                        }
                    },
                    onClick = onActionRegisterBySocialNetwork
                )

                Spacer(modifier = Modifier.size(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Already have an account? ",
                        style = AppTypography.bodyMedium.copy(color = Color.Gray)
                    )
                    Text(
                        "Log In", modifier = Modifier.clickable {
                            onNavigateToLogin()
                        },
                        style = AppTypography.bodyMedium.copy(
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterPagePreview() {
    OnlineStoreComposeTheme {
        RegisterBody(
            fullNameText = "Hello",
            emailText = "abc@email.com",
            passText = "",
            rePassText = ""
        )
    }
}