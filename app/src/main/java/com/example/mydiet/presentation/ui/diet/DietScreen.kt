package com.example.mydiet.presentation.ui.diet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mydiet.R
import com.example.mydiet.presentation.models.Product
import com.example.mydiet.presentation.models.Status
import com.example.mydiet.presentation.ui.BaseListItem
import com.example.mydiet.presentation.ui.theme.Background

private val STATUSES_LIST = listOf(
    Status(id = 1, name = "Разрешено"),
    Status(id = 2, name = "Под вопросом"),
    Status(id = 3, name = "Запрещено")
)

@Composable
fun DietScreen(
    viewModel: DietViewModel,
    onStatusClick: (Long) -> Unit
) {
    val products by viewModel.products.collectAsStateWithLifecycle()
    DietScreenContent(
        products = products,
        onStatusClick = onStatusClick,
        onSearchClick = { name ->
            viewModel.getProductsLikeName(name)
        }
    )
}

@Composable
private fun DietScreenContent(
    products: List<Product>,
    onStatusClick: (Long) -> Unit,
    onSearchClick: (String) -> Unit
) {
    var showSearchResultState by remember { mutableStateOf(false) }
    val searchNameState = rememberTextFieldState()
    var oldName = ""
    
    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        TextField(
            state = searchNameState,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            textStyle = TextStyle(color = Color.White),
            placeholder = {
                Text(
                    text = "Поиск продуктов",
                    color = Color.LightGray
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (oldName != searchNameState.text) {
                            oldName = searchNameState.text.toString()
                            onSearchClick(oldName)
                            showSearchResultState = true
                        } else {
                            searchNameState.clearText()
                            showSearchResultState = false
                        }

                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = "Поиск",
                        tint = if (showSearchResultState) Color.Green else Color.White
                    )
                }
            }

        )
        if (showSearchResultState) {
            val products = listOf(
                Product(
                    id = 1,
                    name = "Продукт 1",
                    status = STATUSES_LIST[0].name,
                    categoryId = 1,
                    dietId = 1
                ),
                Product(
                    id = 1,
                    name = "Продукт 2",
                    status = STATUSES_LIST[1].name,
                    categoryId = 1,
                    dietId = 1
                ),
                Product(
                    id = 1,
                    name = "Продукт 3",
                    status = STATUSES_LIST[2].name,
                    categoryId = 1,
                    dietId = 1
                )
            )
            if (products.isEmpty()) {
                Column {
                    Spacer(modifier = Modifier.fillMaxHeight(0.45f))
                    Text(
                        text = "Продукты не найдены",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

            } else {
                LazyColumn {
                    items(items = products) {
                        ProductListItem(product = it)
                    }
                }
            }
        } else {
            LazyColumn {
                items(items = STATUSES_LIST) {
                    StatusListItem(status = it, onClick = onStatusClick)
                }
            }
        }
    }
}

@Composable
private fun StatusListItem(
    status: Status,
    onClick: (Long) -> Unit
) {
    BaseListItem(
        text = status.name,
        labelText = "Статус",
        onClick = { onClick(status.id) }
    )
}

@Composable
private fun ProductListItem(
    product: Product
) {
    BaseListItem(
        text = product.name,
        labelText = "Название продукта",
        editable = true,
        deletable = true,
        onEdit = { _, _ -> },
        onDelete = {},
        hasStatus = true,
        status = product.status
    )
}

@Composable
@Preview(showSystemUi = true)
private fun DietScreenContentPreview() {
    Scaffold(containerColor = Background) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            DietScreenContent(
                products = listOf(),
                onStatusClick = {},
                onSearchClick = {},
            )
        }
    }
}

@Composable
@Preview
private fun StatusListItemPreview() {
    StatusListItem(
        status = Status(name = "Статус"),
        onClick = {}
    )
}

@Composable
@Preview
private fun ProductListItemPreview() {
    ProductListItem(
        product = Product(
            id = 1,
            name = "Продукт",
            status = "Разрешено",
            categoryId = 1,
            dietId = 1
        )
    )
}