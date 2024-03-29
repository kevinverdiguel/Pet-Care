package com.example.petcare.Screens.Vet

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.petcare.R
import com.example.petcare.Screens.color
import com.example.petcare.ui.theme.AppDispMovTheme
import com.example.petcare.ui.theme.md_theme_light_onSecondaryContainer
import com.example.petcare.ui.theme.md_theme_light_secondaryContainer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.HashMap
import kotlin.collections.HashMap as HashMap1

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

object Option {
    const val OPTION_1 = "Macho"
    const val OPTION_2 = "Hembra"
}

object Option2 {
    const val OPTION2_1 = "Esterilizado"
    const val OPTION2_2 = "No esterilizado"
}
var mascota = hashMapOf<String, Any>(

)


@Composable
fun PantallaRegistro(
    goToVetProfile: () -> Unit = {},
    goToPatients: () -> Unit = {},
    goToVetCalendar: () -> Unit = {},
    goToPetProfile: () -> Unit = {},
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
                    modifier = Modifier
                        .fillMaxWidth()
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
            RegistroMascota()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .absoluteOffset(x = 0.dp, y = 200.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xff87E0C5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            CuadroEspecie(name = "Especie")
            Spacer(modifier = Modifier.padding(10.dp))
            Genero(name = "Genero:")
            Spacer(modifier = Modifier.padding(10.dp))
            Nombre(name = "Nombre:")
            Spacer(modifier = Modifier.padding(10.dp))
            Edad(name = "Edad:                     XX/XX/XXXX")
            Spacer(modifier = Modifier.padding(10.dp))
            Esterilizado(name = "Esterilizado")
            Spacer(modifier = Modifier.padding(4.dp))
            Indicaciones(name = "Indicaciones:")
            Spacer(modifier = Modifier.padding(4.dp))
            registrarMascota(text = "Registrar mascota", mascota)
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
fun CuadroEspecie(name: String) {

    val animales = listOf("Perro", "Gato", "Hamster", "Loro")
    var mSelectedText by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("Especie") }

    var expanded by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .size(height = 50.dp, width = 343.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xffE2ECE9))
    ) {
        Text(
            text = "$especie",
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable { expanded = true }
                .padding(end = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { (343 - 32).toDp() })
        ) {
            animales.forEach { animal ->
                DropdownMenuItem(
                    onClick = {
                        mSelectedText = animal
                        especie = mSelectedText
                        expanded = false
                        if(mascota.containsValue("especie")) {
                            mascota["especie"] = especie
                        }
                        else{
                            mascota["especie"] = especie
                        }
                    }
                ) {
                    Text(text = animal)
                }
            }
        }
    }



}

@Composable
fun Genero(name: String) {
    val listItems = arrayOf("Macho", "Hembra")
    var selectedItem by remember {
        mutableStateOf("")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var options by remember {
        mutableStateOf("")
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (selectedOption, setSelectedOption) = remember { mutableStateOf(Option.OPTION_1) }

            RadioButton(
                selected = selectedOption == Option.OPTION_1,
                onClick = { setSelectedOption(Option.OPTION_1)
                    options = Option.OPTION_1},
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = Option.OPTION_1, modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )

            RadioButton(
                selected = selectedOption == Option.OPTION_2,
                onClick = { setSelectedOption(Option.OPTION_2)
                    options = Option.OPTION_2
                          },
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = Option.OPTION_2, modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
    if (options == Option.OPTION_1) {
        mascota.put("sexo", "Macho")
    } else if(options == Option.OPTION_2) {
        mascota.put("sexo", "Hembra")
    }

}

@Composable
fun Nombre(name: String) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var nombre = text.text

    Box(
        modifier = Modifier
            .size(height = 50.dp, width = 343.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xffE2ECE9))
    ) {
        /*Text(text = "$name", fontSize = 24.sp, modifier = Modifier.absoluteOffset(x = 0.dp, y =10.dp ))*/
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = {
                Text(text = "Nombre")
            },
            placeholder = { Text(text = "Ingresa el nombre de tu mascota",
                style = MaterialTheme.typography.subtitle2.copy(fontSize = 10.sp))
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (nombre != null) {
            mascota.put("nombre", nombre)
        } else {
            // Handle the case when the entered text is not a valid integer
        }

    }
}

@Composable
fun Edad(name: String) {

    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val edad = text.text.toIntOrNull()

    Box(
        modifier = Modifier
            .size(height = 50.dp, width = 343.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xffE2ECE9))
    ) {
        TextField(
            /*text = "$name",
            fontSize = 24.sp,
            modifier = Modifier.absoluteOffset(x = 0.dp, y = 10.dp)*/
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = { Text(text = "Edad")
                    },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            placeholder = { Text(text = "Ingresa la edad de tu mascota",
                style = MaterialTheme.typography.subtitle2.copy(fontSize = 10.sp))
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (edad != null) {
            mascota.put("edad", edad)
        } else {
        }

    }
}

@Composable
fun Esterilizado(name: String) {
    val listItems = arrayOf("Esterilizado", "No esterilizado")

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    var options by remember {
        mutableStateOf("")
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var (selectedOption, setSelectedOption) = remember { mutableStateOf(Option.OPTION_1) }

            RadioButton(
                selected = selectedOption == Option.OPTION_1,
                onClick = { setSelectedOption(Option.OPTION_1)
                    options = Option.OPTION_1
                          },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = Option2.OPTION2_1, modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )

            RadioButton(
                selected = selectedOption == Option.OPTION_2,
                onClick = { setSelectedOption(Option.OPTION_2)
                    options = Option.OPTION_2

                },
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = Option2.OPTION2_2, modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }

    if (options == Option.OPTION_1) {
        mascota.put("esterilizado", "Si")
    } else  if (options == Option.OPTION_2){
        mascota.put("esterilizado", "No")
    }


    }

@Composable
fun Indicaciones(name: String) {
    Box(
        modifier = Modifier
            .size(height = 100.dp, width = 343.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xffE2ECE9))
    ) {
        Text(
            text = "$name",
            fontSize = 24.sp,
            modifier = Modifier.absoluteOffset(x = 0.dp, y = 10.dp)
        )

    }
}

@Composable
fun registrarMascota(text: String, hashMap: HashMap<String, Any>,
                     ) {
    val user = FirebaseAuth.getInstance().currentUser
    Button(onClick = { registrar(user, hashMap)

    }) {
        Text(text = "$text")

    }

}

fun registrar(userID: FirebaseUser?, hashMap: HashMap<String, Any>,
              ) {
    val db = Firebase.firestore
    val user = FirebaseAuth.getInstance().currentUser

    val userIDD = user?.uid

    if (userIDD != null) {
        hashMap.put("UserID", userIDD)
    }

    if (userIDD != null) {
        db.collection("usuarios")
            .document(userIDD.toString())
            .collection("mascotas") // Assuming you want to store "mascotas" under the user's document
            .add(mascota)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }


    
}

@Preview(showBackground = true)
@Composable
fun PetRegisterView() {
    AppDispMovTheme {
        PantallaRegistro()
    }
}


