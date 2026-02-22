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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.app.feature.home.R
import com.bhst.dailydango.designsystem.component.DailyDangoElevationCard
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.component.NotOutLineSearchField
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme

@Composable
fun HomeScreen(
    navigateToHiraganaStudy: () -> Unit = {},
    navigateToKatakanaStudy: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
    navigateToSearch: () -> Unit = {}
) {
    HomeContent(
        navigateToHiraganaStudy = navigateToHiraganaStudy,
        navigateToKatakanaStudy = navigateToKatakanaStudy,
        navigateToGrammarStudy = navigateToGrammarStudy,
        navigateToGrammarTest = navigateToGrammarTest,
        navigateToSearch = navigateToSearch
    )
}

@Composable
fun HomeContent(
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
            .padding(12.dp)
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
                    .height(180.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                JapaneseStudyContent(
                    modifier = Modifier
                        .weight(1f),
                    onClick = navigateToHiraganaStudy,
                    title = stringResource(R.string.hiragana),
                    content = stringResource(R.string.for_study)
                )
                Spacer(modifier = Modifier.width(20.dp))
                JapaneseStudyContent(
                    modifier = Modifier
                        .weight(1f),
                    onClick = navigateToKatakanaStudy,
                    title = stringResource(R.string.gatakana),
                    content = stringResource(R.string.for_study),
                    painter = painterResource(R.drawable.gatakana_study_img)
                )
            }
            Row(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                JapaneseStudyContent(
                    modifier = Modifier
                        .weight(1f),
                    onClick = navigateToGrammarStudy,
                    title = stringResource(R.string.basic_expressions),
                    content = stringResource(R.string.for_study),
                    painter = painterResource(R.drawable.basic_expressions_img)
                )
                Spacer(modifier = Modifier.width(20.dp))
                JapaneseStudyContent(
                    modifier = Modifier
                        .weight(1f),
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

@Composable
fun JapaneseStudyContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    title: String,
    content: String,
    painter: Painter = painterResource(R.drawable.hiragana_study_img)
) {
    DailyDangoElevationCard(
        modifier = modifier
            .fillMaxSize(),
        shape = 20.dp,
        color = MaterialTheme.colorScheme.primary,
        elevation = 8.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = DailyDangoTheme.typography.bold24,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = content,
                style = DailyDangoTheme.typography.medium24,
                color = MaterialTheme.colorScheme.onBackground
            )
            ImageCard(
                modifier = Modifier.weight(1f),
                painter = painter,
                contentDescription = "",
                onClick = onClick
            )
        }

    }
}

@Composable
fun SearchContent(
    onClick: () -> Unit = {}
) {
    DailyDangoElevationCard(
        modifier = Modifier
            .fillMaxWidth(),
        shape = 20.dp,
        elevation = 8.dp,
        onClick = onClick
    ) {
        NotOutLineSearchField(
            hint = R.string.hint_search_word_or_grammar,
            enabled = false
        )
    }

}

@Composable
fun DailyGoalContent(
    goalDays: Int = 3
) {
    DailyDangoElevationCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(top = 8.dp),
        shape = 20.dp,
        color = MaterialTheme.colorScheme.primary,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageCard(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp),
                painter = painterResource(R.drawable.fire_img),
                contentDescription = ""
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.text_daily_goal_achieved),
                    textAlign = TextAlign.Center,
                    style = DailyDangoTheme.typography.bold24,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${goalDays}일 연속",
                    textAlign = TextAlign.Center,
                    style = DailyDangoTheme.typography.medium20
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    DailyDangoTheme {
        HomeContent()
    }
}