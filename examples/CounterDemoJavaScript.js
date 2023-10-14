let _counter = 0;

updateText(_counter);

function handleButtonClicked() {
    updateText(++_counter);
}

function updateText(counter) {
    if (counter == 0) {
        document.getElementById("counter").innerHTML = "<h3>Noch nicht geklickt</h3>";
    }
    else {
        document.getElementById("counter").innerHTML = `<h1>${counter}</h1>`;
    }
}