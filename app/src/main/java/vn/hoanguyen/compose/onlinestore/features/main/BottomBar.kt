package vn.hoanguyen.compose.onlinestore.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.VisibleItem
import vn.hoanguyen.compose.onlinestore.navigation.NavRoute

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Home : BottomNavItem(NavRoute.Home.path, Icons.Outlined.Home, "Home")
    data object Search : BottomNavItem(NavRoute.Search.path, Icons.Outlined.Search, "Search")
    data object Saved : BottomNavItem(NavRoute.Saved.path, Icons.Outlined.FavoriteBorder, "Saved")
    data object Cart : BottomNavItem(NavRoute.Cart.path, Icons.Outlined.ShoppingCart, "Cart")
    data object Profile : BottomNavItem(NavRoute.Account.path, Icons.Outlined.Person, "Account")
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Search,
    BottomNavItem.Saved,
    BottomNavItem.Cart,
    BottomNavItem.Profile,
)

@Composable
fun AppBottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedItem by remember { mutableIntStateOf(0) }

    Column {
        Divider(color = Color.LightGray)
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = bottomNavItems.size,
            containerColor = Color.White,
            indicatorStyle = IndicatorStyle.NONE,
        ) {
            bottomNavItems.forEachIndexed { index, navigationItem ->
                BottomBarItem(
                    selected = currentRoute == navigationItem.route,
                    onClick = {
                        if (currentRoute != navigationItem.route) {
                            navController.navigate(navigationItem.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }

                            selectedItem = index
                        }
                    },
                    imageVector = navigationItem.icon,
                    label = navigationItem.label,
                    contentColor = Color.Black,
                    visibleItem = VisibleItem.BOTH
                )
            }
        }
    }
}

@Composable
@Deprecated("BottomBar V3")
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBar(
            containerColor = Color.White,
        ) {
            bottomNavItems.forEach { item ->
                val selected = item.route == currentRoute

                NavigationBarItem(
                    selected = selected,
                    alwaysShowLabel = false,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screenRoute ->
                                popUpTo(screenRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = {
                        Text(
                            text = item.label,
                            fontWeight = FontWeight.SemiBold,
                        )
                    },
                    icon = {
                        Column {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.label} Icon",
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPrev() {
    Scaffold(
        bottomBar = { AppBottomNavigationBar(rememberNavController()) }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues))
    }

}