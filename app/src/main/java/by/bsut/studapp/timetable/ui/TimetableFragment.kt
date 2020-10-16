package by.bsut.studapp.timetable.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.bsut.studapp.R
import by.bsut.studapp.timetable.data.enums.WeekMode
import by.bsut.studapp.timetable.data.objects.Para
import by.bsut.studapp.timetable.presenter.dao.ParaDao
import by.bsut.studapp.timetable.presenter.database.ParaRoomDatabase
import by.bsut.studapp.timetable.presenter.preferences.PREFERENCES
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
    private var paras: List<Para>? = null
    private var excludedWeekMode: Int = 1
    private var day: Int = 1
    private lateinit var prefEditor: TimetablePreferenceEditor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefEditor = TimetablePreferenceEditor(
            view.context.applicationContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE))

        layoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById<RecyclerView>(R.id.paras_list).apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
        }

        excludedWeekMode =
            if (prefEditor.getWeekMode() == WeekMode.UPPER.ordinal) { WeekMode.LOWER.ordinal
            } else { WeekMode.UPPER.ordinal }
        day = prefEditor.getDay()

        CoroutineScope(Dispatchers.IO).launch { refreshParas(view) }
    }

    private fun refreshParas(view: View) {
        dao = ParaRoomDatabase
            .getDatabase(view.context.applicationContext, CoroutineScope(Dispatchers.IO)).paraDao()

        paras = dao.getDayParas(excludedWeekMode, day)

        if (paras == null) throw IllegalStateException("paras list not initialized!!!")

        val podgroupa = prefEditor.getPodroupa()
        val isMilitary = prefEditor.getMilitary()
        val todayParas = paras!!.filter { para ->
            (!para.isMilitary || isMilitary) &&
                (para.podgroupa == Para.ALL_GROUPS || para.podgroupa == podgroupa)
        }
        MainScope().launch { recyclerView.apply { adapter = ParaViewAdapter(todayParas) } }
    }
}