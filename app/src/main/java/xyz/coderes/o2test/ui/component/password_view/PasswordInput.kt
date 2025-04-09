package xyz.coderes.o2test.ui.component.password_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.coderes.o2test.ui.component.input_view.InputView
import xyz.coderes.o2test.ui.component.input_view.model.InputViewIntent
import xyz.coderes.o2test.ui.component.password_view.model.PasswordInputIntent
import xyz.coderes.o2test.ui.component.password_view.model.PasswordInputState
import xyz.coderes.o2test.ui.component.password_view.model.PasswordValidationResult
import xyz.coderes.o2test.ui.component.password_view.model.PasswordViewState
import xyz.coderes.o2test.ui.component.password_view.model.ValidationType
import xyz.coderes.o2test.ui.component.password_view.model.toInputViewState
import xyz.coderes.o2test.ui.theme.AppColors
import xyz.coderes.o2test.ui.theme.LocalTypography

/**
 * Represents a password input view.
 */
@Composable
fun PasswordInput(
    state: PasswordInputState,
    onIntent: (PasswordInputIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        InputView(
            modifier = Modifier.fillMaxWidth(),
            state = state.inputState.toInputViewState(state.validation),
            onIntent = {
                when (it) {
                    is InputViewIntent.ValueChanged -> {
                        onIntent(PasswordInputIntent.OnPasswordChanged(it.value))
                    }

                    is InputViewIntent.TogglePasswordVisibility -> {
                        onIntent(PasswordInputIntent.TogglePasswordVisibility)
                    }
                }
            }
        )
//        AnimatedVisibility(
//            visible = state.validation.any{!it.isValid},
//            ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                items(state.validation.size) {
                    PasswordRequirementRow(
                        text = stringResource(state.validation[it].errorMessage),
                        isValid = state.validation[it].isValid
                    )
                }
            }
//        }
    }
}


/**
 * Represents a password requirement row.
 */
@Composable
fun PasswordRequirementRow(text: String, isValid: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(2.dp)) {
        Icon(
            imageVector = if (isValid)
                Icons.Default.Check
            else
                Icons.Default.Close,
            contentDescription = null,
            tint = if (isValid)
                AppColors.Content.OnNeutralMedium
            else
                AppColors.Content.OnNeutralDanger
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text, style = LocalTypography
                .current
                .labelSmall.copy(
                    color = if (isValid)
                        AppColors.Content.OnNeutralMedium
                    else
                        AppColors.Content.OnNeutralDanger
                )
        )
    }
}

@Preview(name = "RequirementRow, positive")
@Composable
fun PasswordRequirementRowPreviewPositive() {
    PasswordRequirementRow("At least 8 characters", true)
}

@Preview(name = "RequirementRow, negative")
@Composable
fun PasswordRequirementRowPreviewNegative() {
    PasswordRequirementRow("At least 8 characters", false)
}

@Stable
fun defaultPasswordRules(): List<ValidationType> {
    return listOf(
        ValidationType.MinLength, ValidationType.HasUpperCase,
        ValidationType.HasDigit, ValidationType.HasSpecialChar
    )
}

@Preview
@Composable
fun PasswordInputPreview() {
    Box (modifier = Modifier.fillMaxWidth().background(Color.White)) {
        PasswordInput(
            modifier = Modifier.padding(16.dp),
            state = PasswordInputState(
                PasswordViewState(
                    value = "",
                    label = "Password",
                    placeholder = "Enter password",
                    errorText = "",
                    isPasswordVisible = false,
                    isOptional = false,
                    enabled = true,
                ),
                defaultPasswordRules().map {
                    PasswordValidationResult(
                        isValid = true,//Random.nextBoolean(),
                        errorMessage = it.stringRes,
                    )
                }),
            onIntent = {}
        )
    }
}
