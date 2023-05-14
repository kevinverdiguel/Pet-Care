package com.example.petcare.Screens


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.petcare.R
import com.example.petcare.ui.composables.CustomOutlinedTextField
import com.example.petcare.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            AppDispMovTheme {
                RegisterForm()
            }
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterForm(
    goToLogin: () -> Unit = {},
    goToMenu: () -> Unit = {},
    goToVetMenu: () -> Unit = {},
    goToPetProfile: () -> Unit = {},
){

    val auth:FirebaseAuth
    auth = Firebase.auth
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollSate = rememberScrollState()

    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var cedula by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable{ mutableStateOf("") }


    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateSurname by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
    var validaterePasswordsEqual by rememberSaveable { mutableStateOf(true) }
    var validateCedula by rememberSaveable { mutableStateOf(true) }
    var validateTelefono by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateNameError = "Por favor, ingresa un nombre válido"
    var validateSurnameError = "Por favor, ingresa un apellido válido"
    var validateEmailError = "El formato de email no es correcto"
    var validatePasswordError = "Debe incluir mayúsculas y minúsculas, número, un caractér especial y mínimo ocho caractéres"
    var validateEqualPasswordError = "Las contraseñas deben ser iguales"

    var isTextFieldEnabled by remember { mutableStateOf(false) }


    var checkVet by remember {
        mutableStateOf(true)
    }

    fun validateData(name: String, surname: String, email: String, password: String, confirmPassword: String, cedula: String, telefono: String): Boolean {
        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=]).{8,}".toRegex()

        validateName = name.isNotBlank()
        validateSurname = surname.isNotBlank()
        if(checkVet){
            validateCedula = cedula.isNotBlank()
        }
        validateTelefono = telefono.isDigitsOnly()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePassword = passwordRegex.matches(password)
        validateConfirmPassword = passwordRegex.matches(password)
        validaterePasswordsEqual = password == confirmPassword

        return validateName && validateSurname && validateEmail && validatePassword && validateConfirmPassword && validaterePasswordsEqual
    }

    fun register (
        name: String,
        surname: String,
        email: String,
        password: String,
        confirmPassword: String,
        checkVet: Boolean,
        cedula: String,
        telefono: String
    ){

        if (validateData(name, surname, email, password, confirmPassword, cedula, telefono)){
            //Log.d(RegisterActivity::class.java.simpleName, "Name: $name, Surname: $surname, Password: $password")
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity()) {task ->
                if (task.isSuccessful){
                    val db = Firebase.firestore
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    val userId = user?.uid
                    //

                    if(checkVet){
                        var registro = hashMapOf(
                            "nombre" to "$name",
                            "apellido" to "$surname",
                            "email" to  "$email",
                            "id" to "$userId",
                            "Veterinario" to checkVet,
                            "Cedula" to "$cedula",
                            "Telefono" to "$telefono"
                        )
                        if (userId != null) {
                            db.collection("usuarios")
                                .document(userId)
                                .set(registro)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "DocumentSnapshot added with ID: $userId")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                        }
                        goToVetMenu()

                    }
                    else{
                        var registro = hashMapOf(
                            "nombre" to "$name",
                            "apellido" to "$surname",
                            "email" to  "$email",
                            "id" to "$userId",
                            "Veterinario" to checkVet,
                            "Telefono" to "$telefono"
                        )
                        if (userId != null) {
                            db.collection("usuarios")
                                .document(userId)
                                .set(registro)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "DocumentSnapshot added with ID: $userId")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                        }
                        goToPetProfile()

                    }



                }  else if (validateData(name, surname, email, password, confirmPassword, cedula, telefono)){
                    //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    //Toast.makeText(context,"Authetication failed", Toast.LENGTH_SHORT).show()
                } else{
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context,"Authetication failed", Toast.LENGTH_SHORT).show()
                }
            }

        }else{
            Toast.makeText(context, "Por favor, revisa los campos", Toast.LENGTH_SHORT).show()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollSate),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        androidx.compose.material.Text(
            text = "¡Regístrate fácilmente!",
            modifier = Modifier.padding(vertical = 20.dp),
            color = md_theme_light_primary,
            fontSize = 28.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold

        )

        Image(
            painter = painterResource(id = R.drawable.reg_img),
            contentDescription = "Register",
            modifier = Modifier.size(180.dp)
        )

        CustomOutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nombre",
            showError = !validateName,
            errorMessage = validateNameError,
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        CustomOutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = "Apellido",
            showError = !validateSurname,
            errorMessage = validateSurnameError,
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        CustomOutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            showError = !validateEmail,
            errorMessage = validateEmailError,
            leadingIconImageVector = Icons.Default.AlternateEmail,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            ),
        )

        CustomOutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = "Telefono",
            showError = !validateTelefono,
            leadingIconImageVector = Icons.Default.Phone,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            ),
        )


        CustomOutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = "Contraseña (8 caracteres. 1 mayuscula, 1 caracter especial y 1 numero.)",
            showError = !validatePassword,
            errorMessage = validatePasswordError,
            isPasswordField = true,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange = { isPasswordVisible = it },
            leadingIconImageVector = Icons.Default.Password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            )
        )

        CustomOutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirmar contraseña",
            showError = !validateConfirmPassword || !validaterePasswordsEqual,
            errorMessage = if (!validateConfirmPassword) validatePasswordError else validateEqualPasswordError,
            isPasswordField = true,
            isPasswordVisible = isConfirmPasswordVisible,
            onVisibilityChange = { isConfirmPasswordVisible = it },
            leadingIconImageVector = Icons.Default.Password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions (
                onDone = { focusManager.clearFocus()}
            )
        )


        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checkVet,
                onCheckedChange = { isChecked ->
                    checkVet = isChecked
                    isTextFieldEnabled = isChecked
                    cedula = ""
                }
            )
            Text("Soy veterinario")

        }
        CustomOutlinedTextField(
            value = cedula,
            onValueChange = { cedula = it },
            label = "Ingresar tu cedula",
            leadingIconImageVector = Icons.Default.ContactPage,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions (
                onDone = { focusManager.clearFocus()}
            ),
            enabled = checkVet
        )






        androidx.compose.material3.Button(
            onClick = {
                register(name, surname, email, password, confirmPassword, checkVet, cedula, telefono)
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                md_theme_light_primaryContainer,
                contentColor = md_theme_light_onPrimaryContainer
            )
        ) {
            androidx.compose.material3.Text(
                text = "Registrarse",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = md_theme_light_primary
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = { goToLogin() }){
                androidx.compose.material.Text(
                    color = md_theme_light_onSecondaryContainer,
                    fontStyle = FontStyle.Italic,
                    text = "¿Ya tienes una cuenta? INICIA SESIÓN",
                    modifier = Modifier.padding(all = 0.dp)
                )
            }
        }
    }
}

