package ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Cell


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CrosswordInputCell(
    cell: Cell,
    textFieldState: MutableState<String>,
    focusRequester: FocusRequester
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    var borderColor by remember { mutableStateOf(Black) }

    Box(
        modifier = Modifier
            .size(32.dp)
            .border(width = 1.dp, color = borderColor)
            .background(color = Color(0xFFf0f0f0))
            .bringIntoViewRequester(bringIntoViewRequester),
    ) {
        if (cell.colNum == cell.wordPair?.first?.startCol &&
            cell.rowNum == cell.wordPair?.first?.startRow) {
            SmallTextNumberInCorner(cell.wordPair?.first?.number, Modifier.align(Alignment.TopStart))
        }
        if (cell.wordPair?.second != null && cell.colNum == cell.wordPair!!.second!!.startCol &&
            cell.rowNum == cell.wordPair!!.second!!.startRow) {
            SmallTextNumberInCorner(cell.wordPair?.second?.number, Modifier.align(Alignment.TopStart))
        }

        Text(
            text = textFieldState.value,
            modifier = Modifier
                .align(Alignment.Center)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    borderColor = if (it.isFocused) Green else Black
                    if (it.isFocused) {
                        // Прокрутка, если элемент в фокусе
                        CoroutineScope(Dispatchers.Main).launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }
                .focusable(enabled = true),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        )
    }
}


@Composable
fun CrosswordNotAvailableCell() {
    Box(
        modifier = Modifier
            .size(32.dp)
            .border(width = 1.dp, color = Color.White)
            .background(color = Color.White)
    )
}