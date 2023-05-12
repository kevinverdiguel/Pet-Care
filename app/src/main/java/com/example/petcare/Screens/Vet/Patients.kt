package com.example.petcare.Screens.Vet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petcare.R
import com.example.petcare.ui.theme.AppDispMovTheme
import com.example.petcare.ui.theme.md_theme_light_onSecondaryContainer
import com.example.petcare.ui.theme.md_theme_light_primary
import com.example.petcare.ui.theme.md_theme_light_secondaryContainer

class Patients : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDispMovTheme {
                PatientsView()
            }
        }
    }
}

@Composable
fun PatientsView(
    goToVetProfile: () -> Unit = {},
    goToPatients: () -> Unit = {},
    goToVetCalendar: () -> Unit = {},
    goToPetRegister: () -> Unit ={},
    goToVaccinesRegister: () -> Unit ={},

    ) {
    Column(
        modifier = Modifier
            .background(color = "#CAE1D9".Color)
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
                        onClick = { goToVetProfile() },
                        enabled = true,
                        modifier = Modifier
                            .padding(all = 12.dp)
                            .size(height = 40.dp, width = 80.dp)
                            .absoluteOffset(x = 1.dp, y = 1.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_onSecondaryContainer)
                    ) {
                        Text(
                            text = "Perfil",
                            fontWeight = FontWeight.Bold,
                            color = md_theme_light_secondaryContainer,
                            fontSize = 15.sp
                        )
                    }

                    Button(
                        onClick = { goToPatients() },
                        enabled = true,
                        modifier = Modifier
                            .padding(all = 12.dp)
                            .size(height = 40.dp, width = 120.dp)
                            .absoluteOffset(x = 1.dp, y = 1.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_onSecondaryContainer)
                    ) {
                        Text(
                            text = "Pacientes",
                            fontWeight = FontWeight.Bold,
                            color = md_theme_light_secondaryContainer,
                            fontSize = 15.sp
                        )
                    }

                    Button(
                        onClick = { goToVetCalendar() },
                        enabled = true,
                        modifier = Modifier
                            .padding(all = 12.dp)
                            .size(height = 40.dp, width = 150.dp)
                            .absoluteOffset(x = 1.dp, y = 1.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_onSecondaryContainer)
                    ) {
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
        Box(
            modifier = Modifier
                .size(width = 318.dp, height = 50.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color(0xff3F7875)),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "M.V.Z Ericka",
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                color = Color.White
            )
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(all = 80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 318.dp, height = 220.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "Pacientes",
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(all = 80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 318.dp, height = 340.dp),
            contentAlignment = Alignment.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_mascotas_48_1),
                modifier = Modifier.size(80.dp),
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
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(16.dp))
            Animal(name = "Roja", animal = "perro")
            Spacer(modifier = Modifier.padding(16.dp))
            Animal(name = "Kitty", animal = "gato")
            Spacer(modifier = Modifier.padding(16.dp))
            Animal(name = "Estrella", animal = "perro")
            Spacer(modifier = Modifier.padding(16.dp))
            Animal(name = "Benny", animal = "gato")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            /*Button(
                onClick = { goToPetRegister() },
                enabled = true,
                modifier = Modifier.weight(1f)
                    .padding(all = 5.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_primary)
            ) {
                Text(
                    text = "Registrar paciente",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier.wrapContentSize()
                )
            }*/

            Button(
                onClick = { goToVaccinesRegister() },
                enabled = true,
                modifier = Modifier.weight(1f)
                    .padding(all = 5.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_primary)
            ) {
                Text(
                    text = "Registrar vacuna",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.wrapContentSize()
                )
            }

        }
    }
}

@Composable
fun Animal(name: String, animal: String) {

    val gato = painterResource(id = R.drawable.icons8_gato_100_1)
    val perro = painterResource(id = R.drawable.icons8_perro_100_1)
    val annotatedString = buildAnnotatedString {
        appendInlineContent(id = "imageId")
        append(" $name ")
    }
    val inlineContentMap = mapOf(
        "imageId" to InlineTextContent(
            Placeholder(60.sp, 60.sp, PlaceholderVerticalAlign.TextCenter)
        ) {
            if (animal == "gato") Image(
                painter = gato,
                modifier = Modifier.fillMaxSize(),
                contentDescription = ""
            ) else if (animal == "perro") Image(
                painter = painterResource(id = R.drawable.icons8_perro_100_1),
                modifier = Modifier.fillMaxSize(),
                contentDescription = ""
            )
        }
    )
    Box(
        modifier = Modifier
            .size(height = 58.dp, width = 343.dp)
            .background(color = Color(0xffE2ECE9))
    ) {
        Text(annotatedString, inlineContent = inlineContentMap, fontSize = 30.sp)
        Image(
            imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.align(
                Alignment.CenterEnd
            )
        )
    }
}


val String.Color
    get()= Color(android.graphics.Color.parseColor(this))



@Preview(showBackground = true)
@Composable
fun PetView() {
    AppDispMovTheme {
        PatientsView()
    }
}