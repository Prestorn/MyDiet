package com.example.mydiet.presentation.ui.dietslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mydiet.R
import com.example.mydiet.presentation.models.Diet
import com.example.mydiet.presentation.ui.BaseListItem
import com.example.mydiet.presentation.ui.theme.Background
import com.example.mydiet.presentation.ui.theme.ListItemBackground

@Composable
fun DietsListScreenControll(
    viewModel: DietsListViewModel,
    onListItemClick: (Int) -> Unit
) {
    val diets by viewModel.diets.collectAsStateWithLifecycle(
        initialValue = TODO(),
        lifecycle = TODO(),
        minActiveState = TODO(),
        context = TODO()
    )
    DietsListScreen(
        listItems = TODO(),
        onListItemClick = TODO(),
        onEdit = TODO(),
        onDelete = TODO(),
        onAddDiet = TODO()
    )
}

@Composable
fun DietsListScreen(
    listItems: List<Diet>,
    onListItemClick: (Int) -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onAddDiet: () -> Unit
) {
    Scaffold(
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 10.dp,
                    end = 10.dp
                ),
            horizontalAlignment = Alignment.End
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp
                    )
            ) {
                items(items = listItems, key = { it.id }) {
                    DietListItem(diet = it, onClick = {})
                }
            }
            IconButton(
                onClick = { onAddDiet() },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(ListItemBackground)
                    .height(50.dp)
                    .width(50.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "Add diet",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun DietsListScreenPreview() {

    val listItems = listOf(
        Diet(1, "Low fodmap"),
        Diet(2, "Low carb"),
        Diet(3, "Low fat"),
        Diet(4, "Low sodium")
    )
    DietsListScreen(
        listItems = listItems,
        onListItemClick = {}, onEdit = {}, onDelete = {}, onAddDiet = {}
    )
}

@Composable
fun DietListItem(
    diet: Diet,
    onClick: (Long) -> Unit
) {
    Box(
        modifier = Modifier.clickable(
            onClick = {
                onClick(diet.id)
            },
        )
    ) {
        BaseListItem(
            text = diet.name,
            editable = true,
            deletable = true,
            onEdit = {_, _ ->
                TODO()
            },
            onDelete = {
                TODO()
            },
            labelText = "Название диеты"
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00103A)
fun DietListItemPreview() {
    DietListItem(
        diet = Diet(1, "Low fodmap"),
        onClick = {}
    )
}