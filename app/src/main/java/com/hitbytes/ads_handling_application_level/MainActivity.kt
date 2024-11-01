package com.hitbytes.ads_handling_application_level

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hitbytes.ads_handling_application_level.AdsHandling.showAd

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        button.setOnClickListener {
            showAd(context = this,
                activity = this@MainActivity,
                onTransitionAction = {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                }
            )
        }
        button2.setOnClickListener {
            showAd(context = this,
                activity = this@MainActivity,
                onTransitionAction = {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                }
            )
        }
        button3.setOnClickListener {
            showAd(context = this,
                activity = this@MainActivity,
                onTransitionAction = {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                }
            )
        }
        button4.setOnClickListener {
            showAd(context = this,
                activity = this@MainActivity,
                onTransitionAction = {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}