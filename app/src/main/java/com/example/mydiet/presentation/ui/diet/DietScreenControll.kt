package com.example.mydiet.presentation.ui.diet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun DietScreenControll (
    viewModel: DietViewModel,
    onStatusClick: (Long) -> Unit
) {

}

@Composable
fun DietScreen(
    products: State<List<Product>>,
    onStatusClick: (Long) -> Unit,
    onSearchClick: (String) -> Unit
) {
    var showSearchResultState by remember { mutableStateOf(false) }
    val searchNameState = rememberTextFieldState()

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
            textStyle = TextStyle(color = White),
            placeholder = { Text(
                text = "Поиск продуктов",
                color = Color.LightGray
            ) },
            trailingIcon = {
                IconButton(onClick = { onSearchClick(searchNameState.text.toString()) } ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = "Поиск",
                        tint = White
                    )
                }
            }

        )
        LazyColumn(
            modifier = Modifier.padding(top = 15.dp)
        ) {
            items(items = STATUSES_LIST) { status ->
                StatusListItem(status = status, onClick = onStatusClick)
            }
        }
    }
}

@Composable
fun StatusListItem(
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
@Preview(showSystemUi = true)
fun DietScreenPreview() {
    val products = remember { mutableStateOf(listOf<Product>()) }
    Scaffold(containerColor = Background) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            DietScreen(
                products = products,
                onStatusClick = {},
                onSearchClick = {},
            )
        }
    }
}

@Composable
@Preview
fun StatusListItemPreview() {
    StatusListItem(
        status = Status(name = "Статус"),
        onClick = {}
    )
}