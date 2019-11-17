package com.vanks.sound_cloud_client

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vanks.sound_cloud_client.databinding.ActivityMainBinding
import com.vanks.sound_cloud_client.util.Reusable

class MainActivity : AppCompatActivity() {

    var musicRepository = Reusable.musicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        val navView: BottomNavigationView = binding.root.findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        var mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.inPlayerMode = musicRepository.retrieveInPlayerMode()
        mainActivityViewModel.inPlayerMode.observe(this, Observer {
            binding.inPlayerMode = it
        })

    }
}
