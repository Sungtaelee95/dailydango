package com.bhst.dailydango.designsystem.component

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.bhst.dailydango.app.core.designsystem.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.util.filterHanja
import com.turtlekazu.furiganable.compose.m3.TextWithReading

@Composable
fun FavoriteContentCard(
    contentState: ContentState,
    speakerClick: (Uri?) -> Unit = {},
    updateContent: (ContentState) -> Unit = {},
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
            FavoriteContentCardTop(
                contentState = contentState,
                speakerClick = speakerClick,
                bookmarkClick = updateContent,
                navigateToHanjaDetail = navigateToHanjaDetail
            )
            FavoriteContentCardMid(
                contentState = contentState,
                isOpenChanged = updateContent
            )
            if (contentState.isOpen) {
                FavoriteContentCardBottom(
                    contentState = contentState,
                    speakerClick = speakerClick,
                    navigateToHanjaDetail = navigateToHanjaDetail
                )
            }
        }
    }
}

@Composable
fun FavoriteContentCardBottom(
    contentState: ContentState,
    speakerClick: (Uri?) -> Unit = {},
    navigateToHanjaDetail: (List<String>) -> Unit
) {
    var selectedImageUrl by remember { mutableStateOf<String?>(null) }
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
        if (contentState.tipImages.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(contentState.tipImages) { imageUrl ->
                    SubcomposeAsyncImage( // 💡 변경된 부분
                        model = imageUrl,
                        contentDescription = "Tip Image",
                        modifier = Modifier
                            .fillParentMaxSize()
                            .clickable {
                                // 이미지 클릭 시 다이얼로그를 띄우기 위해 url 저장
                                selectedImageUrl = imageUrl
                            },
                        contentScale = ContentScale.Fit,
                        loading = { // 💡 로딩 상태일 때 보여줄 UI
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    )
                }
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
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri1 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri1) },
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
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri2 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri2) },
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
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri3 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri3) },
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
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpeakerAnimatedIcon(
                        visible = contentState.contentUri.explanationSoundUri4 != null,
                        onClick = { speakerClick(contentState.contentUri.explanationSoundUri4) },
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
        if (selectedImageUrl != null) {
            var scale by remember { mutableFloatStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            Dialog(
                onDismissRequest = { selectedImageUrl = null },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false, // 화면 꽉 채우기 위해 설정
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.9f)) // 어두운 배경 반투명
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { selectedImageUrl = null } // 화면 아무데나 누르면 닫힘
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = selectedImageUrl,
                        contentDescription = "Full Screen Tip Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .pointerInput(Unit) {
                                detectTransformGestures { _, pan, zoom, _ ->
                                    // 확대 비율 제한 (1배 ~ 5배)
                                    scale = (scale * zoom).coerceIn(1f, 5f)

                                    // 1배보다 클 때만 패닝(이동) 허용, 1배일 때는 원래 위치로
                                    if (scale > 1f) {
                                        offset += pan
                                    } else {
                                        offset = Offset.Zero
                                    }
                                }
                            }
                            // 2. 그래픽 레이어에 상태 적용
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationX = offset.x
                                translationY = offset.y
                            }, // 가로 길이에 맞추고 비율 유지
                        contentScale = ContentScale.Fit // 잘리지 않고 전체가 다 보이게
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteContentCardMid(
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
fun FavoriteContentCardTop(
    contentState: ContentState,
    speakerClick: (Uri?) -> Unit = {},
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
            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                SpeakerAnimatedIcon(
                    visible = contentState.contentUri.titleSoundUri != null,
                    onClick = { speakerClick(contentState.contentUri.titleSoundUri) },
                )
            }
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
            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                SpeakerAnimatedIcon(
                    visible = contentState.contentUri.titleSoundUri != null,
                    onClick = { speakerClick(contentState.contentUri.titleSoundUri) },
                )
            }
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


