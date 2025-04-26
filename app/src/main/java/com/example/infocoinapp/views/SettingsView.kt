package com.example.infocoinapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.infocoinapp.viewModels.SettingsViewModel

@Composable
fun SettingsView(viewModel: SettingsViewModel = hiltViewModel()) {
    // 1) Observamos el estado de user (puede ser null)
    val user by viewModel.user.collectAsState(initial = null)

    // 2) Lo almacenamos en una variable normal para smart-casting
    val currentUser = user

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (currentUser == null) {
            // Formulario de login
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { viewModel.signIn(email, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesión")
            }
        } else {

            Text("Conectado como ${currentUser.email}")
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { viewModel.signOut() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}
