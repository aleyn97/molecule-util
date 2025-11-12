
# Molecule

`
Molecule is a library from cashapp, which can write business logic in Compose, and it's also Kotlin Multiplatform project. For more information: https://github.com/cashapp/molecule
`

https://github.com/cashapp/molecule

# Molecule Util
[![Maven Central](https://img.shields.io/maven-central/v/io.github.aleyn97/molecule-util)](https://github.com/aleyn97/molecule-util)
```groovy
implementation("io.github.aleyn97:molecule-util:x.y.z")
```
MoleculeUtil is an extension library for Molecule. It has the same functionality as [PreCompose](https://github.com/Tlaster/PreCompose), but integrating the org.jetbrains.kotlin.native.cocoapods plugin with the IOS platform may cause conflicts. MoleculeUtil handles conflict issues


# Usage

## Flow Action

You can write a Presenter like this:
```kotlin
@Composable
fun CounterPresenter(
    action: Flow<CounterAction>,
): CounterState {
    var count by remember { mutableStateOf(0) }

    action.collectAction {
        when (this) {
            CounterAction.Increment -> count++
            CounterAction.Decrement -> count--
        }
    }

    return CounterState("Clicked $count times")
}
```
in your Compose UI, you can use this `CounterPresenter` with `rememberPresenter`
```kotlin
val (state, channel) = rememberPresenter { CounterPresenter(it) }
```
`state` is the instance of `CounterState`, which return by `CounterPresenter`, and `channel` is the instance of `Channel<CounterEvent>`, you can send event to `CounterPresenter` by `channel.trySend(CounterEvent.Increment)`

The molecule scope and the Event Channel will be managed by the ViewModel, so it has the same lifecycle as the ViewModel.

You can nest your Presenter by using `rememberNestedPresenter`

## State Action

If you prefer using State Action, you can write a Presenter like this:
```kotlin
@Composable
fun CounterPresenter(): CounterState {
    var count by remember { mutableStateOf(0) }
    return CounterState("Clicked $count times") {
        when (it) {
            CounterAction.Increment -> count++
            CounterAction.Decrement -> count--
        }
    }
}
```

in your Compose UI, you can use this `CounterPresenter` with `producePresenter`
```kotlin
val state by producePresenter { CounterPresenter() }
```