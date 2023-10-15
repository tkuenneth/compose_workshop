package eu.thomaskuenneth.composeworkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
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
                // ColoredBoxesDemo()
                ScaffoldDemo()
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
        contentAlignment = Center
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ScaffoldDemo(
    content: @Composable (PaddingValues) -> Unit = {}
) {
    var modalBottomSheetOpen by remember { mutableStateOf(false) }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name)
                )
            },
            actions = {
                IconButtonWithTooltip(
                    onClick = { modalBottomSheetOpen = true },
                    painter = painterResource(id = R.drawable.baseline_info_24),
                    contentDescription = stringResource(id = R.string.info)
                )
                MenuDemo(
                    menuItems = stringArrayResource(id = R.array.menu_items).toList(),
                    onClick = { modalBottomSheetOpen = false }
                )
            }
        )
    }) {
        content(it)
        if (modalBottomSheetOpen) ModalBottomSheetDemo {
            modalBottomSheetOpen = false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconButtonWithTooltip(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String
) {
    PlainTooltipBox(tooltip = {
        Text(text = contentDescription)
    }) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.tooltipAnchor()
        ) {
            Icon(
                painter = painter,
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
fun MenuDemo(
    menuItems: List<String>,
    onClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = {
            expanded = true
        }) {
            Icon(Icons.Default.MoreVert, stringResource(id = R.string.options_menu))
        }
        if (menuItems.isNotEmpty()) DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            menuItems.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onClick(index)
                    },
                    text = {
                        Text(s)
                    })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetDemo(dismissed: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = dismissed,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Center
        ) {
            Text(text = stringResource(id = R.string.app_name))
        }
    }
}

@Preview
@Composable
fun StateChangeDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val color = if (toggled) Color.White else Color.Red
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(stringResource(R.string.toggle))
        }
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .background(color = color)
                .size(128.dp)
        )
    }
}

@Composable
@Preview
fun SingleValueAnimationDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (toggled) Color.White else Color.Red,
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
    )
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(stringResource(R.string.toggle))
        }
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .background(color = color)
                .size(128.dp)
        )
    }
}

@Composable
@Preview
fun MultipleValuesAnimationDemo() {
    var toggled by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = toggled)
    val borderWidth by transition.animateDp(label = "borderWidthTransition") { state ->
        if (state) 10.dp else 1.dp
    }
    val degrees by transition.animateFloat(label = "degreesTransition") { state ->
        if (state) -90F else 0F
    }
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(
                stringResource(R.string.toggle)
            )
        }
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .padding(top = 32.dp)
                .border(
                    width = borderWidth,
                    color = Color.Black
                )
                .size(128.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.rotate(degrees = degrees)
            )
        }
    }
}

@Composable
@Preview(widthDp = 128, heightDp = 230)
fun AnimatedVisibilityDemo() {
    var visible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Button(onClick = {
            visible = !visible
        }) {
            Text(
                stringResource(
                    id = if (visible) R.string.hide else R.string.show
                )
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(),
            exit = slideOutVertically()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .background(color = Color.Red)
                    .size(128.dp)
            )
        }
    }
}

@Preview
@Composable
fun CrossFadeAnimationDemo() {
    var isFirstScreen by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(192.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            checked = isFirstScreen,
            onCheckedChange = {
                isFirstScreen = !isFirstScreen
            },
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Crossfade(targetState = isFirstScreen) {
            if (it) {
                Screen(
                    text = stringResource(id = R.string.letter_w),
                    backgroundColor = Color.Gray
                )
            } else {
                Screen(
                    text = stringResource(id = R.string.letter_i),
                    backgroundColor = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun Screen(
    text: String,
    backgroundColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
