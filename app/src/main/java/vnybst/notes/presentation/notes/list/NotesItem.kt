package vnybst.notes.presentation.notes.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vnybst.notes.data.entities.Note
import vnybst.notes.data.utils.formatToDDMMMYYYY
import vnybst.notes.data.utils.getColor
import vnybst.notes.presentation.theme.*

@Composable
fun NotesItem(
    note: Note, onClickNote: (note: Note) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable {
                onClickNote(note)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            note.backgroundColor?.getColor() ?: PurpleGrey80
        )
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .wrapContentHeight()
        ) {
            Text(
                text = note.noteHeading,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = note.noteDescription,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Normal,
                color = Grey800,
                fontSize = 14.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                NotesLabelView(
                    modifier = Modifier.weight(0.8f), note.label
                )
                Text(
                    text = note.createdAt?.formatToDDMMMYYYY().toString(),
                    fontSize = 12.sp,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Light,
                    color = Grey800
                )
            }
        }
    }
}
