package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.chapter.Chapter
import com.turtlekazu.furiganable.compose.m3.TextWithReading

@Composable
fun ChapterTabCard(
    chapter: Chapter = Chapter(),
    onClick: () -> Unit = {},
    circleColor: Color = MaterialTheme.colorScheme.primaryFixedDim,
    tagContainerColor: Color = MaterialTheme.colorScheme.secondary,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, // ⭐ 물결 효과 제거 핵심
                onClick = onClick
            ),
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = CircleShape,
                color = circleColor,
                shadowElevation = 4.dp,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            ) {
                AsyncImage(
                    model = chapter.imgUrl,
                    contentDescription = "Chapter_Avatar",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)

                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 중앙 텍스트 (수직 정렬을 위해 Column 사용)
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = chapter.title + stringResource(R.string.chapter),
                            style = DailyDangoTheme.typography.bold20,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        TextChip(
                            text = chapter.tag,
                            labelColor = MaterialTheme.colorScheme.surface,
                            containerColor = tagContainerColor,
                            style = DailyDangoTheme.typography.bold16,
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TextWithReading(
                                formattedText = chapter.subTitle,
                                furiganaFontSize = 10.sp,
                                style = DailyDangoTheme.typography.medium16,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = chapter.subTitleForKorean,
                                style = DailyDangoTheme.typography.medium14,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Go to chapter",
                            modifier = Modifier.size(36.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ChapterTabCardPreview() {
    DailyDangoTheme {
        ChapterTabCard(
            chapter = Chapter(
                title = "1",
                subTitle = "おはようございます",
                subTitleForKorean = "안녕하세요",
                tag = "초급"
            )
        )
    }
}