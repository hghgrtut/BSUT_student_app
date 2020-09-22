package by.bsut.studapp.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.bsut.studapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        NavigationUI.setupWithNavController(toolbar, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.changeTheme) changeTheme()
        else super.onOptionsItemSelected(item)
    }

    private fun changeTheme(): Boolean {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Configuration.UI_MODE_NIGHT_NO ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        return true
    }
}