package vnybst.notes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import vnybst.notes.data.entities.Note
import vnybst.notes.data.entities.NoteLabel

@Dao
interface NotesDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertNotes(note: Note): Long

    @Query("select * from notes_list")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("select * from notes_list where id=:noteId")
    fun getNote(noteId: Long): Note

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)


    //For notes label
    @Insert(onConflict = REPLACE)
    suspend fun insertLabel(note: NoteLabel): Long

    @Query("select * from note_label")
    fun getAllNotesLabel(): LiveData<List<NoteLabel>>

    @Update
    suspend fun updateNoteLabel(note: NoteLabel)

    @Delete
    suspend fun deleteNoteLabel(note: NoteLabel)

}