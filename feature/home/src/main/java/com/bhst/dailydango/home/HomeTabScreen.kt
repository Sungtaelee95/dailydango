package com.bhst.dailydango.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhst.dailydango.ad_mob.loadInterstitialAd
import com.bhst.dailydango.ad_mob.showInterstitialAd
import com.bhst.dailydango.app.feature.home.R
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.google.android.gms.ads.interstitial.InterstitialAd

@Composable
fun HomeTabScreen(
    navigateToHiraganaKatakanaTip: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
    navigateToSearch: () -> Unit = {}
) {
    HomeTabContent(
        navigateToHiraganaKatakanaTip = {
            navigateToHiraganaKatakanaTip()
        },
        navigateToGrammarStudy = navigateToGrammarStudy,
        navigateToGrammarTest = navigateToGrammarTest,
        navigateToSearch = navigateToSearch,
    )
}

@Composable
fun HomeTabContent(
    navigateToHiraganaKatakanaTip: () -> Unit = {},
    navigateToGrammarStudy: () -> Unit = {},
    navigateToGrammarTest: () -> Unit = {},
    navigateToSearch: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        SearchContent(
            onClick = navigateToSearch,
            modifier = Modifier.width(480.dp)
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Column(
            modifier = Modifier
                .width(480.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row {
                Text(
                    text = stringResource(R.string.hiragana_katacana),
                    style = DailyDangoTheme.typography.bold16,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.study_do_it),
                    style = DailyDangoTheme.typography.medium16,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            JapaneseStudyContent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(R.string.hiragana_katakana_study),
                subtitle = stringResource(R.string.strong_foundation),
                onClick = navigateToHiraganaKatakanaTip,
                painter = painterResource(R.drawable.hiragana_katakana_study),
            )

            JapaneseStudyContent(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(R.string.learn_basic_expressions),
                subtitle = stringResource(R.string.with_conversation),
                leftColor = MaterialTheme.colorScheme.secondary,
                rightColor = MaterialTheme.colorScheme.onSecondary,
                onClick = navigateToGrammarStudy,
                painter = painterResource(R.drawable.basic_expressions_img)
            )

//            JapaneseStudyContent(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                title = stringResource(R.string.take_skill_test),
//                leftColor = MaterialTheme.colorScheme.tertiary,
//                rightColor = MaterialTheme.colorScheme.onTertiary,
//                painter = painterResource(R.drawable.level_test_img)
//            )
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