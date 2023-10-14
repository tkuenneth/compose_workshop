package eu.thomaskuenneth.composeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // ComposeWorkshopScreen()
                // CounterDemo()
                ColoredBoxesDemo()
            }
        }
    }
}

@Composable
@Preview
fun ComposeWorkshopScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello Jetpack Compose",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
@Preview
fun CounterDemo() {
    var counter by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .height(100.dp)
        ) {
            if (counter == 0) {
                Text(
                    text = "Noch nicht geklickt",
                    softWrap = true,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            } else {
                Text(
                    text = "$counter",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
        Button(
            onClick = { counter += 1 }
        ) {
            Text(text = "Klick")
        }
    }
}

@Composable
@Preview
fun CheckboxDemo() {
    var state by remember { mutableStateOf(false) }
    Checkbox(
        checked = state,
        onCheckedChange = {
            state = it
        }
    )
}

@Composable
@Preview
fun ButtonDemo() {
    var counter by remember { mutableStateOf(1) }
    Button(
        onClick = {
            counter += 1
        }
    ) {
        Text(
            text = counter.toString()
        )
    }
}

@Composable
@Preview
fun TextFieldDemo() {
    var text by remember { mutableStateOf("hello") }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text("label") },
        placeholder = { Text("placeholder") }
    )
}

@Composable
@Preview
fun SliderDemo() {
    var value by remember { mutableStateOf(0.5F) }
    Slider(
        value = value,
        onValueChange = {
            value = it
        },
        steps = 5
    )
}

@Composable
@Preview
fun ListDemo() {
    val list: List<Int> = (1..3).toList()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = list
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primary),
                text = it.toString(),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun ColoredBoxesDemo() {
    var red by remember { mutableStateOf(true) }
    var green by remember { mutableStateOf(true) }
    var blue by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CheckboxWithLabel(
            label = stringResource(id = R.string.red),
            checked = red,
            onClicked = { red = it }
        )
        CheckboxWithLabel(
            label = stringResource(id = R.string.green),
            checked = green,
            onClicked = { green = it }
        )
        CheckboxWithLabel(
            label = stringResource(id = R.string.blue),
            checked = blue,
            onClicked = { blue = it }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            if (red) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                )
            }
            if (green) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                        .background(Color.Green)
                )
            }
            if (blue) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(64.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@Composable
fun CheckboxWithLabel(
    label: String,
    checked: Boolean,
    onClicked: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onClicked(!checked)
        }, verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                onClicked(it)
            }
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
