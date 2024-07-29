package com.pedromonteiro.catsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pedromonteiro.catsapp.ui.CatsApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler { paramThread, paramThrowable ->
            Log.e("Uncaught Exception", paramThrowable.message, paramThrowable)
        }

        enableEdgeToEdge()
        setContent {
            CatsApp()
        }
    }
}