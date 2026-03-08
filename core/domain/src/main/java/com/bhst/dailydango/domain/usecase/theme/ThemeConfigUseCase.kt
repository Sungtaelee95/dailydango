package com.bhst.dailydango.domain.usecase.theme

import com.bhst.dailydango.domain.repository.theme.ThemeConfigRepository
import javax.inject.Inject

class ThemeConfigUseCase @Inject constructor(
    private val themeConfigRepository: ThemeConfigRepository
) {
    operator fun invoke() = themeConfigRepository.getAppThemeConfig()
}