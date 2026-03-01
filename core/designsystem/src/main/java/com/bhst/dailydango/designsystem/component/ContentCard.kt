package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.ContentState

@Composable
fun ContentCard(
    contentState: ContentState,
    speakerClick: (String) -> Unit = {},
    isOpenChanged: (ContentState) -> Unit = {},
    bookmarkClick: (ContentState) -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ContentCardTop(
                contentState = contentState,
                speakerClick = speakerClick,
                bookmarkClick = bookmarkClick
            )
            ContentCardMid(
                contentState = contentState,
                isOpenChanged = isOpenChanged
            )
            if (contentState.isOpen) {
                ContentCardBottom(
                    contentState = contentState,
                    speakerClick = speakerClick
                )
            }
        }
    }
}

@Composable
fun ContentCardBottom(
    contentState: ContentState,
    speakerClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextChip(
                text = contentState.partOfSpeech,
                containerColor = MaterialTheme.colorScheme.primary,
                labelColor = MaterialTheme.colorScheme.onPrimary,
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            )
            Text(
                text = contentState.titleToKorean,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = DailyDangoTheme.typography.light20
            )
        }
        if (contentState.tip.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextChip(
                    text = "TIP",
                    containerColor = MaterialTheme.colorScheme.primary,
                    labelColor = MaterialTheme.colorScheme.onPrimary,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                )
                Text(
                    text = contentState.tip,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Center,
                    style = DailyDangoTheme.typography.light20
                )
            }
        }
        if (contentState.exampleForJapanese1.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageCard(
                    painter = painterResource(R.drawable.speaker_24px),
                    contentDescription = "Speaker",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = { speakerClick(contentState.exampleForJapanese1) }
                        )
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = contentState.exampleForJapanese1,
                        style = DailyDangoTheme.typography.light20,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = contentState.explanationForKoreanSound1,
                        style = DailyDangoTheme.typography.light20,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = contentState.explanationForKorean1,
                        style = DailyDangoTheme.typography.light20,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
        if (contentState.exampleForJapanese2.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageCard(
                    painter = painterResource(R.drawable.speaker_24px),
                    contentDescription = "Speaker",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = { speakerClick(contentState.exampleForJapanese2) }
                        )
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = contentState.exampleForJapanese2,
                        style = DailyDangoTheme.typography.light20,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = contentState.explanationForKoreanSound2,
                        style = DailyDangoTheme.typography.light20,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = contentState.explanationForKorean2,
                        style = DailyDangoTheme.typography.light20,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}

@Composable
fun ContentCardMid(
    contentState: ContentState,
    isOpenChanged: (ContentState) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        ImageCard(
            painter = if (contentState.isOpen) {
                painterResource(R.drawable.keyboard_arrow_up_24px)
            } else {
                painterResource(R.drawable.keyboard_arrow_down_24px)
            },
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    onClick = { isOpenChanged(contentState.copy(isOpen = !contentState.isOpen)) }
                ),
            contentDescription = "Arrow",
        )
    }
}

@Composable
fun ContentCardTop(
    contentState: ContentState,
    speakerClick: (String) -> Unit = {},
    bookmarkClick: (ContentState) -> Unit = {}
) {
    if (contentState.titleHanja.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            ImageCard(
                painter = painterResource(R.drawable.speaker_24px),
                contentDescription = "Speaker",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = { speakerClick(contentState.japaneseTitle) }
                    )
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = contentState.titleHanja,
                    style = DailyDangoTheme.typography.bold20,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = contentState.japaneseTitle,
                    style = DailyDangoTheme.typography.light20,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = contentState.japaneseTitleOfSoundToKorea,
                    style = DailyDangoTheme.typography.light20,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            ImageCard(
                painter = if (contentState.isBookmark) {
                    painterResource(R.drawable.fill_star_24px)
                } else {
                    painterResource(R.drawable.star_24px)
                },
                contentDescription = "Speaker",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = {
                        bookmarkClick(
                            contentState.copy(
                                isBookmark = !contentState.isBookmark
                            )
                        )
                    })
            )
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            ImageCard(
                painter = painterResource(R.drawable.speaker_24px),
                contentDescription = "Speaker",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = { speakerClick(contentState.titleHanja) }
                    )
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = contentState.japaneseTitle,
                    style = DailyDangoTheme.typography.bold20,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = contentState.japaneseTitleOfSoundToKorea,
                    style = DailyDangoTheme.typography.light20,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            ImageCard(
                painter = if (contentState.isBookmark) {
                    painterResource(R.drawable.fill_star_24px)
                } else {
                    painterResource(R.drawable.star_24px)
                },
                contentDescription = "Speaker",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = {
                        bookmarkClick(
                            contentState.copy(
                                isBookmark = !contentState.isBookmark
                            )
                        )
                    })
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ContentCardPreview() {
    DailyDangoTheme {
        ContentCard(
            contentState = ContentState()
        )
    }
}