package com.bhst.dailydango.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bhst.dailydango.app.feature.menu.R
import com.bhst.dailydango.designsystem.component.ColorBar
import com.bhst.dailydango.designsystem.component.ImageCard
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.model.theme.config.ThemeConfig

@Composable
fun ThemeScreen(
    viewModel: ThemeViewModel = hiltViewModel()
) {
    val themeConfig by viewModel.themeConfig.collectAsStateWithLifecycle(ThemeConfig.SYSTEM)
    ThemeContent(
        selectedTheme = themeConfig,
        updateTheme = viewModel::updateThemeConfig
    )
}

@Composable
fun ThemeContent(
    selectedTheme: ThemeConfig,
    updateTheme: (ThemeConfig) -> Unit = {}
) {
    Column {
        ColorBar(
            color = MaterialTheme.colorScheme.tertiaryFixed
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                ImageCard(
                    painter = painterResource(R.drawable.theme_img),
                    contentDescription = "Favorites",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(R.string.theme_setting),
                    style = DailyDangoTheme.typography.bold20,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            // 시스템 설정 따라가기
            ThemeRadioItem(
                label = stringResource(R.string.system_mode),
                description = stringResource(R.string.system_mode_description),
                selected = selectedTheme == ThemeConfig.SYSTEM,
                onClick = {
                    // 시스템 모드 선택 시 현재 시스템 테마 감지 가능
                    updateTheme(ThemeConfig.SYSTEM)
                }
            )

            // 라이트 모드
            ThemeRadioItem(
                label = stringResource(R.string.light_mode),
                description = stringResource(R.string.light_mode_description),
                selected = selectedTheme == ThemeConfig.LIGHT,
                onClick = { updateTheme(ThemeConfig.LIGHT) }
            )

            // 다크 모드
            ThemeRadioItem(
                label = stringResource(R.string.dark_mode),
                description = stringResource(R.string.dark_mode_description),
                selected = selectedTheme == ThemeConfig.DARK,
                onClick = { updateTheme(ThemeConfig.DARK) }
            )
        }
    }
}

@Composable
fun ThemeRadioItem(
    label: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primaryFixed,
                unselectedColor = MaterialTheme.colorScheme.primaryFixed,
                disabledSelectedColor = MaterialTheme.colorScheme.primaryFixed,
                disabledUnselectedColor = MaterialTheme.colorScheme.primaryFixed,
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = label,
                style = DailyDangoTheme.typography.bold16,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = description,
                style = DailyDangoTheme.typography.medium14,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}