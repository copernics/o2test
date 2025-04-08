package xyz.coderes.o2test.ui.component.input_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.coderes.o2test.R
import xyz.coderes.o2test.ui.theme.AppColors
import xyz.coderes.o2test.ui.theme.LocalShapes
import xyz.coderes.o2test.ui.theme.LocalTypography
import xyz.coderes.o2test.ui.theme.O2testTheme

/**
 * Represents an universal input view.
 */
@Composable
fun InputView(
    modifier: Modifier = Modifier,
    state: InputViewState,
    onIntent: (InputViewIntent) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(modifier = modifier.testTag("InputView")) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                text = state.label,
                style = LocalTypography.current.bodyMedium,
                color = if (state.isError) {
                    AppColors.Content.OnNeutralDanger
                } else {
                    AppColors.Content.OnNeutralXxHigh
                }
            )
            if (state.isOptional) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(R.string.optional),
                    style = LocalTypography
                        .current
                        .bodyMedium
                        .copy(color = AppColors.Content.OnNeutralLow)
                )
            }
        }

        OutlinedTextField(
            value = state.value,
            onValueChange = { onIntent(InputViewIntent.ValueChanged(it)) },
            placeholder = {
                Text(
                    text = state.placeholder,
                    style = LocalTypography
                        .current
                        .bodyMedium
                        .copy(color = AppColors.Content.OnNeutralLow)
                )
            },
            isError = state.isError,
            enabled = state.enabled,
            singleLine = true,
            visualTransformation = if (state.isPassword && !state.isPasswordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon ?: if (state.isPassword) {
                {
                    IconButton(onClick = { onIntent(InputViewIntent.TogglePasswordVisibility) }) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = if (state.isPasswordVisible) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = stringResource(R.string.toggle_password_visibility)
                        )
                    }
                }
            } else {
                null
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.Content.OnNeutralXxHigh,
                unfocusedBorderColor = AppColors.Content.OnNeutralXxHigh.copy(alpha = 0.8f),
                errorBorderColor = AppColors.Content.OnNeutralDanger
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = LocalShapes.current.medium
        )

        if (state.isError && state.errorText != null) {
            Text(
                text = state.errorText,
                color = AppColors.Content.OnNeutralDanger,
                style = LocalTypography.current.labelSmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(name = "InputView Error")
@Composable
fun InputViewPreview() {
    O2testTheme {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            InputView(
                modifier = Modifier.padding(16.dp),
                state = InputViewState(
                    value = "",
                    label = "Label",
                    placeholder = "Placeholder",
                    errorText = "",
                    isError = true,
                    isOptional = true,
                    isPassword = true,
                    isPasswordVisible = false
                ),
                onIntent = {}
            )
        }
    }
}
