package com.developeralamin.admanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    var adManagerInterstitialAd: AdManagerInterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdManagerAdView = findViewById<AdManagerAdView>(R.id.adManagerAdView)
        val adRequest = AdManagerAdRequest.Builder().build()
        mAdManagerAdView.loadAd(adRequest)

        loadInterstaiAds()

        loadNativeAds()
    }

    private fun loadNativeAds() {

        val adLoader: AdLoader = AdLoader.Builder(this, "/6499/example/native")
            .forNativeAd { nativeAd ->

                val style = NativeTemplateStyle.Builder().build()
                val template = findViewById<TemplateView>(R.id.my_template)
                template.setStyles(style)
                template.setNativeAd(nativeAd)
            }
            .build()

        adLoader.loadAd(AdManagerAdRequest.Builder().build())

    }

    private fun loadInterstaiAds() {
        var adRequest = AdManagerAdRequest.Builder().build()

        AdManagerInterstitialAd.load(
            this,
            "/6499/example/interstitial",
            adRequest,
            object : AdManagerInterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("Sakib", "Ads no loaded")
                    adManagerInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: AdManagerInterstitialAd) {
                    Log.d("sakib", "Ads is loaded")

                    interstitialAd.show(this@MainActivity)

                }
            })
    }
}
