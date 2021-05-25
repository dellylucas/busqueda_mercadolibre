package com.dfl.busquedamercadolibre.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.dfl.busquedamercadolibre.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), IBaseActivity {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navController =
            (supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment).navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    override fun setLoading(isVisible: Boolean) {
        val visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.backgroundView.visibility = visibility
        binding.animationView.visibility = visibility
    }
}