package com.example.petcare.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route:String,
    val title:String,
    val icon: ImageVector
    // esta ruta nos servira como un id para nuestros destinos
){

    object LoginRoute: Destinations("login_route", "Login", Icons.Filled.Home)
    object Pantalla1: Destinations("pantalla1", "Pantalla1", Icons.Filled.Favorite)

    object Pantalla2: Destinations("pantalla2/{newText}", "Pantalla2", Icons.Filled.Settings )
    object MenuToChoose: Destinations("menuToChoose", "Choose", Icons.Filled.Favorite)

    object RegisterRoute: Destinations("Register", "Register", Icons.Filled.Favorite)

    //Usuario
    object PetProfile: Destinations("pet_profile", "Perfil de mascota",Icons.Filled.Favorite )
    object VaccinesUser: Destinations("vaccines_user", "vacunas del usuario",Icons.Filled.Favorite )
    object CalendarUser: Destinations("calendar_user", "calendario del usuario",Icons.Filled.Favorite )
    //Vet
    object VetMenu: Destinations("vet_menu", "menu del veterinario",Icons.Filled.Favorite )
    object Patients: Destinations("patients", "pacientes del veterinario",Icons.Filled.Favorite )
    object RegisterPatients: Destinations("register_patients", "registrar pacientes",Icons.Filled.Favorite )
    object RegisterVaccines: Destinations("register_vaccines", "registrar vacunas",Icons.Filled.Favorite )
    object CalendarVet: Destinations("calendar_vet", "valendario del veterinario", Icons.Filled.Favorite)

}