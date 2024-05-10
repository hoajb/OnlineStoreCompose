package vn.hoanguyen.compose.onlinestore.components

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDateTimeInput(
    modifier: Modifier = Modifier,
    label: String = "",
    inputDefault: Date = Calendar.getInstance().time,
    format: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()),
    onSetValue: (Date) -> Unit
) {
    var value by remember { mutableStateOf(format.format(inputDefault)) }
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    Column(modifier = modifier
        .fillMaxWidth()
        .clickable(
            indication = null, // no ripple
            interactionSource = remember { MutableInteractionSource() }
        ) {
            showDatePicker = true
        }) {
        if (label.isNotEmpty())
            Text(
                label, modifier = Modifier.padding(bottom = 8.dp),
                style = AppTypography.bodyMedium
            )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    width = 0.5.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(12.dp)
                )
                .wrapContentHeight(align = Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value,
                style = AppTypography.bodyMedium
            )

            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "",
                tint = Color.Gray
            )
        }
    }

    val context = LocalContext.current

    // date picker component
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {  },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }
                        if (selectedDate.before(Calendar.getInstance())) {
                            value = format.format(selectedDate.time)
                            onSetValue(selectedDate.time)
                            showDatePicker = false
                        } else {
                            Toast.makeText(
                                context,
                                "Selected date should be before today, please select again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text(
                        "OK",
                        style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.W600)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text(
                        "Cancel",
                        style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.W600)
                    )
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
            )
        )
        {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview
@Composable
private fun AppDateTimeInputPrev() {
    OnlineStoreComposeTheme {
        Scaffold { padding ->
            AppDateTimeInput(
                label = "Date of Birth",
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth(),
                onSetValue = {}
            )
        }
    }
}