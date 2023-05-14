package com.example.petcare.Screens


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import com.example.petcare.R
import com.example.petcare.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VetMenu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDispMovTheme {
                vetMenu()
            }
        }
    }
}

@Composable
fun vetMenu(
    goToVetProfile: () -> Unit = {},
    goToPatients: () -> Unit = {},
    goToVetCalendar: () -> Unit = {},
    goToLogin: () -> Unit ={},

    ) {

    val db = Firebase.firestore // get a reference to the Firestore database
    val user = FirebaseAuth.getInstance().currentUser
    val userIDD = user?.uid

    val userRef = db.collection("usuarios").document(userIDD.toString())

    var nombre: String
    var Email: String
    var Telefono: String
    var Cedula: String
    var apellido: String

    fun getDataFromFirestore() {
        userRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()){
                    nombre = document.getString("nombre") ?: ""
                    apellido = document.getString("apellido") ?: ""
                    Telefono = document.getString("Telefono") ?: ""
                    Cedula = document.getString("Cedula") ?: ""
                    Email = document.getString("email") ?: ""



                    Log.d(ContentValues.TAG, "$nombre" + "$apellido" + "$Telefono" + "$Cedula" + "$Email")
                    // Do something with the retrieved data
                }
            }
            .addOnFailureListener { exception ->
                // Handle errors
                Log.d(ContentValues.TAG, "Nothing was found")

            }
    }

    getDataFromFirestore()

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
                        onClick = { goToVetProfile()},
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
                        onClick = {goToPatients() },
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
                        onClick = {goToVetCalendar()},
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
                text ="¡Bienvenida!",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 10.dp),
                color = md_theme_light_onTertiaryContainer
            )
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(all = 80.dp)
    ) {
        Box(modifier = Modifier
            .size(width = 318.dp, height = 220.dp),
            contentAlignment = Alignment.Center

        ){
            Text(
                text ="M.V.",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 32.sp,
                color = md_theme_light_onTertiaryContainer
            )
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(all = 80.dp)
    ) {
        Box(modifier = Modifier
            .size(width = 318.dp, height = 350.dp),
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(id = R.drawable.vet_img),
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
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text ="Datos Personales",
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
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Nombre completo: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_dark_tertiary)) {
                            append("Ericka Martínez Rivas")
                        }
                    }
                    )

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Número de cedula: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_dark_tertiary)) {
                            append("1918943")
                        }
                    }
                    )

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Estatus: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_dark_tertiary)) {
                            append("Activo")
                        }
                    }
                    )

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = {}){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                                contentDescription = "Editar"
                            )
                        }
                    }

                }
            }
            Text(
                text ="Datos de Contacto",
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
                    modifier = Modifier.padding(all = 12.dp)
                ){

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Teléfono: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_dark_tertiary)) {
                            append("81172638")
                        }
                    }
                    )
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_light_onTertiaryContainer)) {
                            append("Email: ")
                        }
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = md_theme_dark_tertiary)) {
                            append("erickamtz@hotmail.com")
                        }
                    }
                    )

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = {}){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                                contentDescription = "Editar"
                            )
                        }
                    }

                }
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
fun VetMenuPreview() {
    AppDispMovTheme {
        vetMenu()
    }
}