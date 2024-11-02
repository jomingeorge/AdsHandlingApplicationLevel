package com.hitbytes.ads_handling_application_level

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.util.Log
import android.view.Window
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.Calendar

object AdsHandling {
    var transitions = 0
    var mInterstitialAd: InterstitialAd? = null


    fun showAd(context: Context,activity: Activity,onTransitionAction: TransitionAction) {
        if (mInterstitialAd != null) {
            val prefs: SharedPreferences = activity.getSharedPreferences("pref", Context.MODE_PRIVATE)
            val adIntervalTracker = prefs.getLong("adintervaltracker", 0)
            val c = Calendar.getInstance()
            val currentTime = c.timeInMillis

            if (transitions > 3&&(currentTime-adIntervalTracker)>1000) {
                //todo Light or Black theme on xml
                val waitDialog = MaterialAlertDialogBuilder(context)
                    .setView(R.layout.dialog_please_wait)
                    .create()

                val editor = prefs.edit()
                editor.putLong("adintervaltracker",currentTime)
                editor.apply()

                mInterstitialAd?.show(activity)
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        mInterstitialAd = null
                        onTransitionAction()
                        transitions = 0
                        loadAd(context)
                        val handler = Handler(activity.mainLooper)
                        handler.postDelayed({
                            waitDialog.dismiss()
                        }, 700)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        mInterstitialAd = null
                        onTransitionAction()
                        loadAd(context)
                    }

                    override fun onAdShowedFullScreenContent() {
                        waitDialog.show()
                    }
                }
            }else{
                onTransitionAction()
            }
        }else{
            onTransitionAction()
            loadAd(context)
        }
    }

    fun loadAd(context: Context){

        //todo premium checking
        //if (premiumuser==0) {

            val adRequest: AdRequest = AdRequest.Builder().build()

            InterstitialAd.load(context, context.getString(R.string.full_ad_unit_id), adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                    }
                })
        }
    //}

}

typealias TransitionAction = () -> Unit