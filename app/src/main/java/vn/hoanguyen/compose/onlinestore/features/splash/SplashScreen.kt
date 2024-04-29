package vn.hoanguyen.compose.onlinestore.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.hoanguyen.compose.onlinestore.R
import vn.hoanguyen.compose.onlinestore.components.Loading
import vn.hoanguyen.compose.onlinestore.features.auth.AuthViewmodel
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun SplashScreen(
    authViewmodel: AuthViewmodel = hiltViewModel(),
    onNavigateWelcome: () -> Unit,
    onNavigateHome: () -> Unit,
) {
    LaunchedEffect(Unit) {
        authViewmodel.onNavigateHandle(
            onNavigateWelcome,
            onNavigateHome
        )
    }

    SplashBody()
}

@Composable
fun SplashBody() {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 50.dp),
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "app_logo"
            )

            Loading(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp)
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPrev() {
    OnlineStoreComposeTheme {
        SplashBody()
    }
}