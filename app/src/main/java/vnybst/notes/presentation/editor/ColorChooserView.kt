package vnybst.notes.presentation.editor

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import vnybst.notes.R
import vnybst.notes.data.utils.BackgroundColors
import vnybst.notes.data.utils.notesColors
import vnybst.notes.presentation.NotesViewModel
import vnybst.notes.presentation.theme.customFontFamily

@Composable
fun ColorChooserDialog(
    show: Boolean,
    showDialog: (show: Boolean) -> Unit,
    onClickDone: (backgroundColor: BackgroundColors?) -> Unit,
    viewModel: NotesViewModel
) {
    val data = notesColors()
    val selectedBackgroundColors: MutableState<BackgroundColors?> = remember {
        mutableStateOf(null)
    }

    selectedBackgroundColors.value =
        viewModel.getSelectedBackgroundColor().observeAsState().value

    if (show) {
        Dialog(
            onDismissRequest = { showDialog(false) },
            properties = DialogProperties(
                dismissOnClickOutside = false, dismissOnBackPress = false
            ),
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(20.dp)
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.choose_color),
                    fontWeight = FontWeight.Bold,
                    fontFamily = customFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 15.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(15.dp))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(80.dp), contentPadding = PaddingValues(8.dp)
                ) {
                    items(data.size) {
                        ColorChooserItem(
                            color = data[it].second,
                            backgroundColors = data[it].third,
                            onClickColor = { bgColor ->
                                viewModel.setSelectedBackgroundColor(bgColor)
                            },
                            isSelected = data[it].third == selectedBackgroundColors.value
                        )
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    TextButton(
                        onClick = { showDialog(false) }, modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            fontFamily = customFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    TextButton(
                        onClick = {
                            onClickDone(selectedBackgroundColors.value)
                            showDialog(false)
                        }, modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.done),
                            fontFamily = customFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColorChooserItem(
    color: Color,
    backgroundColors: BackgroundColors,
    isSelected: Boolean,
    onClickColor: (backgroundColors: BackgroundColors) -> Unit
) {
    Box(modifier = Modifier.padding(10.dp)) {
        Image(painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .clickable {
                    onClickColor(backgroundColors)
                }
                .size(50.dp)
                .clip(RoundedCornerShape(30.dp)))
        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_check_24),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}