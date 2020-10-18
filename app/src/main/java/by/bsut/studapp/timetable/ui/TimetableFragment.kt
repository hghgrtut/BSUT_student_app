package by.bsut.studapp.timetable.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.bsut.studapp.R
import by.bsut.studapp.timetable.constants.HAS_PARAS_ON_SATURDAY
import by.bsut.studapp.timetable.data.enums.WeekMode
import by.bsut.studapp.timetable.data.objects.Para
import by.bsut.studapp.timetable.presenter.dao.ParaDao
import by.bsut.studapp.timetable.presenter.database.ParaRoomDatabase
import by.bsut.studapp.timetable.presenter.preferences.PREFERENCES
import by.bsut.studapp.timetable.presenter.preferences.TimetableNetworkPreferenceEditor
import by.bsut.studapp.timetable.presenter.preferences.TimetablePreferenceEditor
import by.bsut.studapp.timetable.view.adapter.ParaViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TimetableFragment : Fragment(R.layout.fragment_timetable) {
    private lateinit var dao: ParaDao
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private var excludedWeekMode: Int = 1
    private var day: Int = 1
    private var weekLenght: Int = FIVE_DAY_WEEK
    private lateinit var prefEditor: TimetablePreferenceEditor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs: SharedPreferences =
            view.context.applicationContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        prefEditor = TimetablePreferenceEditor(prefs)
        if (HAS_PARAS_ON_SATURDAY.contains(TimetableNetworkPreferenceEditor(prefs).getGroup())) {
            weekLenght = SIX_DAY_WEEK
        }

        layoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById<RecyclerView>(R.id.paras_list).apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
        }

        refreshExcludedWeek()

        day = prefEditor.getDay()

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch { refreshParas(view) }

        view.findViewById<Button>(R.id.modeButton).setOnClickListener {
            prefEditor.setWeekMode(excludedWeekMode)
            refreshExcludedWeek()
            scope.launch { refreshParas(view) }
        }

        view.findViewById<Button>(R.id.previousButton).setOnClickListener {
            val newDay = day - 1
            changeDay(if (newDay >= 0) newDay else weekLenght - 1)
            scope.launch { refreshParas(view) }
        }

        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            changeDay((day + 1) % weekLenght)
            scope.launch { refreshParas(view) }
        }
    }

    private fun changeDay(newDay: Int) { // View is needed to refresh ViewAdapter
        day = newDay
        prefEditor.setDay(newDay)
    }

    private fun refreshExcludedWeek() {
        excludedWeekMode =
            if (prefEditor.getWeekMode() == WeekMode.UPPER.ordinal) { WeekMode.LOWER.ordinal
            } else { WeekMode.UPPER.ordinal }
    }

    private suspend fun refreshParas(view: View) {
        dao = ParaRoomDatabase
            .getDatabase(view.context.applicationContext, CoroutineScope(Dispatchers.IO)).paraDao()

        var paras: List<Para> = dao.getDayParas(excludedWeekMode, day, prefEditor.getPodgroupa())

        if (!prefEditor.getMilitary()) { paras = paras.filter { para -> !para.isMilitary } }
        MainScope().launch { recyclerView.apply { adapter = ParaViewAdapter(paras) } }
    }

    companion object {
        private const val FIVE_DAY_WEEK = 5
        private const val SIX_DAY_WEEK = 6
    }
}