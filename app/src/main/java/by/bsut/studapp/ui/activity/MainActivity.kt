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
import by.bsut.studapp.timetable.presenter.api.RetrofitTimetableApiImplementation
import by.bsut.studapp.timetable.presenter.database.ParaRoomDatabase
import by.bsut.studapp.timetable.presenter.preferences.PREFERENCES
import by.bsut.studapp.timetable.presenter.preferences.TimetableNetworkPreferenceEditor
import by.bsut.studapp.utils.HelperFuns.showToast
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

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
        return when (item.itemId) {
            R.id.changeTheme -> changeTheme()
            R.id.refreshTimetable -> refreshTimetable()
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun refreshTimetable(): Boolean {
        CoroutineScope(Dispatchers.IO).launch {
            val prefEditor = TimetableNetworkPreferenceEditor(
                applicationContext.getSharedPreferences(PREFERENCES, MODE_PRIVATE))
            val apiData = RetrofitTimetableApiImplementation.getListOfParas(prefEditor.getGroup()!!)
            MainScope().launch { showToast(applicationContext, R.string.toast_refresh_start) }
            val dao = ParaRoomDatabase
                .getDatabase(applicationContext, CoroutineScope(Dispatchers.IO)).paraDao()

            if (apiData == null || apiData.version == prefEditor.getTimetableVersion()) {
                MainScope().launch { showToast(applicationContext, R.string.toast_actual_version) }
                return@launch
            }

            prefEditor.putTimetableVersion(apiData.version)

            for (para in apiData.paras) { dao.insert(para) }
        }
        return true
    }
}