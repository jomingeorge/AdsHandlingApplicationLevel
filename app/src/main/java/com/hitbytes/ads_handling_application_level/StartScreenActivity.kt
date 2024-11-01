package com.hitbytes.ads_handling_application_level

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val premiumuser = 0

        if (premiumuser==1){
            intentTransition()
        }else {
            val tutorialShown = "shown"
            if (tutorialShown==""){
                intentTransition()
            }else {
                showAdfunction()
            }
        }

    }

    fun showAdfunction(){
        val applicationVar = application as? MyApplication

        if (applicationVar == null) {
            intentTransition()
            return
        }

        applicationVar.showAdIfAvailable(
            this@StartScreenActivity,
            object : MyApplication.OnShowAdCompleteListener {
                override fun onShowAdComplete() {
                    intentTransition()
                }
            }
        )


            /*
        // Show the app open ad.
        applicationVar.showAdIfAvailable(this@StartScreenActivity, object : AppOpenAd.OnShowAdCompleteListener {
            override fun onShowAdComplete() {
                intentTransition()
            }
        }) {
            if(!it)
            {
                intentTransition()
            }
        }

             */
    }

    fun intentTransition(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        /*
        if (lang==""){
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("notificationContent",notificationContent)
            startActivity(intent)
            finish()
        }

         */
    }


}