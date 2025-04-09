package xyz.coderes.o2test.ui.component.input_view.model

/**
 * Represents the state of the input view.
 */
data class InputViewState(
    val value: String = "",
    val label: String = "",
    val placeholder: String = "",
    val errorText: String? = null,
    val isError: Boolean = false,
    val isPassword: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isOptional: Boolean = false,
    val enabled: Boolean = true,
)