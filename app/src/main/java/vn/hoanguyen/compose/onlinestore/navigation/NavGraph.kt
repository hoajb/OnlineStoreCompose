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
import vn.hoanguyen.compose.onlinestore.features.main.account.AccountScreen
import vn.hoanguyen.compose.onlinestore.features.main.cart.CartScreen
import vn.hoanguyen.compose.onlinestore.features.main.checkout.CheckoutScreen
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
        addCheckoutScreen(navController)

        addMyOrdersScreen(navController)
        addMyDetailsScreen(navController)
        addAddressBookScreen(navController)
        addPaymentMethodScreen(navController)
        addNotificationsScreen(navController)
        addFAQSScreen(navController)
        addHelpCenterScreen(navController)
    }
}

@Composable
fun NavBottomBarGraph(
    navBottomBarController: NavHostController,
    navMainController: NavHostController
) {
    NavHost(
        navController = navBottomBarController, startDestination = NavRoute.Home.path
    ) {
        addBottomBarScreen(navMainController)
    }
}

private fun NavOptionsBuilder.clear(path: String) {
    popUpTo(path) { inclusive = true }
    launchSingleTop = true
}

private fun NavOptionsBuilder.clearMain(path: String) {
    popUpTo(path) { inclusive = true }
    launchSingleTop = false
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
                navController.navigate(NavRoute.Main.path) { clearMain(NavRoute.Splash.path) }
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
                    clearMain(NavRoute.Login.path)
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
                    clearMain(NavRoute.Register.path)
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
    navMainController: NavHostController,
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
        CartScreen(
            onNavigateToCheckout = {
                navMainController.navigate(NavRoute.Checkout.path)
            }
        )
    }
    composable(route = NavRoute.Account.path) {
        AccountScreen(
            onNavigateMenu = { path -> navMainController.navigate(path) },
            onLogout = {
                navMainController.navigate(NavRoute.Login.path) {
                    clear(NavRoute.Main.path)
                }
            })
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

private fun NavGraphBuilder.addCheckoutScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Checkout.path) {
        CheckoutScreen() {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.addMyOrdersScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.MyOrders.path) {
        EmptyScreen("My Orders") {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.addMyDetailsScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.MyDetails.path) {
        EmptyScreen("My Details") {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.addAddressBookScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.AddressBook.path) {
        EmptyScreen("Address Book") {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.addPaymentMethodScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.PaymentMethods.path) {
        EmptyScreen("Payment Methods") {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.addNotificationsScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.Notification.path) {
        EmptyScreen("Notifications") {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.addFAQSScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.FAQS.path) {
        EmptyScreen("FAQs") {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.addHelpCenterScreen(
    navController: NavHostController,
) {
    composable(route = NavRoute.HelpCenter.path) {
        EmptyScreen("Help Center") {
            navController.popBackStack()
        }
    }
}