package mc.ui.components

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mc.model.Repository
import kotlin.math.ceil

@Composable
fun HorizontalSpacer(spacing: Int) {
    Spacer(modifier = Modifier.width(spacing.dp))
}

@Composable
fun VerticalSpacer(spacing: Int) {
    Spacer(modifier = Modifier.width(spacing.dp))
}

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    fontSize: Int,
    fontStyle: FontStyle = FontStyle.Normal,
    textDecoration: TextDecoration = TextDecoration.None,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontFamily = FontFamily(Repository.currentFont.value),
        fontSize = fontSize.sp,
        fontStyle = fontStyle,
        textDecoration = textDecoration,
        lineHeight = (ceil(fontSize*1.3f).toInt()).sp,
    )
}