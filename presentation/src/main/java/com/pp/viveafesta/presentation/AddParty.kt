package com.pp.viveafesta.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.core.util.Pair
import com.google.android.material.datepicker.MaterialDatePicker
import com.pp.design.small.button.Button
import com.pp.design.small.switches.Switch
import com.pp.design.small.text.Text
import com.pp.design.small.text.TextField
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.TemporalAccessor

@Composable
fun AddParty(viewModel: MainViewModel, onAddClicked: () -> Unit) {
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var municipality by remember { mutableStateOf("") }
    var dateRange by remember { mutableStateOf("") }
    var hasParking by remember { mutableStateOf(false) }
    var hasFood by remember { mutableStateOf(false) }
    var hasAmusement by remember { mutableStateOf(false) }
    var isPaid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Field(
            text = name,
            label = "Name",
            onValueChanged = { name = it }
        )
        Field(
            text = district,
            label = "District",
            onValueChanged = { district = it }
        )
        Field(
            text = municipality,
            label = "Municipality",
            onValueChanged = { municipality = it }
        )
        DateField(
            text = dateRange,
            label = "Dates",
            onDateUpdated = { range ->
                val startDate = range.first?.let {
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()).toLocalDate()
                }
                val endDate = range.second?.let {
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()).toLocalDate()
                }

                dateRange = "$startDate to $endDate"
            }
        )
        SwitchField(label = "Tem estacionamento?", onCheckedChange = { hasParking = it })
        SwitchField(label = "Tem comida?", onCheckedChange = { hasFood = it })
        SwitchField(label = "Tem diversões?", onCheckedChange = { hasAmusement = it })
        SwitchField(label = "É pago?", onCheckedChange = { isPaid = it })
        PosterField()
        Button.Primary(
            text = "Adicionar",
            onClick = {
                viewModel.addParty(
                    name = name,
                    district = district,
                    municipality = municipality,
                    dateRange = dateRange,
                    poster = null,
                    location = null,
                    hasFood = hasFood,
                    hasParking = hasParking,
                    isPaid = isPaid,
                    hasAmusement = hasAmusement,
                )
                onAddClicked()
            }
        )
    }
}

@Composable
private fun DateField(
    text: String,
    label: String,
    onDateUpdated: (Pair<Long?, Long?>) -> Unit
) {
    val activity = LocalContext.current as AppCompatActivity

    TextField.DatePicker(
        text = text,
        label = label,
        onValueChange = {},
        onClick = {
            showDatePicker(activity, onDateUpdated)
        }
    )
}

private fun showDatePicker(
    activity: AppCompatActivity,
    onDateRangeUpdated: (Pair<Long?, Long?>) -> Unit
) {
    val picker = MaterialDatePicker.Builder.dateRangePicker().build()
    picker.show(activity.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        onDateRangeUpdated(it)
    }
}

@Composable
private fun Field(text: String, label: String, onValueChanged: (String) -> Unit) {
    val fm = LocalFocusManager.current

    TextField.Primary(
        text = text,
        label = label,
        onFocusChanged = {},
        onValueChange = {
            onValueChanged(it)
        },
        keyboardActions = KeyboardActions(onDone = {
            fm.clearFocus()
        })
    )
}

@Composable
private fun SwitchField(label: String, onCheckedChange: (Boolean) -> Unit) {
    Switch.Primary(text = label, onCheckedChange = onCheckedChange)
}

@Composable
fun PosterField() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text.BodyOne(text = "Cartaz:")
        Button.Primary(text = "Foto")
    }
}