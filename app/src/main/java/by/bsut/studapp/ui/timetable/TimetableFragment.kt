package by.bsut.studapp.ui.timetable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.bsut.studapp.R
import by.bsut.studapp.timetable.presenter.database.ParaRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class TimetableFragment : Fragment(R.layout.fragment_timetable) {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = ParaRoomDatabase
            .getDatabase(view.context.applicationContext, CoroutineScope(Dispatchers.IO)).paraDao()
        layoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById<RecyclerView>(R.id.paras_list).apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
        }
    }
}