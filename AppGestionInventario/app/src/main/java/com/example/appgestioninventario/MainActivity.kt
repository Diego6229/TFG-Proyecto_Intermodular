package com.example.appgestioninventario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appgestioninventario.ui.theme.App.AppScreen
import com.example.appgestioninventario.ui.theme.AppGestionInventarioTheme
import com.example.appgestioninventario.ui.theme.login.PantallaLogin
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGestionInventarioTheme {
                val navController = rememberNavController()
                auth = FirebaseAuth.getInstance()

                NavHost(
                    navController = navController,
                    startDestination = "Login"
//
                ) {
                    composable("Login"){
                        PantallaLogin (
                            auth =auth,
                            OnGoToApp = {
                                navController.navigate("App"){
                                    popUpTo("Login"){
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable("App"){
                        AppScreen (
                            OnGoToLogin = {
                                auth.signOut()
                                navController.navigate("Login") {
                                    popUpTo("App"){
                                        inclusive = true
                                    }
                                }
                            }

                        )
                    }

                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppGestionInventarioTheme {

    }
}