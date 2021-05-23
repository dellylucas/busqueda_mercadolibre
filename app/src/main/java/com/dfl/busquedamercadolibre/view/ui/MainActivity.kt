package com.dfl.busquedamercadolibre.view.ui

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.dfl.busquedamercadolibre.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

      /*  val model: SearchViewModel by viewModels()
        model.getUsers().observe(this, Observer<List<User>>{ users ->
            // update UI
        })*/
       /* val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val id = destination
            this.supportActionBar?.show()
            /*when (id) {
                R.id.searchFragment ->this.supportActionBar?.hide()
                else -> this.supportActionBar?.hide()
            }*/
        }*/
    }
}