package com.bhst.dailydango.home

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.feature.home.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun HomeTabScreen(
    navigateToHiraganaStudy: () -> Unit = {},
    navigateToKatakanaStudy: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
    navigateToSearch: () -> Unit = {}
) {
    HomeTabContent(
        navigateToHiraganaStudy = navigateToHiraganaStudy,
        navigateToKatakanaStudy = navigateToKatakanaStudy,
        navigateToGrammarStudy = navigateToGrammarStudy,
        navigateToGrammarTest = navigateToGrammarTest,
        navigateToSearch = navigateToSearch
    )
}

@Composable
fun HomeTabContent(
    navigateToHiraganaStudy: () -> Unit = {},
    navigateToKatakanaStudy: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
    navigateToSearch: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 40.dp, end= 40.dp, bottom = 20.dp)
            .verticalScroll(scrollState),
    ) {
        DailyGoalContent(
            goalDays = 3
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        SearchContent(
            onClick = navigateToSearch
        )
        Spacer(
            modifier = Modifier.height(40.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row {
                Text(
                    text = stringResource(R.string.hiragana_gatacana),
                    style = DailyDangoTheme.typography.bold16
                )
                Text(
                    text = stringResource(R.string.study_do_it),
                    style = DailyDangoTheme.typography.medium16
                )
            }
            Row(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                JapaneseStudyContent(
                    modifier = Modifier
                        .width(300.dp)
                        .height(160.dp),
                    onClick = navigateToHiraganaStudy,
                    title = stringResource(R.string.hiragana),
                    content = stringResource(R.string.for_study)
                )
                JapaneseStudyContent(
                    modifier = Modifier
                        .width(300.dp)
                        .height(160.dp),
                    onClick = navigateToKatakanaStudy,
                    title = stringResource(R.string.gatakana),
                    content = stringResource(R.string.for_study),
                    painter = painterResource(R.drawable.gatakana_study_img)
                )
            }
            Row(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                JapaneseStudyContent(
                    modifier = Modifier
                        .width(300.dp)
                        .height(160.dp),
                    onClick = navigateToGrammarStudy,
                    title = stringResource(R.string.basic_expressions),
                    content = stringResource(R.string.for_study),
                    painter = painterResource(R.drawable.basic_expressions_img)
                )
                JapaneseStudyContent(
                    modifier = Modifier
                        .width(300.dp)
                        .height(160.dp),
                    onClick = navigateToGrammarTest,
                    title = stringResource(R.string.level),
                    content = stringResource(R.string.for_test),
                    painter = painterResource(R.drawable.level_test_img)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.for_me),
                    style = DailyDangoTheme.typography.medium16
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.level_up),
                    style = DailyDangoTheme.typography.bold16
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.do_it),
                    style = DailyDangoTheme.typography.medium16
                )
            }
        }
    }
}

@Preview(showBackground = true, device = TABLET)
@Composable
fun HomeTabContentPreview() {
    DailyDangoTheme {
        HomeTabContent()
    }
}