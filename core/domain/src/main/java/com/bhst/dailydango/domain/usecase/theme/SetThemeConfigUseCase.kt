package com.bhst.dailydango.domain.usecase.theme

import com.bhst.dailydango.domain.repository.theme.ThemeConfigRepository
import com.bhst.dailydango.model.theme.config.ThemeConfig
import javax.inject.Inject

class SetThemeConfigUseCase @Inject constructor(
    private val themeConfigRepository: ThemeConfigRepository
) {
    suspend operator fun invoke(themeConfig: ThemeConfig) {
        themeConfigRepository.updateAppThemeConfig(themeConfig)
    }
}