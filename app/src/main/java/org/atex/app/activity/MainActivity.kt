package org.atex.app.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*
import org.atex.app.R


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var fab: FloatingActionButton
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        appBarLayout = findViewById(R.id.app_bar_layout)
        bottomAppBar = findViewById(R.id.bottom_app_bar)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.nav_view)
        fab = findViewById(R.id.fab)
        // Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // Bottom
        bottomNavView.background = null
        bottomNavView.menu.getItem(2).isEnabled = false

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        fab.setColorFilter(Color.WHITE)
        fab.setOnClickListener {
            navController.navigate(R.id.navigation_place)
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_scan,
                R.id.navigation_place,
                R.id.navigation_ranking,
                R.id.navigation_profile
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        // Left Navigation
        navigationView.setupWithNavController(navController)
        // Bottom Navigation
        bottomNavView.setupWithNavController(navController)


//        navController.addOnDestinationChangedListener { _, destination, _ ->
//        }

    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_navigation, menu)
//        return true
//    }

    fun appBarVisible(visible: Boolean) {
        when {
            visible -> {
                appBarLayout.visibility = View.VISIBLE
                val params: CoordinatorLayout.LayoutParams =
                    layout_main.layoutParams as CoordinatorLayout.LayoutParams
                params.behavior = AppBarLayout.ScrollingViewBehavior()
            }
            else -> {
                appBarLayout.visibility = View.GONE

                val params: CoordinatorLayout.LayoutParams =
                    layout_main.layoutParams as CoordinatorLayout.LayoutParams
                params.behavior = null
            }
        }
    }

    fun bottomAppBarVisible(visible: Boolean) {
        when {
            visible -> {
                bottomAppBar.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE
            }
            else -> {
                bottomAppBar.visibility = View.GONE
                fab.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}