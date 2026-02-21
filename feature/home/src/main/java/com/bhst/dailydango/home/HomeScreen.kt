package com.bhst.dailydango.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        DailyGoalContent(
            goalDays = 3
        )
        Spacer(modifier = Modifier.height(20.dp))
        SearchContent(
            onClick = navigateToSearch
        )
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
            .height(64.dp),
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