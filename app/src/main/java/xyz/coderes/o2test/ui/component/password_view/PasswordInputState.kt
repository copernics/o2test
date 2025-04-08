package xyz.coderes.o2test.ui.component.password_view

/**
 * Represents the state of the password input, including validation results.
 */
data class PasswordInputState(
    val inputState: PasswordViewState = PasswordViewState(),
    val validation: List<PasswordValidationResult> = emptyList(),
)