package vnybst.notes.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.withContext
import vnybst.notes.data.database.NotesDatabase
import vnybst.notes.data.entities.Note
import vnybst.notes.data.entities.NoteLabel
import vnybst.notes.data.repo.NotesRepository
import vnybst.notes.data.utils.BackgroundColors

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    private val tag = NotesViewModel::class.java.simpleName

    private val selectedNotesLabel = MutableLiveData<List<String>>()
    private val selectedBackgroundColor = MutableLiveData<BackgroundColors>()

    suspend fun insertNote(note: Note) {
        withContext(viewModelScope.coroutineContext) {
            val insert = notesRepository.insertNote(note)
            Log.d(tag, "insertNote: $insert")
        }
    }

    fun getNote(noteId: Long): Note {
        return notesRepository.getNote(noteId)
    }

    suspend fun updateNote(note: Note) {
        withContext(viewModelScope.coroutineContext) {
            notesRepository.updateNote(note)
        }
    }

    suspend fun deleteNote(note: Note) {
        withContext(viewModelScope.coroutineContext) {
            notesRepository.deleteNote(note)
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return notesRepository.getAllNotes()
    }

    suspend fun insertNoteLabel(noteLabel: NoteLabel) {
        withContext(viewModelScope.coroutineContext) {
            val insert = notesRepository.insertLabel(noteLabel)
            Log.d(tag, "insertNoteLabel: $insert")
        }
    }

    suspend fun updateNoteLabel(noteLabel: NoteLabel) {
        withContext(viewModelScope.coroutineContext) {
            notesRepository.updateNoteLabel(noteLabel)
        }
    }

    suspend fun deleteNoteLabel(noteLabel: NoteLabel) {
        withContext(viewModelScope.coroutineContext) {
            notesRepository.deleteNoteLabel(noteLabel)
        }
    }

    fun getAllNotesLabel(): LiveData<List<NoteLabel>> {
        return notesRepository.getAllNotesLabel()
    }

    fun setNotesLabel(notesLabel: List<String>) {
        selectedNotesLabel.postValue(notesLabel)
    }

    fun getSelectedNotesLabel(): LiveData<List<String>> {
        return selectedNotesLabel
    }

    fun setSelectedBackgroundColor(backgroundColors: BackgroundColors?) {
        selectedBackgroundColor.value = backgroundColors
    }

    fun getSelectedBackgroundColor(): LiveData<BackgroundColors> {
        return selectedBackgroundColor
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val notesRepository = NotesRepository(
                    NotesDatabase.getDatabase(this[APPLICATION_KEY] as Application).notesDao()
                )
                NotesViewModel(
                    notesRepository = notesRepository,
                )
            }
        }
    }

}