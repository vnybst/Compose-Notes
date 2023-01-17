package vnybst.notes.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vnybst.notes.R
import vnybst.notes.presentation.theme.customFontFamily

@Preview
@Composable
fun Preview() {
    SearchView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView() {
    var searchValue by remember {
        mutableStateOf("")
    }
    TextField(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(5.dp),
        singleLine = true,
        value = searchValue, onValueChange = { searchValue = it },
        shape = RoundedCornerShape(20.dp),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        },
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_clear_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        searchValue = ""
                    }
            )
        },
        placeholder = {
            Text(
                stringResource(id = R.string.search_notes),
                fontSize = 14.sp,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xff616161)
            )
        },
        textStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = customFontFamily,
            color = Color.Black
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xffc2c2c2),
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}