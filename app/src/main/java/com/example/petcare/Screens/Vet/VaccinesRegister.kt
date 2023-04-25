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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petcare.R
import com.example.petcare.Screens.color
import com.example.petcare.ui.theme.*

class VaccinesRegister : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDispMovTheme {
                VaccinesReg()
            }

        }
    }
}

@Composable
fun VaccinesReg(
    goToVetProfile: () -> Unit = {},
    goToPatients: () -> Unit = {},
    goToVetCalendar: () -> Unit = {},
    ) {
    Column(
        modifier = Modifier
            .background(color = "#CAE1D9".color)
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
                    ){
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
                    ){
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
            RegistroVacunacion()
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
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(12.dp))
            Drop(name = "Vacuna")
            Spacer(modifier = Modifier.padding(12.dp))
            Drop(name = "Fecha de Vacunacion")
            Spacer(modifier = Modifier.padding(12.dp))
            Drop(name = "Etiqueta y No. de Lote")
            Spacer(modifier = Modifier.padding(12.dp))
            Drop(name = "Sello y Firma del Veterinario")
        }
    }
}


@Composable
fun Drop(name:String){
    Box(modifier = Modifier
        .size(height = 50.dp, width = 343.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(color = Color(0xffE2ECE9))){
        Text(text = "$name", fontSize = 24.sp, modifier = Modifier.absoluteOffset(x = 0.dp, y =10.dp ))
        Image(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "", modifier = Modifier
            .align(Alignment.CenterEnd)
            .fillMaxSize()
            .absoluteOffset(x = 140.dp))
    }
}

@Composable
fun RegistroVacunacion() {
    val annotatedString = buildAnnotatedString {
        appendInlineContent(id = "imageId")
        append(" Registro de Vacunacion ")
    }
    val inlineContentMap = mapOf(
        "imageId" to InlineTextContent(
            Placeholder(80.sp, 80.sp, PlaceholderVerticalAlign.TextCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.reporte_1),
                modifier = Modifier.fillMaxSize(),
                contentDescription = ""
            )
        }
    )
    Text(
        annotatedString,
        inlineContent = inlineContentMap,
        fontSize = 25.sp,
        modifier = Modifier.absoluteOffset(x = 25.dp, y = 110.dp)
    )


}

@Preview(showBackground = true)
@Composable
fun VaccinesRegView() {
    AppDispMovTheme {
        VaccinesReg()
    }
}