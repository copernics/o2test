package xyz.coderes.o2test.ui.component.password_view

import androidx.annotation.StringRes
import xyz.coderes.o2test.R

fun validatePassword(
    password: String,
    rules: List<ValidationType> = defaultPasswordRules(),
): List<PasswordValidationResult> {
    return rules.map {
        PasswordValidationResult(
            isValid = it.validationRule(password),
            errorMessage = it.stringRes,
        )
    }
}

data class PasswordValidationResult(
    val isValid: Boolean,
    @StringRes val errorMessage: Int,
)

sealed class ValidationType(
    @StringRes val stringRes: Int,
    val validationRule: (value: String) -> Boolean,
) {
    data object MinLength :
        ValidationType(R.string.requirements_at_least_8_characters, { it.length >= 8 })

    data object HasUpperCase : ValidationType(
        R.string.requirements_at_least_one_uppercase_letter,
        { it.any { char -> char.isUpperCase() } })

    data object HasDigit : ValidationType(
        R.string.requirements_at_least_one_number,
        { it.any { char -> char.isDigit() } })

    data object HasSpecialChar :
        ValidationType(
            R.string.requirements_at_least_one_special_character,
            { it.any { char -> char in "?=#/%" } })
}