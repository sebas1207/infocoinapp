package com.example.infocoinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import com.example.infocoinapp.ui.theme.InfoCoinAppTheme
import com.example.infocoinapp.views.SettingsView

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pon un log para verificar que entra hasta aquí
        android.util.Log.d("MainActivity", "onCreate - antes de setContent")

        setContent {
            InfoCoinAppTheme {
                // Asegura que haya un Surface que ocupe toda la pantalla
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SettingsView()
                }
            }
        }
    }
}
