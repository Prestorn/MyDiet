package com.example.mydiet.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydiet.R
import com.example.mydiet.presentation.ui.theme.ListItemBackground

@Composable
fun BaseListItem(
    text: String,
    labelText: String,
    editable: Boolean = false,
    deletable: Boolean = false,
    onEdit: (String, String) -> Unit = { _, _ -> },
    onDelete: () -> Unit = {},
    onClick: () -> Unit = {},
    hasStatus: Boolean = false,
    status: String = "",
    readOnlyStateInitial: Boolean = true
) {
    var readOnlyState by remember { mutableStateOf(readOnlyStateInitial) }
    val textState = rememberTextFieldState(text)
    val statusState = remember { mutableStateOf(status) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = ListItemBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(
                            top = if (readOnlyState) 5.dp else 12.dp
                        ),
                    state = textState,
                    readOnly = readOnlyState,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Transparent,
                        focusedBorderColor = Transparent,
                        unfocusedContainerColor = Transparent,
                        focusedContainerColor = Transparent
                    ),
                    label = if (readOnlyState)
                        null
                    else {
                        //TODO посмотреть анимации
                        { Text(text = labelText) }
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    contentPadding = PaddingValues(
                        top = if (readOnlyState) 14.dp else 0.dp,
                        start = 10.dp
                    )
                )

                if (hasStatus) {
                    Dropdown(
                        readOnlyState = readOnlyState,
                        statusState = statusState
                    )
                }
            }

            if (editable) {
                IconButton(
                    modifier = Modifier
                        .padding(end = 10.dp),
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(
                            if (readOnlyState) R.drawable.ic_edit else R.drawable.ic_check
                        ),
                        contentDescription = "Edit icon",
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )
                }
            }
            if (deletable) {
                IconButton(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = "Delete icon",
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )
                }
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(
    readOnlyState: Boolean,
    statusState: MutableState<String>
) {
    var expanded by remember { mutableStateOf(false) }
    val statuses = listOf("Разрешено", "Под вопросом", "Запрещено")
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it && !readOnlyState },
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                    enabled = !readOnlyState
                )
                .fillMaxWidth()
                .clickable(
                    onClick = { expanded = !expanded && !readOnlyState },
                    enabled = !readOnlyState
                )
                .padding(start = 10.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = statusState.value,
                fontSize = 16.sp
            )
            if (!readOnlyState) {
                Spacer(modifier = Modifier.width(10.dp))
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = false)
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            statuses.forEach { status ->
                DropdownMenuItem(
                    text = { Text(status) },
                    onClick = {
                        statusState.value = status
                        expanded = false
                    },
                    enabled = !readOnlyState
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00103A)
fun BaseListItemPreviewBase() {
    BaseListItem(
        text = "Test",
        editable = true,
        deletable = true,
        labelText = "Label",
        readOnlyStateInitial = true
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00103A)
fun BaseListItemPreviewOnEdit() {
    BaseListItem(
        text = "Test",
        editable = true,
        deletable = true,
        labelText = "Label",
        readOnlyStateInitial = false
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00103A)
fun BaseListItemPreviewBaseWithStatus() {
    BaseListItem(
        text = "Test",
        editable = true,
        deletable = true,
        labelText = "Label",
        hasStatus = true,
        status = "Status",
        readOnlyStateInitial = true
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00103A)
fun BaseListItemPreviewWithStatusOnEdit() {
    BaseListItem(
        text = "Test",
        editable = true,
        deletable = true,
        labelText = "Label",
        hasStatus = true,
        status = "Status",
        readOnlyStateInitial = false
    )
}