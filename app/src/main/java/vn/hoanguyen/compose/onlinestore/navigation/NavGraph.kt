package vn.hoanguyen.compose.onlinestore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import vn.hoanguyen.compose.onlinestore.features.EmptyScreen
import vn.hoanguyen.compose.onlinestore.features.auth.ForgotPasswordScreen
import vn.hoanguyen.compose.onlinestore.features.auth.LoginScreen
import vn.hoanguyen.compose.onlinestore.features.auth.OTPScreen
import vn.hoanguyen.compose.onlinestore.features.auth.RegisterScreen
import vn.hoanguyen.compose.onlinestore.features.auth.ResetPasswordScreen
import vn.hoanguyen.compose.onlinestore.features.auth.WelcomeScreen
import vn.hoanguyen.compose.onlinestore.features.main.MainScreen
import vn.hoanguyen.compose.onlinestore.features.main.home.HomeScreen
import vn.hoanguyen.compose.onlinestore.features.main.saved.SavedScreen
import vn.hoanguyen.compose.onlinestore.features.splash.SplashScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavRoute.Main.path
    ) {
        addSplashScreen(navController)
        addWelcomeScreen(navController)
        addLoginScreen(navController)
        addRegisterScreen(navController)
        addForgotPasswordScreen(navController)
        addMainScreen(navController)
        addOTPScreen(navController)
        addResetPassScreen(navController)
    }
}

@Composable
fun NavBottomBarGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavRoute.Home.path
    ) {
        addBottomBarScreen(navController)
    }
}

private fun NavOptionsBuilder.clear(path: String) {
    popUpTo(path) { inclusive = true }
    launchSingleTop = true
}

private fun NavGraphBuilder.addSplashScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Splash.path) {
        SplashScreen(
            onNavigateWelcome = {
                navController.navigate(NavRoute.Welcome.path) { clear(NavRoute.Splash.path) }
            },
            onNavigateMain = {
                navController.navigate(NavRoute.Main.path) { clear(NavRoute.Splash.path) }
            })
    }
}

private fun NavGraphBuilder.addWelcomeScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Welcome.path) {
        WelcomeScreen(onNavigateLogin = {
            navController.navigate(NavRoute.Login.path) { clear(NavRoute.Welcome.path) }
        })
    }
}

private fun NavGraphBuilder.addLoginScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Login.path) {
        LoginScreen(
            navigateToRegister = {
                navController.navigate(NavRoute.Register.path) {
                    clear(NavRoute.Login.path)
                }
            },
            navigateToMain = {
                navController.navigate(NavRoute.Main.path) {
                    clear(NavRoute.Login.path)
                }
            },
            navigateToForgotPassword = {
                navController.navigate(NavRoute.ForgotPassword.path)
            },
        )
    }
}

private fun NavGraphBuilder.addRegisterScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Register.path) {
        RegisterScreen(
            navigateToLogin = {
                navController.navigate(NavRoute.Login.path) {
                    clear(NavRoute.Register.path)
                }
            },
            navigateToMain = {
                navController.navigate(NavRoute.Main.path) {
                    clear(NavRoute.Register.path)
                }
            },
        )
    }
}

private fun NavGraphBuilder.addMainScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Main.path) {
        MainScreen(navController)
    }
}

private fun NavGraphBuilder.addBottomBarScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Home.path) {
        HomeScreen()
    }
    composable(route = NavRoute.Search.path) {
        EmptyScreen("Search")
    }
    composable(route = NavRoute.Saved.path) {
        SavedScreen()
    }
    composable(route = NavRoute.Cart.path) {
        EmptyScreen("Cart")
    }
    composable(route = NavRoute.Account.path) {
        EmptyScreen("Account")
    }
}

private fun NavGraphBuilder.addForgotPasswordScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.ForgotPassword.path) {
        ForgotPasswordScreen(
            onNavigationBack = {
                navController.popBackStack()
            },
            onNavigateInputCode = {
                navController.navigate(NavRoute.OTP.path)
            }
        )
    }
}

private fun NavGraphBuilder.addOTPScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.OTP.path) {
        OTPScreen(
            email = "example@emial.com", //TODO
            onNavigationBack = {
                navController.popBackStack()
            },
            onOTPEntered = { otp ->
                if (otp.length > 3) {
                    navController.navigate(NavRoute.ResetPassword.path)
                }
            }
        )
    }
}

private fun NavGraphBuilder.addResetPassScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.ResetPassword.path) {
        ResetPasswordScreen(
            onNavigationBack = {
                navController.popBackStack()
            },
            onNavigationToLogin = {
                navController.navigate(NavRoute.Login.path) { clear(NavRoute.Login.path) }
            }
        )
    }
}