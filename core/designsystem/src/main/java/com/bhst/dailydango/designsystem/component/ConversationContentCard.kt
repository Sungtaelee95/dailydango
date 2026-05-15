package com.bhst.dailydango.designsystem.component

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.conversation.ConversationContentState
import com.bhst.dailydango.util.filterHanja
import com.turtlekazu.furiganable.compose.m3.TextWithReading

@Composable
fun ConversationContentCard(
    contentState: ConversationContentState,
    allSpeakClick: (List<Uri>) -> Unit = {},
    speakerClick: (Uri?) -> Unit = {},
    updateContent: (ConversationContentState) -> Unit = {},
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
            ConversationContentCardTop(
                contentState = contentState,
                updateContent = updateContent,
                allSpeakClick = allSpeakClick,
            )
            if (contentState.isOpen) {
                ConversationContentCardBottom(
                    contentState = contentState,
                    speakerClick = speakerClick,
                    hanjaClick = navigateToHanjaDetail
                )
            }
        }
    }
}

@Composable
fun ConversationContentCardBottom(
    contentState: ConversationContentState,
    speakerClick: (Uri?) -> Unit = {},
    hanjaClick: (List<String>) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceTint)
            .padding(20.dp),
    ) {
        TextChip(
            text = stringResource(R.string.conversation_history),
            containerColor = MaterialTheme.colorScheme.surfaceTint,
            labelColor = MaterialTheme.colorScheme.onPrimaryFixedVariant,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimaryFixedVariant,
            ),
            modifier = Modifier
                .wrapContentWidth(),
            style = DailyDangoTheme.typography.medium14
        )

        if (contentState.exampleForJapanese1.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri1 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri1) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese1,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese1.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese1.filterHanja())
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
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri2 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri2) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese2,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese2.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese2.filterHanja())
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
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri3 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri3) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese3,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese3.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese3.filterHanja())
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
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri4 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri4) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese4,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese4.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese4.filterHanja())
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

        if (contentState.exampleForJapanese5.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri5 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri5) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese5,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese5.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese5.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound5,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean5,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (contentState.exampleForJapanese6.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri6 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri6) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese6,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese6.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese6.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound6,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean6,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (contentState.exampleForJapanese7.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri7 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri7) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese7,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese7.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese7.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound7,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean7,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (contentState.exampleForJapanese8.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri8 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri8) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese8,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese8.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese8.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound8,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean8,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (contentState.exampleForJapanese9.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri9 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri9) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese9,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese9.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese9.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound9,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean9,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (contentState.exampleForJapanese10.isNotEmpty()) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri10 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri10) }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextWithReading(
                        formattedText = contentState.exampleForJapanese10,
                        furiganaFontSize = 9.sp,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (contentState.exampleForJapanese10.filterHanja().isNotEmpty()) {
                                    hanjaClick(contentState.exampleForJapanese10.filterHanja())
                                }
                            }
                        )
                    )
                    Text(
                        text = contentState.explanationForKoreanSound10,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = contentState.explanationForKorean10,
                        style = DailyDangoTheme.typography.medium14,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        if (contentState.wordTips.isNotEmpty()) {
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextChip(
                    text = stringResource(R.string.conversation_word),
                    containerColor = MaterialTheme.colorScheme.surfaceTint,
                    labelColor = MaterialTheme.colorScheme.onPrimaryFixedVariant,
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimaryFixedVariant,
                    ),
                    modifier = Modifier
                        .wrapContentWidth(),
                    style = DailyDangoTheme.typography.medium14
                )
                Spacer(modifier = Modifier.width(12.dp))
                TextWithReading(
                    formattedText = contentState.wordTips,
                    style = DailyDangoTheme.typography.medium14,
                    furiganaFontSize = 9.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            if (contentState.wordTips.filterHanja().isNotEmpty()) {
                                hanjaClick(contentState.wordTips.filterHanja())
                            }
                        }
                    )
                )
            }
        }
    }
}


@Composable
fun ConversationContentCardTop(
    contentState: ConversationContentState,
    updateContent: (ConversationContentState) -> Unit = {},
    allSpeakClick: (List<Uri>) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 16.dp) // 상단과 하단 패딩 확보
    ) {
        // 상단 좌측: 스피커 아이콘
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                SpeakerAnimatedIcon(
                    visible = contentState.isAllLoading,
                    onClick = { allSpeakClick(contentState.contentUri.getContentUriList()) }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 중앙: 타이틀 이미지 (좌우 여백을 주어 꽉 차지 않게 만듭니다)
        SubcomposeAsyncImage(
            model = contentState.titleImageUrl,
            contentDescription = "Title Image",
            loading = {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            },
            error = {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                    Log.d("lstlst", "gif 오류")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp) // 사진처럼 안쪽으로 들어오게 설정
                .wrapContentHeight()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 하단 우측: 펼치기/접기 화살표
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            ImageCard(
                painter = if (contentState.isOpen) {
                    painterResource(R.drawable.keyboard_arrow_up_24px)
                } else {
                    painterResource(R.drawable.keyboard_arrow_down_24px)
                },
                modifier = Modifier.size(20.dp),
                contentDescription = "Arrow",
                onClick = { updateContent(contentState.copy(isOpen = !contentState.isOpen)) },
                filter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ConversationContentCardPreview() {
    DailyDangoTheme {
        ConversationContentCard(
            contentState = ConversationContentState(
                isOpen = true,
            )
        )
    }
}