package com.gid.gidassistant.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gid.gidassistant.R
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.permissions_layout)
    }
}