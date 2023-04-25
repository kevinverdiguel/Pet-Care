package com.example.petcare.Screens.User

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petcare.R
import com.example.petcare.ui.theme.AppDispMovTheme
import com.example.petcare.ui.theme.md_theme_light_onSecondaryContainer
import com.example.petcare.ui.theme.md_theme_light_secondaryContainer

class Vaccines : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDispMovTheme  {
                Surface(color = MaterialTheme.colors.background) {
                    VacunasWithImage( )
                }
            }
        }
    }
}

@Composable
fun TipoVac(message: String) {
    // Create a column so that texts don't overlap
    Column {
        Text(
            text = " ",
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, top = 16.dp)

        )
        Text(
            text = message,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun Sello(nombrevac: String){
    Text(
        text = nombrevac,
        fontSize = 18.sp,
        modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(start = 40.dp, top = 1.dp)
    )
}

@Composable
fun VacunasWithImage(
    goToPetProfile: () -> Unit = {},
    goToVaccines: () -> Unit = {},
    goToClaendar: () -> Unit ={}
) {
    val bg = painterResource(R.drawable.bg)
    val image = painterResource(R.drawable.garantia)
    Box {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color(0xFFE2ECE9)
                    )
            ) {
                Box(
                    Modifier
                        .background(color = Color(0xffC3DED6))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { goToPetProfile() },
                            enabled = true,
                            modifier = Modifier
                                .padding(all = 12.dp)
                                .size(height = 40.dp, width = 80.dp)
                                .absoluteOffset(x = 1.dp, y = 1.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_onSecondaryContainer)
                        ){
                            Text(
                                text = "Perfil",
                                fontWeight = FontWeight.Bold,
                                color = md_theme_light_secondaryContainer,
                                fontSize = 15.sp
                            )
                        }

                        Button(
                            onClick = { goToVaccines() },
                            enabled = true,
                            modifier = Modifier
                                .padding(all = 12.dp)
                                .size(height = 40.dp, width = 120.dp)
                                .absoluteOffset(x = 1.dp, y = 1.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_onSecondaryContainer)
                        ){
                            Text(
                                text = "Vacunas",
                                fontWeight = FontWeight.Bold,
                                color = md_theme_light_secondaryContainer,
                                fontSize = 15.sp
                            )
                        }

                        Button(
                            onClick = { goToClaendar() },
                            enabled = true,
                            modifier = Modifier
                                .padding(all = 12.dp)
                                .size(height = 40.dp, width = 150.dp)
                                .absoluteOffset(x = 1.dp, y = 1.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_onSecondaryContainer)
                        ){
                            Text(
                                text = "Calendario",
                                fontWeight = FontWeight.Bold,
                                color = md_theme_light_secondaryContainer,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }



        TipoVac(message = "Vacunas Vigentes")
        Column {
            Row {
                Column {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 120.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row{
                        Sello(nombrevac = "Rabia")
                    }
                }
                Column {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 120.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row{
                        Sello(nombrevac = "Rabia")
                    }
                }
                Column {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 120.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)

                    )
                    Row{
                        Sello(nombrevac = "Triple")
                    }
                }
            }
            Row{
                Column {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 40.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row{
                        Sello(nombrevac = "Rabia")
                    }
                }
                Column{
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 40.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row{
                        Sello(nombrevac = "Rabia")
                    }
                }
                Column{
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 40.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row{
                        Sello(nombrevac = "P.Virus")
                    }
                }
            }
            TipoVac(message = "Vacunas Pendientes")
            Row {
                Column {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 5.dp,
                                top = 30.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row {
                        Sello(nombrevac = "Rabia")
                    }
                }
                Column{
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 5.dp,
                                top = 30.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row{
                        Sello(nombrevac = "Rabia")
                    }
                }
                Column{
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 5.dp,
                                top = 30.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row{
                        Sello(nombrevac = "Triple")
                    }
                }

            }
            Row{
                Column {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(
                                start = 0.dp,
                                top = 50.dp,
                                bottom = 0.dp,
                                end = 0.dp
                            )
                            .width(120.dp)
                            .height(82.dp)
                    )
                    Row{
                        Sello(nombrevac = "Rabia")
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VacunasPreview() {
    AppDispMovTheme {
        VacunasWithImage( )
    }
}