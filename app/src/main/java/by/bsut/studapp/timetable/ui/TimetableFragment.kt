package by.bsut.studapp.timetable.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.bsut.studapp.R
import by.bsut.studapp.timetable.constants.HAS_PARAS_ON_SATURDAY
import by.bsut.studapp.timetable.data.enums.WeekDays
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

    private lateinit var dayText: TextView
    private lateinit var weekModeText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayText = view.findViewById(R.id.day)
        weekModeText = view.findViewById(R.id.weekMode)
        val context: Context = view.context

        val prefs: SharedPreferences =
            context.applicationContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        prefEditor = TimetablePreferenceEditor(prefs)
        if (HAS_PARAS_ON_SATURDAY.contains(TimetableNetworkPreferenceEditor(prefs).getGroup())) {
            weekLenght = SIX_DAY_WEEK
        }

        layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById<RecyclerView>(R.id.paras_list).apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
        }

        refreshExcludedWeek()
        refreshDay()

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch { refreshParas(view) }

        view.findViewById<Button>(R.id.modeButton).setOnClickListener {
            prefEditor.setWeekMode(excludedWeekMode)
            refreshExcludedWeek()
            scope.launch { refreshParas(view) }
        }

        view.findViewById<Button>(R.id.previousButton).setOnClickListener {
            prefEditor.setDay(if (day > 0) day - 1 else weekLenght - 1)
            refreshDay()
            scope.launch { refreshParas(view) }
        }

        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            prefEditor.setDay((day + 1) % weekLenght)
            refreshDay()
            scope.launch { refreshParas(view) }
        }
    }

    private fun refreshDay() {
        day = prefEditor.getDay()
        dayText.text = getString(daysOfWeek[day].localization)
    }

    private fun refreshExcludedWeek() {
        if (prefEditor.getWeekMode() == WeekMode.UPPER.ordinal) {
            excludedWeekMode = WeekMode.LOWER.ordinal
            weekModeText.text = getString(R.string.week_upper)
        } else {
            excludedWeekMode = WeekMode.UPPER.ordinal
            weekModeText.text = getString(R.string.week_lower)
        }
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
        private val daysOfWeek: Array<WeekDays> = WeekDays.values()
    }
}