package vnybst.notes.data.utils

import androidx.compose.ui.graphics.Color

fun BackgroundColors.getColor(): Color {
    return notesColors().first {
        it.third == this
    }.first
}