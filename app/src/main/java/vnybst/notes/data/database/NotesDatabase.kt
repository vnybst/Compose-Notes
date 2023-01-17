package vnybst.notes.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vnybst.notes.data.dao.NotesDao
import vnybst.notes.data.entities.Note
import vnybst.notes.data.entities.NoteLabel
import vnybst.notes.data.utils.Converter

/**
 * Created by Vinay Singh Bisht on 07-Jan-22.
 */

@Database(
    entities = [Note::class, NoteLabel::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 3, to = 4)
    ]
)
@TypeConverters(Converter::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {

        @Volatile
        private var dbInstance: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {

            val tempInstance = dbInstance

            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_db"
                ).build()
                dbInstance = instance
                return instance
            }
        }

    }

}