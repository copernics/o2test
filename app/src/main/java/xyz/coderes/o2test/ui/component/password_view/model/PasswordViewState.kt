package xyz.coderes.o2test.ui.component.password_view.model
import xyz.coderes.o2test.ui.component.input_view.model.InputViewState

/**
 * Represents the state of the password input.
 */
data class PasswordViewState(
    val value: String = "",
    val label: String = "",
    val placeholder: String = "",
    val errorText: String = "",
    val isError: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isOptional: Boolean = false,
    val enabled: Boolean = true,
)

fun PasswordViewState.toInputViewState(validation: List<PasswordValidationResult>): InputViewState {
    val isError = validation.any { !it.isValid }
    return InputViewState(
        value = value,
        label = label,
        placeholder = placeholder,
        errorText = errorText,
        isError = isError,
        isOptional = isOptional,
        isPasswordVisible = isPasswordVisible,
        enabled = enabled,
        isPassword = true
    )
}