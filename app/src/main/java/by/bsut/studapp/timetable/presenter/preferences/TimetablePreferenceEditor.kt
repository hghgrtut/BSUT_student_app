package by.bsut.studapp.timetable.presenter.preferences

import android.content.SharedPreferences
import by.bsut.studapp.preferences.SimplePreferenceEditor
import by.bsut.studapp.timetable.data.enums.WeekDays
import by.bsut.studapp.timetable.data.enums.WeekMode
import by.bsut.studapp.timetable.data.objects.Para

class TimetablePreferenceEditor(private val preferences: SharedPreferences) :
    SimplePreferenceEditor(preferences) {
    fun setDay(newDay: Int) { setInt(DAY, newDay) }

    fun getDay(): Int = preferences.getInt(DAY, WeekDays.MONDAY.ordinal)

    fun setMilitary(isMilitary: Boolean) { setBoolean(IS_MILITARY, isMilitary) }

    fun getMilitary(): Boolean = preferences.getBoolean(IS_MILITARY, false)

    fun setPodgroupa(newPodgroupa: Int) { setInt(PODGROUPA, newPodgroupa) }

    fun getPodgroupa(): Int = preferences.getInt(PODGROUPA, Para.ALL_GROUPS)

    fun setWeekMode(weekMode: Int) { setInt(IS_UPPER_WEEK, weekMode) }

    fun getWeekMode(): Int = preferences.getInt(IS_UPPER_WEEK, WeekMode.UPPER.ordinal)
}