package vn.hoanguyen.compose.onlinestore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import vn.hoanguyen.compose.onlinestore.features.auth.ForgotPasswordScreen
import vn.hoanguyen.compose.onlinestore.features.auth.LoginScreen
import vn.hoanguyen.compose.onlinestore.features.auth.OTPScreen
import vn.hoanguyen.compose.onlinestore.features.auth.RegisterScreen
import vn.hoanguyen.compose.onlinestore.features.auth.ResetPasswordScreen
import vn.hoanguyen.compose.onlinestore.features.home.HomeScreen
import vn.hoanguyen.compose.onlinestore.features.splash.SplashScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavRoute.Splash.path
    ) {
        addSplashScreen(navController)
        addLoginScreen(navController)
        addRegisterScreen(navController)
        addForgotPasswordScreen(navController)
        addHomeScreen(navController)
        addOTPScreen(navController)
        addResetPassScreen(navController)
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
        SplashScreen(onNavigateLogin = {
            navController.navigate(NavRoute.Login.path) { clear(NavRoute.Splash.path) }
        }, onNavigateHome = {
            navController.navigate(NavRoute.Home.path) { clear(NavRoute.Splash.path) }
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
            navigateToHome = {
                navController.navigate(NavRoute.Home.path) {
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
        RegisterScreen()
    }
}

private fun NavGraphBuilder.addHomeScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Home.path) {
        HomeScreen()
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