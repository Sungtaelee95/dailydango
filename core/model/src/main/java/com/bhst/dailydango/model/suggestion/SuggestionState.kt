package com.bhst.dailydango.model.suggestion

import android.net.Uri
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date


data class SuggestionState(
    @DocumentId
    val id: String = "",
    val content: String = "",                            // 문의 내용
    val email: String = "",                              // 답변 받을 이메일
    val userName: String = "",                           // 사용자 이름
    val suggestionType: SuggestionType = SuggestionType.APP_ERROR, // 문의 유형
    val status: InquiryStatus = InquiryStatus.PENDING,       // 처리 상태
    val attachmentUrls: List<Uri> = emptyList(),      // 첨부파일 URL 리스트
    val privacyConsentAt: Boolean = false,                  // 개인정보 수집 동의 시간
) {
    fun toData(): Suggestion {
        return Suggestion(
            id = id,
            content = content,
            email = email,
            userName = userName,
            suggestionType = suggestionType,
            status = status,
        )
    }
}