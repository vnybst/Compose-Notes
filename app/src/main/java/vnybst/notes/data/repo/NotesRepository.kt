package vnybst.notes.data.repo

import androidx.lifecycle.LiveData
import vnybst.notes.data.dao.NotesDao
import vnybst.notes.data.entities.Note
import vnybst.notes.data.entities.NoteLabel

class NotesRepository(private val notesDao: NotesDao) {

    suspend fun insertNote(note: Note): Long {
        return notesDao.insertNotes(note)
    }

    suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return notesDao.getAllNotes()
    }

    fun getNote(noteId: Long): Note {
        return notesDao.getNote(noteId)
    }

    suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    suspend fun insertLabel(notesLabel: NoteLabel): Long {
        return notesDao.insertLabel(notesLabel)
    }

    suspend fun updateNoteLabel(notesLabel: NoteLabel) {
        notesDao.updateNoteLabel(notesLabel)
    }

    fun getAllNotesLabel(): LiveData<List<NoteLabel>> {
        return notesDao.getAllNotesLabel()
    }

    suspend fun deleteNoteLabel(noteLabel: NoteLabel) {
        notesDao.deleteNoteLabel(noteLabel)
    }

}