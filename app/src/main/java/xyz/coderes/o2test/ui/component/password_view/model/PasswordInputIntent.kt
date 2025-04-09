package xyz.coderes.o2test.ui.component.password_view.model

sealed interface PasswordInputIntent {
    data class OnPasswordChanged(val value: String) : PasswordInputIntent
    data object TogglePasswordVisibility : PasswordInputIntent
}