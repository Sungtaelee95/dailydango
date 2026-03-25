package com.bhst.dailydango.model.suggestion

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date


data class Suggestion(
    @DocumentId
    val id: String = "",
    val content: String = "",                            // 문의 내용
    val email: String = "",                              // 답변 받을 이메일
    val userName: String = "",                           // 사용자 이름
    val suggestionType:SuggestionType = SuggestionType.CS, // 문의 유형
    val status: InquiryStatus = InquiryStatus.PENDING,       // 처리 상태
    val attachmentUrls: List<String> = emptyList(),      // 첨부파일 URL 리스트
    @ServerTimestamp
    val privacyConsentAt: Date? = null,                  // 개인정보 수집 동의 시간
    @ServerTimestamp
    val createdAt: Date? = null // 작성된 시간
)