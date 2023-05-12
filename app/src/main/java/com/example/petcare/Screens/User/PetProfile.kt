package com.example.petcare.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petcare.R
import com.example.petcare.ui.theme.*

class PetProfile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDispMovTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    PetProfileMenu()
            }
            }
        }
    }
}

@Composable
fun PetProfileMenu(
    goToPetProfile: () -> Unit = {},
    goToVaccines: () -> Unit = {},
    goToUserCalendar: () -> Unit ={},
    goToLogin: () -> Unit = {},
    goToPetRegister: ()  -> Unit ={}

) {
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
                        onClick = { goToPetProfile()},
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
                        onClick = {goToUserCalendar()},
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

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(all = 80.dp)
    ) {
        Box(modifier = Modifier
            .size(width = 318.dp, height = 80.dp),
            contentAlignment = Alignment.Center

        ){
            Text(
                text = "Ultima mascota",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
            )
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(all = 80.dp)
    ) {
        Box(modifier = Modifier
            .size(width = 318.dp, height = 280.dp),
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(id = R.drawable.icons8_gato_100_1),
                modifier = Modifier.size(160.dp),
                contentDescription = ""
            )
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .absoluteOffset(x = 0.dp, y = 310.dp)
            .fillMaxWidth()
            .height(550.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xff87E0C5))
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text ="Datos",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(all = 18.dp),
                color =  md_theme_light_tertiary
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp,

                ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(all = 10.dp)
                ){

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Nombre: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_dark_tertiary)) {
                            append("Kitty")
                        }
                    }
                    )

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Raza: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_dark_tertiary)) {
                            append("Europeo")
                        }
                    }
                    )

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Edad: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_dark_tertiary)) {
                            append("2 años")
                        }
                    }
                    )

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Esterilizado: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_dark_tertiary)) {
                            append("No")
                        }
                    }
                    )

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Indicación Especial: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 22.sp, color = md_theme_dark_tertiary)) {
                            append("\n- No exponer al sol\n- Mantener dieta blanda")
                        }
                    }
                    )
                }
            }

            Button(
                onClick = { goToPetRegister() },
                enabled = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(all = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_primary)
            ) {
                Text(
                    text = "Registrar paciente",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier.wrapContentSize()
                )
            }

            Button(
                onClick = { goToLogin() },
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_onSecondaryContainer)
            ){
                Text(
                    text = "Cerrar sesión",
                    fontWeight = FontWeight.Bold,
                    color = md_theme_light_secondaryContainer,
                    fontSize = 16.sp
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ClientMenuPreview() {
    AppDispMovTheme {
        PetProfileMenu()
    }
}