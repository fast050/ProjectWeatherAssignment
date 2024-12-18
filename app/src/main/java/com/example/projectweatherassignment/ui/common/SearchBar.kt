package com.example.projectweatherassignment.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectweatherassignment.R
import com.example.projectweatherassignment.ui.theme.GrayC4
import com.example.projectweatherassignment.ui.theme.GrayF2

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    isEnable: Boolean = true
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current // Access keyboard controller

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .weight(1f)
                .background(color = GrayF2),
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_location),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = Normal,
                    color = GrayC4
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search),
                    tint = GrayC4
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrayF2,
                unfocusedContainerColor = GrayF2,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledPlaceholderColor = GrayC4, // Placeholder color when disabled
                disabledTextColor = Color.Black, // Text color when disabled
                disabledContainerColor = GrayF2, // Keeps background color the same
                disabledIndicatorColor = Color.Transparent, // Hides disabled border
                cursorColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    keyboardController?.hide() // Explicitly hide the keyboard
                }
            ),
            enabled = isEnable,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = Normal
            )
        )
    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showSystemUi = true
)
@Composable
fun PreviewSearchBar() {
    var query by rememberSaveable { mutableStateOf("") }
    SearchBar(
        modifier = Modifier.padding(top = 100.dp, start = 20.dp, end = 20.dp),
        query = query,
        onQueryChange = {
            query = it
        }
    )
}