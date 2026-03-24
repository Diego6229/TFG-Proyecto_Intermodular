package com.example.appgestioninventario.ui.theme.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.Image
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.appgestioninventario.R

@Composable
fun PantallaLogin(
    auth: FirebaseAuth,
    OnGoToApp: () -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var esError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo app",
            modifier = Modifier
                .height(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Iniciar Sesión",
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F4E7A)

        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email")},
            placeholder = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text("Contraseña")},
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))


        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if(email.isNotEmpty() && password.isNotEmpty()){
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if(task.isSuccessful){
                                OnGoToApp()
                            }else{
                                mensaje = "Credenciales incorrectas"
                                esError = true
                            }
                        }
                }else {
                    mensaje = "Obligatorio añadir email y contraseña"
                    esError = true
                }
            }

        ) {
            Text("Iniciar sesion")

        }

        TextButton(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()){
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                mensaje = "usuario creado correctamente"
                                esError = false
                            }else {
                                if (task.exception is com.google.firebase.auth.FirebaseAuthUserCollisionException) {
                                    mensaje = "Ese usuario ya existe"
                                    esError = true
                                }
                            }
                        }
                }else{
                    mensaje = "Obligatorio rellenar ambos campos"
                    esError = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿No tienes cuenta? Crear cuenta")
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (mensaje.isNotEmpty()){
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text =  mensaje,
                color = if (esError)
                    androidx.compose.ui.graphics.Color.Red
                else
                    androidx.compose.ui.graphics.Color(0xFF2E7D32)
            )
        }

    }
}