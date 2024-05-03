package vn.hoanguyen.compose.onlinestore.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import vn.hoanguyen.compose.onlinestore.navigation.NavBottomBarGraph

@Composable
fun MainScreen(navMainController: NavHostController, navBottomBarController: NavHostController) {

    Scaffold(bottomBar = { AppBottomNavigationBar(navBottomBarController) }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavBottomBarGraph(navMainController, navBottomBarController)
        }
    }
}