package by.bsut.studapp.timetable.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.bsut.studapp.R
import by.bsut.studapp.timetable.data.objects.Para

class ParaViewAdapter(private val paras: List<Para?>) :
    RecyclerView.Adapter<ParaViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val auditory: TextView = view.findViewById(R.id.auditory)
        val subject: TextView = view.findViewById(R.id.subject)
        val teacher: TextView = view.findViewById(R.id.teacher)
        val type: TextView = view.findViewById(R.id.type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_para, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val para = paras[position]
        if (para != null) {
            holder.auditory.text = para.auditory.toString()
            holder.subject.text = para.subject
            holder.teacher.text = para.teacher
            holder.type.text = para.type.toString()
        }
    }

    override fun getItemCount(): Int = paras.size
}