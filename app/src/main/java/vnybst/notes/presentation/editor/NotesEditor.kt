package vnybst.notes.presentation.editor

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vnybst.notes.R
import vnybst.notes.data.entities.Note
import vnybst.notes.data.utils.BackgroundColors
import vnybst.notes.data.utils.getColor
import vnybst.notes.presentation.NotesViewModel
import vnybst.notes.presentation.components.TintedTextField
import vnybst.notes.presentation.theme.customFontFamily
import java.util.*

@Composable
fun NotesEditor(
    navController: NavHostController,
    viewModel: NotesViewModel,
    noteId: String? = null
) {

    val notesEditor: MutableState<Note?> = remember {
        mutableStateOf(null)
    }

    val backgroundColors: MutableState<BackgroundColors?> = remember {
        mutableStateOf(null)
    }

    val openDialog = remember { mutableStateOf(false) }

    val openDeleteDialog = remember { mutableStateOf(false) }

    var noteTitleEditor by remember {
        mutableStateOf("")
    }

    var notesDescriptionEditor by remember {
        mutableStateOf("")
    }

    val selectedNotesLabel = viewModel.getSelectedNotesLabel().observeAsState().value

    val scroll = rememberScrollState(0)

    val coroutineScope = rememberCoroutineScope()

    //get a note from database
    LaunchedEffect(true) {
        coroutineScope.launch(Dispatchers.IO) {
            if (noteId != null) {
                notesEditor.value = viewModel.getNote(noteId.toLong())
                Log.e("NotesEditor", "${notesEditor.value}")
                notesEditor.value?.let {
                    noteTitleEditor = it.noteHeading
                    notesDescriptionEditor = it.noteDescription
                    coroutineScope.launch(Dispatchers.Main) {
                        viewModel.setSelectedBackgroundColor(it.backgroundColor)
                        viewModel.setNotesLabel(it.label)
                        backgroundColors.value = it.backgroundColor
                    }
                }
            }
        }
    }


    BackHandler {
        val newNote = Note(
            noteHeading = noteTitleEditor,
            noteDescription = notesDescriptionEditor,
            label = selectedNotesLabel ?: emptyList(),
            backgroundColor = backgroundColors.value,
            readOnly = false
        )
        if (noteTitleEditor.isNotEmpty() || notesDescriptionEditor.isNotEmpty()) {
            coroutineScope.launch {
                if (noteId == "0") {
                    Log.e("newNote", "$newNote")
                    viewModel.insertNote(
                        newNote.copy(createdAt = Date())
                    )
                } else {
                    val updatedNote = newNote.copy(
                        id = noteId?.toLong(),
                        createdAt = notesEditor.value?.createdAt
                    )
                    Log.e("updateNote", "$updatedNote")
                    viewModel.updateNote(
                        updatedNote
                    )
                }
            }
            //set background color to null and notes label to null, so that it don't cached pre-selected
            //data when come back to Editor screen
            viewModel.setSelectedBackgroundColor(null)
            viewModel.setNotesLabel(emptyList())
        }
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .background(
                viewModel
                    .getSelectedBackgroundColor()
                    .observeAsState().value?.getColor()
                    ?: MaterialTheme.colorScheme.surface
            )
    ) {

        EditorContent(
            modifier = Modifier.weight(0.9f),
            noteHeading = noteTitleEditor,
            backgroundColors = viewModel.getSelectedBackgroundColor().observeAsState().value,
            setNoteHeading = {
                noteTitleEditor = it
            },
            noteDescription = notesDescriptionEditor,
            setNoteDescription = { notesDescriptionEditor = it }
        )

        EditorBottomBar(
            onClickColorChooser = {
                openDialog.value = true
            }, onClickDelete = {
                openDeleteDialog.value = true
            }, onClickLabelView = {
                navController.navigate("labelEditor")
            },
            backgroundColors = viewModel.getSelectedBackgroundColor().observeAsState().value
        )

        // Color chooser dialog
        ColorChooserDialog(
            openDialog.value,
            showDialog = {
                openDialog.value = it
            },
            onClickDone = {
                backgroundColors.value = it
            },
            viewModel = viewModel
        )

        //Delete confirmation dialog
        DeleteDialog(
            show = openDeleteDialog.value,
            showDialog = {
                openDeleteDialog.value = it
            },
            onDeleteClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    if (noteId != "0") {
                        notesEditor.value?.let {
                            viewModel.deleteNote(it)
                            coroutineScope.launch(Dispatchers.Main) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun EditorContent(
    modifier: Modifier,
    noteHeading: String,
    backgroundColors: BackgroundColors?,
    setNoteHeading: (String) -> Unit,
    noteDescription: String,
    setNoteDescription: (String) -> Unit
) {
    TintedTextField(
        modifier = Modifier.fillMaxWidth(),
        textFieldValue = noteHeading,
        setTextFieldValue = {
            setNoteHeading(it)
        },
        placeholder = {
            Text(
                text = stringResource(R.string.add_notes_heading),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                fontFamily = customFontFamily,
                color = Color.Gray
            )
        },
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            fontFamily = customFontFamily,
            color = if (backgroundColors?.getColor() == null) Color.White else Color.Black
        ),
        backgroundColors = backgroundColors
    )
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
    )
    TintedTextField(
        modifier = modifier.fillMaxSize(),
        placeholder = {
            Text(
                stringResource(id = R.string.add_notes_description),
                fontSize = 14.sp,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        },
        textFieldValue = noteDescription,
        setTextFieldValue = {
            setNoteDescription(it)
        },
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = customFontFamily,
            color = if (backgroundColors?.getColor() == null) Color.White else Color.Black
        ),
        backgroundColors = backgroundColors
    )
}