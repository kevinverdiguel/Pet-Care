package com.example.petcare.ui.Components


import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.petcare.Navigation.Destinations
import com.example.petcare.Navigation.currentRoute
import com.example.petcare.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.format.TextStyle

@Composable

fun Drawer(
    scope:CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    items: List<Destinations>
){

    Column() {
        Image(
            painter = painterResource(id = R.drawable.ilustracion_coleccion_perros_53876_17258), contentDescription = "Bg Image",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route){
                navController.navigate(item.route){
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }

}

@Composable
fun DrawerItem(
    item:Destinations,
    selected:Boolean,
    onItemClick: (Destinations) -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(if (selected) Color.Blue.copy(alpha = 0.25f) else Color.Transparent)
            .padding(8.dp)
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = item.icon ,
            contentDescription = item.title,
            tint = if (selected) Color.Blue else Color.Black
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item.title,
            color = if(selected) Color.Blue else Color.Black
        )



    }

}