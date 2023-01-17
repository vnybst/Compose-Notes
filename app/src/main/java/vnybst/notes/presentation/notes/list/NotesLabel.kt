package vnybst.notes.presentation.notes.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vnybst.notes.presentation.theme.Grey800
import vnybst.notes.presentation.theme.customFontFamily

@Composable
fun NotesLabelView(
    modifier: Modifier,
    labelList: List<String>
) {
    LazyRow(modifier = modifier) {
        items(labelList.size) {
            NotesLabelItemRow(labelList[it])
        }
    }
}

@Composable
fun NotesLabelItemRow(
    label: String
) {
    Row {
        Text(
            text = label,
            modifier = Modifier
                .border(
                    border = BorderStroke(0.5.dp, Grey800),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(vertical = 2.dp, horizontal = 10.dp),
            fontSize = 12.sp,
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Normal,
            color = Grey800
        )
        Spacer(modifier = Modifier.size(8.dp))
    }
}