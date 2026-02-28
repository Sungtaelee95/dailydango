package com.bhst.dailydango.ad_mob

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.bhst.dailydango.app.core.ad.mob.BuildConfig

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

// 1. 광고 미리 로드하기
fun loadInterstitialAd(context: Context, onAdLoaded: (InterstitialAd?) -> Unit) {
    val adRequest = AdRequest.Builder().build()

    val adUnitId = BuildConfig.ADMOB_INTERSTITIAL_ID

    InterstitialAd.load(
        context,
        adUnitId,
        adRequest,
        object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.e("AdMob", "광고 로드 실패: ${adError.message}")
                onAdLoaded(null)
            }

            override fun onAdLoaded(ad: InterstitialAd) {
                Log.d("AdMob", "광고 로드 성공!")
                onAdLoaded(ad)
            }
        }
    )
}

// 2. 준비된 광고 보여주기
fun showInterstitialAd(
    context: Context,
    ad: InterstitialAd?,
    onAdDismissed: () -> Unit // 광고가 닫히거나 실패했을 때 실행할 콜백 (예: 다음 화면으로 이동)
) {
    val activity = context.findActivity()

    if (ad != null && activity != null) {
        // 광고 상태를 감지하는 콜백 설정
        ad.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d("AdMob", "사용자가 광고를 닫음")
                onAdDismissed() // 사용자가 닫기(X) 버튼을 누르면 다음 작업 실행
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                Log.e("AdMob", "광고 표시 실패: ${adError.message}")
                onAdDismissed() // 에러가 나도 앱 진행이 막히지 않도록 다음 작업 실행
            }

            override fun onAdShowedFullScreenContent() {
                Log.d("AdMob", "광고가 화면에 표시됨")
                // 전면 광고는 한 번 보여주면 재사용이 불가능합니다.
            }
        }

        ad.show(activity)
    } else {
        Log.d("AdMob", "광고가 아직 로딩 중이거나 없습니다. 바로 다음 작업 실행.")
        onAdDismissed()
    }
}