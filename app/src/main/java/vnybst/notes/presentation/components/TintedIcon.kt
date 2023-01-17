package vnybst.notes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import vnybst.notes.data.utils.BackgroundColors
import vnybst.notes.data.utils.getColor

@Composable
fun TintedIcon(
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColors: BackgroundColors?
) {
    Image(painter = painterResource(id = icon),
        contentDescription = null,
        colorFilter = ColorFilter.tint(
            if (backgroundColors?.getColor() == null) tint else Color.Black
        ),
        modifier = modifier.clickable {
            onClick()
        })
}