package com.ankit.crunch.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ankit.crunch.R
import com.ankit.crunch.ViewModel
import com.ankit.crunch.debugger
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
           val userViewModel by viewModel<ViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController=findNavController(R.id.nav_host_fragment)
        bottom_nav_view.setupWithNavController(navController);
        userViewModel.data.observe(this@MainActivity, Observer {
            debugger("All News->>$it")
        })
    }

}
