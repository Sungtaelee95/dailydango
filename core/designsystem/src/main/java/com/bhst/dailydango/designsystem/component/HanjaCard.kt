package com.bhst.dailydango.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.hanja.HanjaContent

@Composable
fun HanjaCard(
    hanjaContent: HanjaContent
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp,
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .defaultMinSize(
                        minWidth = 100.dp,
                        minHeight = 100.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = hanjaContent.hanja,
                    style = DailyDangoTheme.typography.bold60,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            if (hanjaContent.jlptLevel.isNotEmpty()){
                                TextChip(
                                    text = hanjaContent.jlptLevel,
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    labelColor = MaterialTheme.colorScheme.primaryFixed,
                                    border = BorderStroke(
                                        1.dp, MaterialTheme.colorScheme.primaryFixed,
                                    ),
                                    modifier = Modifier.wrapContentWidth()
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            if (hanjaContent.grade.isNotEmpty()){
                                TextChip(
                                    text = hanjaContent.grade,
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    labelColor = MaterialTheme.colorScheme.primaryFixed,
                                    border = BorderStroke(
                                        1.dp, MaterialTheme.colorScheme.primaryFixed,
                                    ),
                                    modifier = Modifier.wrapContentWidth()
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        // 상세 정보 목록
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            InfoRow(
                                label = "의미",
                                value = "${hanjaContent.koreanMeaning} ${hanjaContent.koreanSound}" +
                                        if (hanjaContent.koreanMeaning2.isNotEmpty()) {
                                            " / ${hanjaContent.koreanMeaning2} ${hanjaContent.koreanSound2}"
                                        } else {
                                            ""
                                        } +
                                        if (hanjaContent.koreanMeaning3.isNotEmpty()) {
                                            " / ${hanjaContent.koreanMeaning3} ${hanjaContent.koreanSound3}"
                                        } else {
                                            ""
                                        } +
                                        if (hanjaContent.koreanMeaning4.isNotEmpty()) {
                                            " / ${hanjaContent.koreanMeaning4} ${hanjaContent.koreanSound4}"
                                        } else {
                                            ""
                                        }
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(
                                        MaterialTheme.colorScheme.outline
                                    )
                            )
                            InfoRow(label = "음독", value = hanjaContent.onyomi)
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(
                                        MaterialTheme.colorScheme.outline
                                    )
                            )
                            InfoRow(label = "훈독", value = hanjaContent.kunyomi)
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(
                                        MaterialTheme.colorScheme.outline
                                    )
                            )
                            InfoRow(label = "부수", value = hanjaContent.radical)
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(
                                        MaterialTheme.colorScheme.outline
                                    )
                            )
                            InfoRow(label = "획수", value = "${hanjaContent.strokeCount}획")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // 레이블 테두리 및 배경색
        Surface(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                .padding(4.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Text(
                text = label,
                style = DailyDangoTheme.typography.medium14,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        // 상세 정보 값
        Text(
            text = value,
            style = DailyDangoTheme.typography.medium14,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HanjaCardPreview() {
    DailyDangoTheme {
        HanjaCard(
            hanjaContent = HanjaContent(
                hanja = "先",
                koreanMeaning = "한국어",
                koreanSound = "음",
                koreanMeaning2 = "한국어",
                koreanSound2 = "음",
                koreanMeaning3 = "한국어",
                koreanSound3 = "음",
                koreanMeaning4 = "한국어",
                koreanSound4 = "음",
                jlptLevel = "JLPT N4",
                grade = "소1",
                onyomi = "음독",
                kunyomi = "훈독",
                radical = "부수",
                strokeCount = "2"
            )
        )
    }
}