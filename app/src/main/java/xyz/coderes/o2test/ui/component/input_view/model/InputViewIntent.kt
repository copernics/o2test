package xyz.coderes.o2test.ui.component.input_view.model

/**
 * Represents an intent that can be triggered by the password input view.
 */
sealed interface InputViewIntent {
    data class ValueChanged(val value: String) : InputViewIntent
    data object TogglePasswordVisibility : InputViewIntent
}