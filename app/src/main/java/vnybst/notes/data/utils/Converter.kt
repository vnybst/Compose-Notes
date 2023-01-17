package vnybst.notes.data.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.Date

class Converter {

    @TypeConverter
    fun labelJsonConverter(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    fun labelJsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromDate(date: Date): Long = date.time

    @TypeConverter
    fun toDate(time: Long): Date = Date(time)

}