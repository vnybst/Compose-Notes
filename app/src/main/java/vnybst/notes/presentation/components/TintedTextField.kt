package vnybst.notes.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import vnybst.notes.data.utils.BackgroundColors
import vnybst.notes.data.utils.getColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TintedTextField(
    placeholder: @Composable () -> Unit,
    textFieldValue: String,
    setTextFieldValue: (value: String) -> Unit,
    backgroundColors: BackgroundColors?,
    textStyle: TextStyle,
    modifier: Modifier
) {
    TextField(
        modifier = modifier,
        value = textFieldValue,
        onValueChange = {
            setTextFieldValue(it)
        },
        placeholder = placeholder,
        textStyle = textStyle,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = backgroundColors?.getColor() ?: MaterialTheme.colorScheme.surface,
            cursorColor = if (backgroundColors?.getColor() == null) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}