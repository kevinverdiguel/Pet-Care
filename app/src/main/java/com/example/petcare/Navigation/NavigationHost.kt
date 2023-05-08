package com.example.petcare.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petcare.Navigation.Destinations.*
import com.example.petcare.Screens.*
import com.example.petcare.Screens.User.UserCalendar
import com.example.petcare.Screens.User.VacunasWithImage
import com.example.petcare.Screens.Vet.CalendarioView
import com.example.petcare.Screens.Vet.PantallaRegistro
import com.example.petcare.Screens.Vet.PatientsView
import com.example.petcare.Screens.Vet.VaccinesReg

@Composable
fun NavigationHost( navController:NavHostController ){


    NavHost(navController = navController, startDestination = LoginRoute.route ){

        composable(LoginRoute.route){
            LoginScreen(
            goToMenu = {
                        navController.navigate(MenuToChoose.route)
            },
            goToRegister = {
                navController.navigate(RegisterRoute.route)
            })
        }


//        composable( Destinations.Pantalla1.route ){
//            Pantalla1(navegarPantalla2 = {
//                navController.navigate(Pantalla2.route)
//            },
//                navegarPantalla3 = {
//
//                }
//            )
//
//        }
        // menu temporal routes
        composable(MenuToChoose.route){
            MenuToChoose(
            goToUserVIew = {
                navController.navigate(Destinations.PetProfile.route);


            },
            goToVetView = {
                    navController.navigate(Destinations.VetMenu.route);
            })
        }
        // rutas del veterinario
        composable(Destinations.VetMenu.route){ // menu del veterinario
            vetMenu(
            goToLogin = {
                navController.navigate(LoginRoute.route)
            },
            goToPatients = {
                navController.navigate(Patients.route)
            },
            goToVetCalendar = {
                navController.navigate(CalendarVet.route)
            },
            goToVetProfile = {
                navController.navigate(Destinations.VetMenu.route)
            }
                )
        }
        composable(Patients.route){ // pacientes del veterinairio
            PatientsView(
            goToVetProfile = {
                    navController.navigate(Destinations.VetMenu.route)
            },
            goToPatients = {
                    navController.navigate(Patients.route)
            },
            goToVetCalendar = {
                    navController.navigate(CalendarVet.route)
            },
            goToPetRegister = {
                    navController.navigate(RegisterPatients.route)
            },
            goToVaccinesRegister = {
                    navController.navigate(RegisterVaccines.route)
            }
            )

        }
            // interfaces dentro de "patients"
                            composable(RegisterPatients.route){
                                PantallaRegistro(
                                    goToVetProfile = {
                                        navController.navigate(Destinations.VetMenu.route)
                                    },
                                    goToPatients = {
                                        navController.navigate(Patients.route)
                                    },
                                    goToVetCalendar = {
                                        navController.navigate(CalendarVet.route)
                                    },
                                )
                            }

                            composable(RegisterVaccines.route){
                                VaccinesReg(
                                    goToVetProfile = {
                                        navController.navigate(Destinations.VetMenu.route)
                                    },
                                    goToPatients = {
                                        navController.navigate(Patients.route)
                                    },
                                    goToVetCalendar = {
                                        navController.navigate(CalendarVet.route)
                                    },


                                    )
                            }


        composable(CalendarVet.route){
            CalendarioView(
            goToVetCalendar = {
                    navController.navigate(CalendarVet.route)
            },
            goToPatients = {
                navController.navigate(Patients.route)
            },
            goToVetProfile = {
                navController.navigate(Destinations.VetMenu.route)
            }
            )
        }



        // -------------------rutas del usuario --------------------------------- //
        composable(Destinations.PetProfile.route){
            PetProfileMenu(
            goToPetProfile = {
                navController.navigate(Destinations.PetProfile.route)
            },
            goToVaccines = {
                navController.navigate(VaccinesUser.route)
            },
            goToUserCalendar = {
                navController.navigate(CalendarUser.route)
            },
            goToLogin = {
                    navController.navigate(LoginRoute.route)
            }
                )
        }
        // vacunas usuario

        composable(VaccinesUser.route){
            VacunasWithImage(
            goToPetProfile = {
                navController.navigate(Destinations.PetProfile.route)
            },
            goToVaccines = {
                    navController.navigate(VaccinesUser.route)
                },
            goToClaendar = {
                    navController.navigate(CalendarUser.route)
            }

                )
        }
        // calendario del usuario
        composable(CalendarUser.route){
            UserCalendar( goToUserCalendar = {
                navController.navigate(CalendarUser.route)
            },
            goToPetProfile = {
                    navController.navigate(Destinations.PetProfile.route)
            },
            goToVaccines = {
                navController.navigate(VaccinesUser.route)
            },
            goToLogin = {
                navController.navigate(LoginRoute.route)
            }

            )
        }



        // Register routes
        composable(RegisterRoute.route){
            RegisterForm( goToLogin = {
                navController.navigate(LoginRoute.route)
            },
                goToMenu = {
                    navController.navigate(MenuToChoose.route)
                }
            )
            
        }



    }
}