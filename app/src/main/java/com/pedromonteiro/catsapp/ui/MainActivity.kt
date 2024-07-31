package com.pedromonteiro.catsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.setDefaultUncaughtExceptionHandler { _, paramThrowable ->
            Log.e("Uncaught Exception", paramThrowable.message, paramThrowable)
        }

        enableEdgeToEdge()
        setContent {
            CatsApp()
        }
    }
}