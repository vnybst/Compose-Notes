package vnybst.notes.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import vnybst.notes.data.utils.BackgroundColors
import java.util.*

@Entity(tableName = "notes_list")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "note_heading")
    var noteHeading: String,
    @ColumnInfo(name = "note_description")
    var noteDescription: String,
    @ColumnInfo(name = "background_color")
    var backgroundColor: BackgroundColors? = null,
    @ColumnInfo(name = "read_only")
    var readOnly: Boolean,
    @ColumnInfo(name = "note_label")
    var label: List<String>,
    @ColumnInfo(name = "created_at")
    var createdAt: Date? = null,
    @ColumnInfo(name = "last_modified")
    var lastModified: Date = Date()
) : Parcelable