package vnybst.notes.presentation.editor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vnybst.notes.R
import vnybst.notes.data.utils.BackgroundColors
import vnybst.notes.presentation.components.TintedIcon
import vnybst.notes.presentation.theme.customFontFamily

@Composable
fun EditorBottomBar(
    onClickLabelView: () -> Unit,
    onClickColorChooser: () -> Unit,
    onClickDelete: () -> Unit,
    backgroundColors: BackgroundColors?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TintedIcon(
            icon = R.drawable.ic_outline_color_lens_24,
            onClick = onClickColorChooser,
            backgroundColors = backgroundColors
        )
        Spacer(modifier = Modifier.size(10.dp))
        TintedIcon(
            icon = R.drawable.ic_outline_label_24,
            onClick = onClickLabelView,
            backgroundColors = backgroundColors
        )
        Spacer(modifier = Modifier.size(10.dp))
        TintedIcon(
            icon = R.drawable.ic_outline_delete_24,
            onClick = onClickDelete,
            backgroundColors = backgroundColors
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(id = R.string.last_modified),
            fontWeight = FontWeight.Light,
            fontFamily = customFontFamily,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier
                .weight(0.3f)
                .wrapContentSize(Alignment.CenterEnd)
                .padding(end = 10.dp)
        )
    }
}