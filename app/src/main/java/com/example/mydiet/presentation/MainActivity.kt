package com.example.mydiet.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mydiet.presentation.ui.dietslist.DietsListScreenControl
import com.example.mydiet.presentation.ui.dietslist.DietsListViewModel
import com.example.mydiet.presentation.ui.theme.Background
import com.example.mydiet.presentation.ui.theme.MyDietTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val dietsListViewModel by viewModel<DietsListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                containerColor = Background
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    DietsListScreenControl(
                        viewModel = dietsListViewModel,
                        onListItemClick = { Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show() }
                    )
                }
            }
        }
    }
}

