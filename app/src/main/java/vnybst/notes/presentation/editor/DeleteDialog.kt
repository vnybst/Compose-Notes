package vnybst.notes.presentation.editor

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import vnybst.notes.R
import vnybst.notes.presentation.theme.customFontFamily

@Composable
fun DeleteDialog(
    show: Boolean,
    showDialog: (show: Boolean) -> Unit,
    onDeleteClick: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                showDialog(false)
            },
            text = {
                Text(
                    text = stringResource(id = R.string.delete_confirmation),
                    fontWeight = FontWeight.Normal,
                    fontFamily = customFontFamily
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteClick()
                        showDialog(false)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.delete),
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog(false) }
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        )
    }
}