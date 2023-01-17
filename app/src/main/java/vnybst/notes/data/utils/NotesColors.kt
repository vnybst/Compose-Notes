package vnybst.notes.data.utils

import androidx.compose.ui.graphics.Color

fun notesColors(): List<Triple<Color, Color, BackgroundColors>> {
    return mutableListOf(
        Triple(Color(0xffffcdd2), Color(0xfff44336), BackgroundColors.RED), // red_100
        Triple(Color(0xfff8bbd0), Color(0xffe91e63), BackgroundColors.PINK), //pink_100
        Triple(Color(0xffe1bee7), Color(0xff9c27b0), BackgroundColors.PURPLE), //purple_100
        Triple(Color(0xffc5cae9), Color(0xff3f51b5), BackgroundColors.INDIGO), //indigo_100
        Triple(Color(0xffbbdefb), Color(0xff2196f3), BackgroundColors.BLUE), //blue_100
        Triple(Color(0xffb2ebf2), Color(0xff00bcd4), BackgroundColors.CYAN), //cyan_100
        Triple(Color(0xffb2dfdb), Color(0xff009688), BackgroundColors.TEAL), //teal_100
        Triple(Color(0xffc8e6c9), Color(0xff4caf50), BackgroundColors.GREEN),// green_100
        Triple(Color(0xfff0f4c3), Color(0xffcddc39), BackgroundColors.LIME), // lime_100
        Triple(Color(0xfffff9c4), Color(0xffffeb3b), BackgroundColors.YELLOW), // yellow_100
        Triple(Color(0xffffe0b2), Color(0xffff9800), BackgroundColors.ORANGE), //orange_100
        Triple(Color(0xffd7ccc8), Color(0xff795548), BackgroundColors.BROWN) //brown_100
    )
}