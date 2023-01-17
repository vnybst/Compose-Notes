package vnybst.notes.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vnybst.notes.presentation.NotesViewModel
import vnybst.notes.presentation.editor.EditLabelScreen
import vnybst.notes.presentation.editor.NotesEditor
import vnybst.notes.presentation.notes.NotesList

@Composable
fun Home(
    viewModel: NotesViewModel
) {

    val notes = viewModel.getAllNotes().observeAsState().value

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "notes") {
        composable("notes") {
            NotesList(notes, navController)
        }
        composable("editor/{noteId}") {
            val noteId = it.arguments?.getString("noteId")
            NotesEditor(navController, viewModel, noteId)
        }
        composable("labelEditor") { EditLabelScreen(navController, viewModel) }
    }
}