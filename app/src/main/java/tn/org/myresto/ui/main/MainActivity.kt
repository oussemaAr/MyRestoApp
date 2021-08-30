package tn.org.myresto.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import tn.org.myresto.R
import tn.org.myresto.databinding.ActivityMainBinding
import tn.org.myresto.utils.isLocationEnable
import tn.org.myresto.utils.isPermissionGranted


@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        if (!isLocationEnable(this) or !isPermissionGranted(this)) {
            navController.navigate(R.id.permissionFragment)
        } else {
            navController.navigate(R.id.restaurantMapFragment)
        }
    }
}