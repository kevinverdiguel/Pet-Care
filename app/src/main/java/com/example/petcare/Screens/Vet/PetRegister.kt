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
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.petcare.R
import com.example.petcare.Screens.color
import com.example.petcare.ui.theme.*

class PetRegister : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDispMovTheme {
                PantallaRegistro()
            }

        }
    }
}

@Composable
fun PantallaRegistro(
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
            RegistroMascota()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .absoluteOffset(x = 0.dp, y = 310.dp)
            .fillMaxWidth()
            .height(700.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xff87E0C5))
    ){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier .padding(10.dp))
            CuadroEspecie(name="Especie")
            Spacer(modifier = Modifier .padding(10.dp))
            Genero(name = "Genero:")
            Spacer(modifier = Modifier .padding(10.dp))
            Nombre(name = "Nombre:")
            Spacer(modifier = Modifier .padding(10.dp))
            Edad(name = "Edad:                     XX/XX/XXXX")
            Spacer(modifier = Modifier .padding(10.dp))
            Esterilizado(name = "Esterilizado")
            Spacer(modifier = Modifier .padding(4.dp))
            Indicaciones(name = "Indicaciones:")
            Spacer(modifier = Modifier.padding(4.dp))
            registrarMascota(text = "Registrar mascota" )
            //Nombre()
            // Edad()
            // Esterilizado()
            //Indicaciones()
        }
    }
    }


@Composable
fun RegistroMascota() {
    val annotatedString = buildAnnotatedString {
        appendInlineContent(id = "imageId")
        append(" Registro de Mascota ")
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

@Composable
fun CuadroEspecie(name:String){

    val animales = listOf("")
    var mSelectedText by remember { mutableStateOf("") }



    Box(modifier = Modifier
        .size(height = 50.dp, width = 343.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(color = Color(0xffE2ECE9))){
        Text(text = "$name", fontSize = 24.sp, modifier = Modifier.absoluteOffset(x = 0.dp, y =10.dp ))
        val expanded by remember { mutableStateOf(false) }

    }
}

@Composable
fun Genero(name : String) {
    val listItems = arrayOf("Macho", "Hembra")
    var selectedItem by remember {
        mutableStateOf("")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }


    Box(
        modifier = Modifier
            .size(height = 50.dp, width = 343.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xffE2ECE9))
    ) {

        OutlinedTextField(
            value = selectedItem,
            onValueChange = { selectedItem },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = Text(text = "Elegir mascota")
        )

    }
}

@Composable
fun Nombre(name:String){
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Box(modifier = Modifier
        .size(height = 50.dp, width = 343.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(color = Color(0xffE2ECE9))){
        /*Text(text = "$name", fontSize = 24.sp, modifier = Modifier.absoluteOffset(x = 0.dp, y =10.dp ))*/
        TextField(value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = {Text(text = "Nombre")},
            placeholder = { Text(text = "Ingresa tu nombre")},
            modifier = Modifier.fillMaxWidth()

        )
    }
}

@Composable
fun Edad(name: String){
    Box(modifier = Modifier
        .size(height = 50.dp, width = 343.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(color = Color(0xffE2ECE9))){
        Text(text = "$name", fontSize = 24.sp, modifier = Modifier.absoluteOffset(x = 0.dp, y =10.dp ))

    }
}

@Composable
fun Esterilizado(name: String){
    Box(modifier = Modifier
        .size(height = 60.dp, width = 343.dp)
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
fun Indicaciones(name: String){
    Box(modifier = Modifier
        .size(height = 100.dp, width = 343.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(color = Color(0xffE2ECE9))){
        Text(text = "$name", fontSize = 24.sp, modifier = Modifier.absoluteOffset(x = 0.dp, y =10.dp ))

    }
}

@Composable
fun registrarMascota(text: String) {
    Button(onClick = { /*TODO*/ }){
        Text(text = "$text")
    }


}

@Preview(showBackground = true)
@Composable
fun PetRegisterView() {
    AppDispMovTheme {
        PantallaRegistro()
    }
}


