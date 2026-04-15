package com.bhst.dailydango

import android.app.Application
import coil.ImageLoader
import com.google.android.gms.ads.MobileAds
import coil.ImageLoaderFactory
import coil.decode.ImageDecoderDecoder
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class DailyDangoApp: Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        val requestConfiguration = RequestConfiguration.Builder()
            // 1. 광고 콘텐츠 등급을 'G(전체이용가)'로 강제 고정
            .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
            // 2. 아동 온라인 개인정보 보호법(COPPA)에 따라 어린이를 대상으로 처리하도록 태그 설정
            .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
            .build()

        MobileAds.setRequestConfiguration(requestConfiguration)
    }
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                // GIF 지원 추가
                add(ImageDecoderDecoder.Factory())
            }
            // 메모리 캐시 정책 등 추가 설정이 필요하다면 여기서 진행
            .build()
    }
}