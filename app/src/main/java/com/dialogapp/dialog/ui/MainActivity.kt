package com.dialogapp.dialog.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dialogapp.dialog.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Light)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
