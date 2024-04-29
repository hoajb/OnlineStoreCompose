package vn.hoanguyen.compose.onlinestore.features.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardVoice
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography

@Composable
fun HomeSearchBar(
    onSearch: (String) -> Unit,
    onFilter: () -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Row(
        Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(Modifier.weight(1f)) {
            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.LightGray,
                    unfocusedPlaceholderColor = Color.LightGray,
                ),
                shape = RoundedCornerShape(12.dp),
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.weight(1f),
                textStyle = AppTypography.bodyMedium.copy(
                    color = Color.Gray
                ),
                placeholder = { Text("Search for clothes...") },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Search,
                        tint = Color.Gray,
                        contentDescription = "search"
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Outlined.KeyboardVoice,
                        tint = Color.Gray,
                        contentDescription = "voice"
                    )
                },

                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus()
                    onSearch.invoke(searchText)
                }),
                singleLine = true,
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
                .clickable {
                    onFilter.invoke()
                }, contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.Tune,
                modifier = Modifier
                    .width(32.dp)
                    .rotate(90f),
                tint = Color.White,
                contentDescription = "voice"
            )
        }
    }
}

@Preview
@Composable
private fun HomeAppBarPrev() {
    Surface {
        HomeSearchBar(onSearch = {}, onFilter = {})
    }
}