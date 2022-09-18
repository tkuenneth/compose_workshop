package eu.thomaskuenneth.counterdemo_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.app_name))
                            }
                        )
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(it),
                        contentAlignment = Center
                    ) {
                        CounterDemo()
                    }
                }
            }
        }
    }
}

@Composable
fun CounterDemo() {
    var counter by remember { mutableStateOf(0) }
    Column(horizontalAlignment = CenterHorizontally) {
        Box(
            contentAlignment = Center,
            modifier = Modifier.height(200.dp)
        ) {
            if (counter == 0) {
                Text(
                    text = "Noch nicht geklickt",
                    softWrap = true,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3
                )
            } else {
                Text(
                    text = "$counter",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h1
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

@Preview
@Composable
fun SimpleDemo() {
    var color by remember { mutableStateOf(Color.Blue) }
    Box(modifier = Modifier
        .width(128.dp)
        .height(64.dp)
        .clickable { color = if (color == Color.Blue) Color.Red else Color.Blue }
        .background(color))
}

@Composable
@Preview
fun Preview() {
    CounterDemo()
}