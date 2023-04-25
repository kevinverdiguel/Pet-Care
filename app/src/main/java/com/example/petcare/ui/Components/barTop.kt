package com.example.petcare.ui.Components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.petcare.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable

fun TopBar(
    scope:CoroutineScope,
    scaffoldState: ScaffoldState
){

    TopAppBar(
        title = {  Text(LocalContext.current.getString(R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = " Menu Icon " )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
            }
        }
    )

}