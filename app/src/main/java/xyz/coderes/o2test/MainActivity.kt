package xyz.coderes.o2test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.coderes.o2test.ui.component.password_view.PasswordInput
import xyz.coderes.o2test.ui.component.password_view.PasswordInputIntent
import xyz.coderes.o2test.ui.component.password_view.PasswordInputState
import xyz.coderes.o2test.ui.component.password_view.PasswordViewState
import xyz.coderes.o2test.ui.component.password_view.validatePassword
import xyz.coderes.o2test.ui.theme.O2testTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            O2testTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JustScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun JustScreen(modifier: Modifier = Modifier) {

    //This part can be easily moved to the ViewModel and integrated into MVI architecture

    val label = stringResource(R.string.password)
    val placeholder = stringResource(R.string.enter_password)
    var inputState by remember {
        mutableStateOf(
            PasswordViewState(
                value = "",
                label = label,
                placeholder = placeholder,
            )
        )
    }
    val isError = remember{ mutableStateOf(true)}

    val validation =
        remember(inputState.value) { derivedStateOf {
            val cur = validatePassword(inputState.value)
            isError.value = cur.any { !it.isValid }
            cur
        } }


    val state by remember(inputState, validation.value) {
        derivedStateOf {
            PasswordInputState(inputState, validation.value)
        }
    }

    Box(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        PasswordInput(
            modifier = Modifier.padding(8.dp),
            state = state,
            onIntent = {
                inputState = when (it) {
                    is PasswordInputIntent.OnPasswordChanged -> inputState.copy(
                        value = it.value,
                    )

                    is PasswordInputIntent.TogglePasswordVisibility -> inputState.copy(
                        isPasswordVisible = !inputState.isPasswordVisible
                    )
                }
            }
        )
    }
}