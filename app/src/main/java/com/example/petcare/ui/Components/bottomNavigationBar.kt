package com.example.petcare.ui.Components


import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.petcare.Navigation.Destinations
import com.example.petcare.Navigation.currentRoute

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<Destinations>
){

    val currentRoute = currentRoute(navController)

    BottomNavigation() {

        items.forEach{ screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title ) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route ,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }

                        launchSingleTop = true
                    }
                },
                alwaysShowLabel = false
            )

        }



    }

}
