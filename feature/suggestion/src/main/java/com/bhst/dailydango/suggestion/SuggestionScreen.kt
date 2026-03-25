package com.bhst.dailydango.suggestion

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.suggestion.R
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.suggestion.SuggestionState
import com.bhst.dailydango.model.suggestion.SuggestionType
import kotlinx.coroutines.launch

@Composable
fun SuggestionScreen(
    viewModel: SuggestionViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutine = rememberCoroutineScope()
    SuggestionContent(
        uiState = uiState,
        updateUiState = viewModel::updateUiState,
        onUpload = {
            coroutine.launch {
                viewModel.uploadSuggestion()
                onBack() // 업로드 후 뒤로가기 처리가 필요하다면 추가 (또는 ViewModel 내 결과 수신 후 처리)
            }
        }
    )
}

@Composable
fun SuggestionContent(
    uiState: SuggestionState = SuggestionState(),
    updateUiState: (SuggestionState) -> Unit = {},
    onUpload: () -> Unit = {}
) {
    // 갤러리에서 다중 이미지를 가져오는 런처
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris: List<Uri> ->
        if (uris.isNotEmpty()) {
            updateUiState(uiState.copy(attachmentUrls = uiState.attachmentUrls + uris))
        }
    }

    // 제출 버튼 활성화 조건 (필수 항목: 이름, 이메일, 내용, 개인정보 동의)
    val isSubmitEnabled = uiState.userName.isNotBlank() &&
            uiState.email.isNotBlank() &&
            uiState.content.isNotBlank() &&
            uiState.privacyConsentAt

    var showPrivacyDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. 상단 귀여운 당고 소녀 이미지 (앞서 추출하신 누끼 이미지 리소스를 R.drawable에 추가하여 사용)
        Image(
            painter = painterResource(id = R.drawable.img_suggestion_character),
            contentDescription = "문의하기 캐릭터",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = stringResource(R.string.suggestion_header_msg),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 2. 문의 유형 선택 (Segmented Button 형태의 커스텀 UI 또는 Dropdown)
        Text(
            text = stringResource(R.string.suggestion_type),
            style = DailyDangoTheme.typography.medium16
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SuggestionType.entries.forEach { type ->
                val isSelected = uiState.suggestionType == type
                FilterChip(
                    selected = isSelected,
                    onClick = { updateUiState(uiState.copy(suggestionType = type)) },
                    label = {
                        Text(
                            text = type.value,
                            style = DailyDangoTheme.typography.medium12
                        )
                    }, // 한글 매핑이 있다면 변환해서 출력
                    colors = FilterChipDefaults.filterChipColors(
                        // 1. 선택되었을 때의 색상
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.inverseSurface,

                        // 2. 선택되지 않았을 때의 색상 (필요시 커스텀, 안 적으면 기본값 적용됨)
                        containerColor = MaterialTheme.colorScheme.background,
                        labelColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }

        // 3. 사용자 이름 입력 (선택)
        OutlinedTextField(
            value = uiState.userName,
            onValueChange = { updateUiState(uiState.copy(userName = it)) },
            label = {
                Text(
                    text = stringResource(R.string.suggestion_name_required),
                    style = DailyDangoTheme.typography.medium16
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // 4. 이메일 입력 (필수)
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { updateUiState(uiState.copy(email = it)) },
            label = {
                Text(
                    text = stringResource(R.string.suggestion_email_required),
                    style = DailyDangoTheme.typography.medium16
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // 5. 문의 내용 입력 (필수) - 이전 질문에서 다룬 lineHeight, letterSpacing 적용 예시
        OutlinedTextField(
            value = uiState.content,
            onValueChange = { updateUiState(uiState.copy(content = it)) },
            label = {
                Text(
                    text = stringResource(R.string.suggestion_content_required),
                    style = DailyDangoTheme.typography.medium16
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp),
            textStyle = DailyDangoTheme.typography.medium16,
        )

        // 6. 이미지 첨부
        Text(
            text = stringResource(R.string.suggestion_attach_photo),
            style = DailyDangoTheme.typography.medium16
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                // 이미지 추가 버튼
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .clickable {
                            imagePickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add_24px),
                        contentDescription = "사진 추가",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            items(uiState.attachmentUrls) { uri ->
                Box(modifier = Modifier.size(80.dp)) {
                    // Coil 라이브러리를 사용한 로컬 Uri 이미지 로딩
                    ImageCard(
                        model = uri,
                        contentDescription = "첨부된 사진",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    // 삭제 버튼 (우측 상단)
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp) // 우측 상단 모서리에서 살짝 띄우기
                            .size(24.dp)   // 원하는 딱 그 크기로 고정
                            .clip(CircleShape) // 터치 영역(리플 효과)을 동그랗게 제한
                            .clickable { // 클릭 이벤트
                                val newList =
                                    uiState.attachmentUrls.toMutableList().apply { remove(uri) }
                                updateUiState(uiState.copy(attachmentUrls = newList))
                            },
                        contentAlignment = Alignment.Center // 내부 아이콘을 정중앙에 배치
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.delete_24px),
                            contentDescription = "삭제",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(20.dp) // 아이콘 자체 크기는 살짝 작게
                        )
                    }
                }
            }
        }

        // 7. 개인정보 동의 체크박스
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        updateUiState(uiState.copy(privacyConsentAt = !uiState.privacyConsentAt))
                    }
            ) {
                Checkbox(
                    checked = uiState.privacyConsentAt,
                    onCheckedChange = { updateUiState(uiState.copy(privacyConsentAt = it)) }
                )
                Text(
                    text = stringResource(R.string.suggestion_privacy_consent_required),
                    style = DailyDangoTheme.typography.medium16
                )
            }
            Text(
                text = stringResource(id = R.string.suggestion_privacy_policy_detail),
                style = DailyDangoTheme.typography.medium16.copy(
                    color = MaterialTheme.colorScheme.primary, // 앱 메인 색상으로 강조
                    textDecoration = TextDecoration.Underline // 밑줄 추가로 누를 수 있음을 표시
                ),
                modifier = Modifier
                    .clickable { showPrivacyDialog = true } // 클릭 시 다이얼로그 띄우기
                    .padding(start = 8.dp)
            )
        }

        if (showPrivacyDialog) {
            AlertDialog(
                onDismissRequest = { showPrivacyDialog = false }, // 배경 클릭 시 닫힘
                title = {
                    Text(
                        text = stringResource(id = R.string.suggestion_privacy_policy_title),
                        style = DailyDangoTheme.typography.bold16,
                    )
                },
                text = {
                    // 내용이 길어질 수 있으므로 스크롤 가능하게 처리
                    Text(
                        text = stringResource(id = R.string.suggestion_privacy_policy_content),
                        style = DailyDangoTheme.typography.medium16,
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showPrivacyDialog = false }) {
                        Text(
                            text = stringResource(R.string.check),
                            style = DailyDangoTheme.typography.bold12
                        )
                    }
                }
            )
        }

        // 8. 제출 버튼
        if (isSubmitEnabled) {
            Button(
                onClick = onUpload,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.suggestion_submit),
                    style = DailyDangoTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun SuggestionContentPreview() {
    DailyDangoTheme {
        SuggestionContent()
    }
}