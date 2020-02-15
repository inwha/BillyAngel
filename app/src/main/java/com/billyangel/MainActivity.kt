package com.billyangel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.view_title.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initViews()
    }

    private fun initViews() {
        getNavController()?.let {
            titleBar(it)
            initBottomView(it)
        }
    }

    private fun titleBar(navController: NavController) {
        settingBtn.setOnClickListener {
            navController.navigate(R.id.settingFragment)
        }
    }

    private fun initBottomView(navController: NavController) {
        bottomView.setupWithNavController(navController)
    }

    private fun getNavController(): NavController? {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.hostLayout) as NavHostFragment? ?: return null
        return host.navController
    }

    override fun onBackPressed() {
        getNavController()?.let {
            val current = it.currentDestination ?: return@let
            if (current.id == R.id.settingFragment) {
                it.popBackStack()
                return
            }
        }
        super.onBackPressed()
    }
}