package com.bhst.dailydango

import android.app.Application
import coil.ImageLoader
import com.google.android.gms.ads.MobileAds
import coil.ImageLoaderFactory
import coil.decode.ImageDecoderDecoder
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class DailyDangoApp: Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
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