package eu.thomaskuenneth.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CheckboxDemo() {
    var state by remember { mutableStateOf(false) }
    Checkbox(
        checked = state,
        onCheckedChange = {
            state = it
        }
    )
}

@Preview
@Composable
fun ButtonDemo() {
    var counter by remember { mutableStateOf(1) }
    Button(onClick = { counter += 1 }) {
        Text(
            text = counter.toString()
        )
    }
}

@Preview
@Composable
fun TextFieldDemo() {
    var text by remember { mutableStateOf("hello") }
    TextField(value = text,
        onValueChange = { text = it },
        label = { Text("label") },
        placeholder = { Text("placeholder") })
}

@Preview
@Composable
fun SliderDemo() {
    var value by remember { mutableStateOf(0.5F) }
    Slider(
        value = value,
        onValueChange = { value = it },
        steps = 5
    )
}

@Preview
@Composable
fun ColoredBoxDemo() {
    Box(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxSize()
            .padding(64.dp)
            .background(Color.Blue)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp, 128.dp)
                .background(color = Color.Green)
                .align(alignment = Alignment.Center)
        )
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun ExposedDropdownMenuBoxDemo() {
    val titles = List(3) { i ->
        stringResource(id = R.string.item, i + 1)
    }
    var expanded by remember { mutableStateOf(false) }
    var selectedTxt by remember { mutableStateOf(titles[0]) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        ExposedDropdownMenuBox(expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }) {
            TextField(value = selectedTxt,
                onValueChange = { },
                readOnly = true,
                label = {
                    Text(text = stringResource(id = R.string.label))
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                }
            )
            ExposedDropdownMenu(expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }) {
                for (title in titles) {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        selectedTxt = title
                    }) {
                        Text(text = title)
                    }
                }
            }
        }
    }
}
