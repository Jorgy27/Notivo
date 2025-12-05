package com.notivo.common.view.composables.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notivo.common.R
import com.notivo.common.utils.FontUtil
import com.notivo.common.view.model.NoteUiState
import com.notivo.common.view.model.UiNote

@Composable
fun NotePreview(
    noteUiState: NoteUiState
) {

    Box(
        modifier = Modifier
            .width(120.dp)
            .height(86.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Text(
                text = "Name",
                color = Color.Black,
                fontFamily = FontUtil.fontBold,
                fontSize = 8.sp,
            )

            when (noteUiState.content) {
                is UiNote.CheckItem -> TODO()
                is UiNote.SubNote -> TODO()
                is UiNote.Text -> {
                    val body = (noteUiState.content as UiNote.Text).text
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 3.dp)
                            .background(Color.LightGray)
                            /*.drawWithCache {
                                val brush = Brush.horizontalGradient(
                                    1.0f to Color.Transparent
                                )

                                onDrawWithContent {
                                    drawContent()
                                    drawRect(brush, blendMode = BlendMode.SrcIn)
                                }
                            }*/
                    ) {
                        Text(
                            text = body,
                            color = Color.Black,
                            fontSize = 5.sp,
                            fontFamily = FontUtil.fontRegular,
                        )
                    }
                }
            }
        }

        Image(
            painter = painterResource(R.drawable.bookmark_filled_cropped),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.TopEnd)
                .padding(end = 10.dp)
                .offset(y = (-7.5).dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotePreviewPreview() {
    val title = "Preview Title"
    val previewText = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.
""".trimIndent()

    val note = NoteUiState(title = title, content = UiNote.Text(previewText))

    NotePreview(note)
}