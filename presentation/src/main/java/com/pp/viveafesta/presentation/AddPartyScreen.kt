package com.pp.viveafesta.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.android.material.datepicker.MaterialDatePicker
import com.pp.design.small.button.Button
import com.pp.design.small.switches.Switch
import com.pp.design.small.text.Text
import com.pp.design.small.text.TextField
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

const val AddPartyScreen = "AddPartyScreen"

private val CAMERA_PERMISSIONS = mutableListOf(Manifest.permission.CAMERA).toTypedArray()

@Composable
fun AddPartyScreen(
    viewModel: AddPartyViewModel,
    onCameraOpenRequested: () -> Unit,
    onPartyAdded: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Field(
            text = viewModel.name,
            label = "Name",
            onValueChanged = { viewModel.name = it }
        )
        Field(
            text = viewModel.district,
            label = "District",
            onValueChanged = { viewModel.district = it }
        )
        Field(
            text = viewModel.municipality,
            label = "Municipality",
            onValueChanged = { viewModel.municipality = it }
        )
        DateField(
            text = viewModel.dateRange,
            label = "Dates",
            onDateUpdated = { range ->
                val startDate = range.first?.let {
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
                        .toLocalDate()
                }
                val endDate = range.second?.let {
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
                        .toLocalDate()
                }

                viewModel.dateRange = "$startDate to $endDate"
            }
        )
        SwitchField(label = "Tem estacionamento?", isChecked = viewModel.hasParking, onCheckedChange = { viewModel.hasParking = it })
        SwitchField(label = "Tem comida?", isChecked = viewModel.hasFood, onCheckedChange = { viewModel.hasFood = it })
        SwitchField(label = "Tem diversões?", isChecked = viewModel.hasAmusement, onCheckedChange = { viewModel.hasAmusement = it })
        SwitchField(label = "É pago?", isChecked = viewModel.isPaid, onCheckedChange = { viewModel.isPaid = it })
        PosterField(poster = viewModel.poster, onCameraOpenRequested)
        Button.Primary(
            text = "Adicionar",
            onClick = {
                viewModel.addParty()
                onPartyAdded()
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
private fun SwitchField(label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch.Primary(text = label, isChecked = isChecked, onCheckedChange = onCheckedChange)
}

@Composable
fun PosterField(poster: Bitmap?, onCameraOpenRequested: () -> Unit) {
    val context = LocalContext.current

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text.BodyOne(text = "Cartaz:")
        if (poster == null) {
            Button.Primary(
                text = "Foto",
                onClick = {
                    if (context.allPermissionsGranted()) {
                        onCameraOpenRequested()
                    }
                }
            )
        } else {
            AsyncImage(model = poster, contentDescription = "cartaz")
        }
    }
}

private fun Context.allPermissionsGranted() = CAMERA_PERMISSIONS.all {
    ContextCompat.checkSelfPermission(
        this, it
    ) == PackageManager.PERMISSION_GRANTED
}