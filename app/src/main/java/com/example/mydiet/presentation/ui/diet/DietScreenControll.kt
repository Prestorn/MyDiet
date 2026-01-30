package com.example.mydiet.presentation.ui.diet

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mydiet.presentation.models.Product

@Composable
fun DietScreenControll (
    viewModel: DietViewModel,
    onStatusClick: (Long) -> Unit
) {

}

@Composable
fun DietScreen(
    products: List<Product>,
    onStatusClick: (Long) -> Unit,
    onSearchClick: (String) -> Unit
) {

}

@Composable
fun StatusListItem(
    name: String,
    onClick: (Long) -> Unit
) {

}

@Composable
@Preview(showSystemUi = true)
fun DietScreenPreview() {

}

@Composable
@Preview
fun StatusListItemPreview() {

}