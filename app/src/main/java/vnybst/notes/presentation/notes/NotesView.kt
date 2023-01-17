package vnybst.notes.presentation.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import vnybst.notes.R
import vnybst.notes.data.entities.Note
import vnybst.notes.presentation.NotesViewModel
import vnybst.notes.presentation.notes.list.NotesItem
import vnybst.notes.presentation.search.SearchView
import vnybst.notes.presentation.theme.customFontFamily

@Composable
fun NotesList(
    notesList: List<Note>?, navController: NavHostController
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = stringResource(id = R.string.notes),
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier.padding(20.dp),
                fontFamily = customFontFamily
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                item {
                    Column {
                        SearchView()
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                notesList?.let { notes ->
                    items(notes.size) {
                        NotesItem(notes[it], onClickNote = { note ->
                            navController.navigate(
                                "editor/{noteId}".replace(
                                    "{noteId}", "${note.id}"
                                )
                            )
                        })
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {
                navController.navigate("editor/{noteId}".replace("{noteId}", "0"))
            }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_outline_add_24),
                contentDescription = null
            )
        }
    }
}