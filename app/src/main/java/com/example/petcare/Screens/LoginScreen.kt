package com.example.petcare.Screens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color.parseColor
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.petcare.ui.Components.Drawer
import com.example.petcare.ui.Components.TopBar
import com.example.petcare.Navigation.Destinations
import com.example.petcare.Navigation.NavigationHost
import com.example.petcare.R
import com.example.petcare.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppDispMovTheme {
                LoginScreen()
            }

        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun LoginScreen(
    goToMenu: () -> Unit = {},
    goToRegister: () -> Unit ={}
){
    /*
    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )

    val Scope = rememberCoroutineScope()
    val navigationItem = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3
    )

    Scaffold(
        scaffoldState = scaffoldState ,
        /* bottomBar = { BottomNavigationBar(navController = navController , items = navigationItem ) }, */
        topBar = { TopBar(Scope,scaffoldState) },
        drawerContent = { Drawer(scope = Scope, scaffoldState = scaffoldState, navController = navController , items = navigationItem ) }
    ) {
        NavigationHost(navController)
    }

     */

    val auth: FirebaseAuth
    auth = Firebase.auth
    val focusManager = LocalFocusManager.current

    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.length > 7
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }


    fun signin(){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginScreen()) { task ->
            if(task.isSuccessful){
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                goToMenu()
            } else{
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
            }
        }
    }

    Column(
        modifier = Modifier
            .background(color = md_theme_light_background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text ="¡Bienvenido a PET CARE!",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 20.dp),
            color = md_theme_light_primary
        )

        Image(
            painter = painterResource(id = R.drawable.pet_care_logo),
            contentDescription = "Pet Care Logo",
            modifier = Modifier.size(220.dp)
        )

        Text(
            text ="Inicia sesión",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(all = 18.dp),
            color =  md_theme_light_primary
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp,

            ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(all = 10.dp)
            ){

                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    label = { Text("Correo electrónico") },
                    placeholder = { Text ("abc@dominio.com") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions (
                        onNext = { focusManager.moveFocus(FocusDirection.Down)}
                    ),
                    isError = isEmailValid,
                    trailingIcon = {
                        if (email.isNotBlank()){
                            IconButton(onClick = { email = ""}) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Borrar email"
                                )

                            }
                        }
                    }

                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = { Text("Contraseña") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions (
                        onDone = { focusManager.clearFocus()}
                    ),
                    isError = !isPasswordValid,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = if(isPasswordVisible) Icons.Default.Visibility else
                                Icons.Default.VisibilityOff,
                                contentDescription = "Visibilidad de la contraseña")
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()

                )
                Button(onClick = { signin() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_primaryContainer),
                    enabled = isEmailValid && isPasswordValid)
                {
                    Text(text = "Iniciar sesión",
                        fontWeight = FontWeight.Bold,
                        color = md_theme_light_primary
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = {}){
                Text(
                    color =  md_theme_light_onSecondaryContainer,
                    fontStyle = FontStyle.Italic,
                    text = "¿Olvidaste tu contraseña?",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = {}){
                Text(
                    color =  md_theme_light_onSecondaryContainer,
                    fontStyle = FontStyle.Italic,
                    text = "¿No estás registrado?",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        Button(
            onClick = { goToRegister() },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_onSecondaryContainer)
        ){
            Text(
                text = "Regístrate aquí",
                fontWeight = FontWeight.Bold,
                color = md_theme_light_secondaryContainer,
                fontSize = 16.sp
            )
        }

    }
}

val String.color
    get()= Color(android.graphics.Color.parseColor(this))


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppDispMovTheme {
        LoginScreen()
    }
}