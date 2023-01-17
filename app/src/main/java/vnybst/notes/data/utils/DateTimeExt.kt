package vnybst.notes.data.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Vinay Singh Bisht on 6/12/2021.
 */

fun Date.formatDate(): String? {
    return try {
        val sdf = SimpleDateFormat("yyyy-MMM-dd-EEEE", Locale.getDefault())
        sdf.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.formatToDDMMMYYYY(): String? {
    return try {
        val sdf = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        sdf.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.formatToDateTime(): String? {
    return try {
        val sdf = SimpleDateFormat("dd-MMM-yyyy hh:mm aa", Locale.getDefault())
        sdf.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.formatToTime(): String? {
    return try {
        val sdf = SimpleDateFormat("hh:mm aa", Locale.getDefault())
        sdf.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.formatHHmmss(): String? {
    return try {
        val sdf = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
        sdf.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.formatDateInt(): String? {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.toDay(): String? {
    return try {
        val outFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        outFormat.format(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.onlyDate(): String? {
    return try {
        val outFormat = SimpleDateFormat("dd", Locale.getDefault())
        outFormat.format(this)
    } catch (e: Exception) {
        null
    }
}


fun Long.toDateTime(): String {
    val date = Date(this)
    val sdf = SimpleDateFormat("dd-MMM-yyyy hh:mm:ss", Locale.getDefault())
    return sdf.format(date)
}

fun Long.toDateTimeInt(): String {
    val date = Date(this)
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return sdf.format(date)
}

fun Long.toDateTimeIntYYYYmmDD(): String {
    val date = Date(this)
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(date)
}

fun Long.toDate(): Date {
    return Date(this)
}

fun Long.minusDay(days: Int): Long {
    return this.minus(24 * 60 * 60 * 1000 * days)
}

fun Long.addDay(days: Int): Long {
    return this.plus(24 * 60 * 60 * 1000 * days)
}

fun Long.toHMSFormat(): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this)
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    return (String.format("%02d", minutes) + " : " +
            String.format("%02d", seconds))
}

fun String.getDateWithServerTimeStamp(): Date? {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun String.convertStringToDate(): Date? {
    val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getDefault()  // IMP !!!
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}
