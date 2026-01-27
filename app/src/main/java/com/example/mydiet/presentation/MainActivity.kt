package com.example.mydiet.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mydiet.presentation.ui.dietslist.DietsListScreenControl
import com.example.mydiet.presentation.ui.dietslist.DietsListViewModel
import com.example.mydiet.presentation.ui.theme.MyDietTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val dietsListViewModel by viewModel<DietsListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DietsListScreenControl(
                viewModel = dietsListViewModel,
                onListItemClick = {id -> Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()}
            )
        }
    }
}

