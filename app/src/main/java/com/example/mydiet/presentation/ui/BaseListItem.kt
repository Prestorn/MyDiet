package com.example.mydiet.presentation.ui

import android.widget.Spinner
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    editable: Boolean,
    deletable: Boolean,
    onEdit: (String) -> Unit,
    onDelete: () -> Unit,
    labelText: String,
    hasStatus: Boolean = false,
    status: String = "",
    readOnlyStateInitial: Boolean = true
) {
    var readOnlyState by remember { mutableStateOf(readOnlyStateInitial) }
    val textState = rememberTextFieldState(text)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = ListItemBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            top = if (readOnlyState) 5.dp else 12.dp,
                            bottom = 5.dp
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
                if (editable) {
                    IconButton(
                        modifier = Modifier
                            .padding(end = 10.dp),
                        onClick = {  }
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
                        onClick = {  }
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
            if (hasStatus) {
                // Выпадающий список
            }
        }

    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00103A)
fun BaseListItemPreview() {
    BaseListItem(
        text = "Test",
        editable = true,
        deletable = true,
        onEdit = {},
        onDelete = {},
        labelText = "Label",
        readOnlyStateInitial = true
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00103A)
fun BaseListItemPreview2() {
    BaseListItem(
        text = "Test",
        editable = true,
        deletable = true,
        onEdit = {},
        onDelete = {},
        labelText = "Label",
        readOnlyStateInitial = false
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00103A)
fun BaseListItemPreview3() {
    BaseListItem(
        text = "Test",
        editable = true,
        deletable = true,
        onEdit = {},
        onDelete = {},
        labelText = "Label",
        hasStatus = true,
        status = "Status",
        readOnlyStateInitial = true
    )
}