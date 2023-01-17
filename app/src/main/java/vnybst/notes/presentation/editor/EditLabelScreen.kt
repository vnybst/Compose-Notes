package vnybst.notes.presentation.editor

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import vnybst.notes.R
import vnybst.notes.data.entities.NoteLabel
import vnybst.notes.presentation.NotesViewModel
import vnybst.notes.presentation.components.TintedIcon
import vnybst.notes.presentation.theme.customFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLabelScreen(
    navHostController: NavHostController,
    viewModel: NotesViewModel
) {

    val coroutineScope = rememberCoroutineScope()

    var labelValue by remember {
        mutableStateOf("")
    }

    val labelsList = viewModel.getAllNotesLabel().observeAsState().value

    val selectedNotesLabel = mutableListOf<String>()

    val getSelectedNotesLabel = viewModel.getSelectedNotesLabel().observeAsState().value

    if (getSelectedNotesLabel?.isNotEmpty() == true) {
        selectedNotesLabel.addAll(getSelectedNotesLabel)
    }

    BackHandler {
        viewModel.setNotesLabel(selectedNotesLabel)
        Log.e("SelectedLabel", "$selectedNotesLabel")
        navHostController.popBackStack()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = stringResource(id = R.string.add_notes_label),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(20.dp),
            fontFamily = customFontFamily
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            labelsList?.size?.let {
                items(it) { position ->
                    LabelView(
                        labelValue = labelsList[position].label,
                        checkChangeListener = { selectedValue ->
                            selectedNotesLabel.add(selectedValue)
                        }, shouldCheck = selectedNotesLabel.contains(labelsList[position].label)
                    )
                }
            }
        }

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = labelValue,
            placeholder = {
                Text(
                    stringResource(id = R.string.add_new_notes_label),
                    fontSize = 14.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            },
            onValueChange = {
                labelValue = it
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_send_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.clickable {
                        val notesLabel = NoteLabel(label = labelValue)
                        coroutineScope.launch {
                            viewModel.insertNoteLabel(notesLabel)
                        }
                        labelValue = ""
                    }
                )
            },
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontFamily = customFontFamily,
            ),
        )
    }
}

@Composable
fun LabelView(
    labelValue: String,
    shouldCheck: Boolean,
    checkChangeListener: (checkedValue: String) -> Unit
) {
    var onCheckChange by remember {
        mutableStateOf(shouldCheck)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 20.dp, bottom = 5.dp)
    ) {
        TintedIcon(
            icon = R.drawable.ic_outline_label_24,
            onClick = {},
            backgroundColors = null
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = labelValue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = customFontFamily,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(6.dp))
        Checkbox(
            checked = onCheckChange,
            onCheckedChange = {
                onCheckChange = it
                checkChangeListener(labelValue)
            },
        )
    }
}