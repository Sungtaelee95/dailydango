package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.util.filterHanja

@Composable
fun SearchContentCard(
    contentState: ContentState,
    speakerClick: (String?) -> Unit = {},
    updateContent: (ContentState) -> Unit = {},
    favoriteClick: (ContentState) -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit = {}
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
                    color = MaterialTheme.colorScheme.surface
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            SearchContentCardTop(
                contentState = contentState,
                speakerClick = speakerClick,
                bookmarkClick = favoriteClick,
                navigateToHanjaDetail = navigateToHanjaDetail
            )
            SearchContentCardMid(
                contentState = contentState,
                isOpenChanged = updateContent
            )
            if (contentState.isOpen) {
                SearchContentCardBottom(
                    contentState = contentState,
                    speakerClick = speakerClick,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
        }
    }
}

@Composable
fun SearchContentCardBottom(
    contentState: ContentState,
    speakerClick: (String?) -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryFixedDim)
            .padding(20.dp)
    ) {
        if (contentState.partOfSpeech.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextChip(
                    text = contentState.partOfSpeech,
                    containerColor = MaterialTheme.colorScheme.primaryFixedDim,
                    labelColor = MaterialTheme.colorScheme.primaryFixed,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primaryFixed,
                    ),
                    modifier = Modifier
                        .width(72.dp),
                    style = DailyDangoTheme.typography.medium14
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = contentState.titleToKorean,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Start,
                    style = DailyDangoTheme.typography.medium14,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        if (contentState.tip.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                TextChip(
                    text = "TIP",
                    containerColor = MaterialTheme.colorScheme.primaryFixedDim,
                    labelColor = MaterialTheme.colorScheme.primaryFixed,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primaryFixed,
                    ),
                    modifier = Modifier.width(72.dp),
                    style = DailyDangoTheme.typography.medium14
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = contentState.tip,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Start,
                    style = DailyDangoTheme.typography.medium14,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        if (contentState.exampleForJapanese1.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                ImageCard(
                    painter = painterResource(R.drawable.speaker_24px),
                    contentDescription = "Speaker",
                    modifier = Modifier
                        .size(20.dp),
                    onClick = { speakerClick(contentState.exampleForJapanese1) },
                    filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = contentState.exampleForJapanese1,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese1.filterHanja().isNotEmpty()) {
                                    navigateToHanjaDetail(contentState.exampleForJapanese1.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound1,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean1,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
        if (contentState.exampleForJapanese2.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                ImageCard(
                    painter = painterResource(R.drawable.speaker_24px),
                    contentDescription = "Speaker",
                    modifier = Modifier
                        .size(20.dp),
                    onClick = { speakerClick(contentState.exampleForJapanese2) },
                    filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = contentState.exampleForJapanese2,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese2.filterHanja().isNotEmpty()) {
                                    navigateToHanjaDetail(contentState.exampleForJapanese2.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound2,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean2,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
        if (contentState.exampleForJapanese3.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                ImageCard(
                    painter = painterResource(R.drawable.speaker_24px),
                    contentDescription = "Speaker",
                    modifier = Modifier
                        .size(20.dp),
                    onClick = { speakerClick(contentState.exampleForJapanese3) },
                    filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = contentState.exampleForJapanese3,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese3.filterHanja().isNotEmpty()) {
                                    navigateToHanjaDetail(contentState.exampleForJapanese3.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound3,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean3,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (contentState.exampleForJapanese4.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                ImageCard(
                    painter = painterResource(R.drawable.speaker_24px),
                    contentDescription = "Speaker",
                    modifier = Modifier
                        .size(20.dp),
                    onClick = { speakerClick(contentState.exampleForJapanese4) },
                    filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = contentState.exampleForJapanese4,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese4.filterHanja().isNotEmpty()) {
                                    navigateToHanjaDetail(contentState.exampleForJapanese4.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound4,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean4,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun SearchContentCardMid(
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
                .size(28.dp),
            contentDescription = "Arrow",
            onClick = { isOpenChanged(contentState.copy(isOpen = !contentState.isOpen)) },
            filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
        )
    }
}

@Composable
fun SearchContentCardTop(
    contentState: ContentState,
    speakerClick: (String?) -> Unit = {},
    bookmarkClick: (ContentState) -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit = {}
) {
    if (contentState.titleHanja.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.Top
        ) {
            ImageCard(
                painter = painterResource(R.drawable.speaker_24px),
                contentDescription = "Speaker",
                modifier = Modifier
                    .size(20.dp),
                onClick = { speakerClick(contentState.japaneseTitle) },
                filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = contentState.titleHanja,
                    style = DailyDangoTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.primaryFixed,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            if (contentState.titleHanja.filterHanja().isNotEmpty()) {
                                navigateToHanjaDetail(contentState.titleHanja.filterHanja())
                            }
                        }
                    )
                )
                Text(
                    text = contentState.japaneseTitle,
                    style = DailyDangoTheme.typography.medium14,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            if (contentState.japaneseTitle.filterHanja().isNotEmpty()) {
                                navigateToHanjaDetail(contentState.japaneseTitle.filterHanja())
                            }
                        }
                    )
                )
                Text(
                    text = contentState.japaneseTitleOfSoundToKorea,
                    style = DailyDangoTheme.typography.medium14,
                    color = MaterialTheme.colorScheme.onBackground
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
                    .size(20.dp),
                onClick = { bookmarkClick(contentState.copy(isBookmark = !contentState.isBookmark)) },
                filter = if (!contentState.isBookmark) ColorFilter.tint(MaterialTheme.colorScheme.onBackground) else null
            )
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.Top
        ) {
            ImageCard(
                painter = painterResource(R.drawable.speaker_24px),
                contentDescription = "Speaker",
                modifier = Modifier
                    .size(20.dp),
                onClick = { speakerClick(contentState.japaneseTitle) },
                filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = contentState.japaneseTitle,
                    style = DailyDangoTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.primaryFixed,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            if (contentState.japaneseTitle.filterHanja().isNotEmpty()) {
                                navigateToHanjaDetail(contentState.japaneseTitle.filterHanja())
                            }
                        }
                    )
                )
                Text(
                    text = contentState.japaneseTitleOfSoundToKorea,
                    style = DailyDangoTheme.typography.medium14,
                    color = MaterialTheme.colorScheme.onBackground
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
                    .size(20.dp),
                onClick = { bookmarkClick(contentState.copy(isBookmark = !contentState.isBookmark)) },
                filter = if (!contentState.isBookmark) ColorFilter.tint(MaterialTheme.colorScheme.onBackground) else null
            )
        }
    }
}


