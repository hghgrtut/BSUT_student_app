package by.bsut.studapp.timetable.presenter.preferences

import android.content.SharedPreferences
import by.bsut.studapp.preferences.SimplePreferenceEditor
import by.bsut.studapp.timetable.data.Para

class TimetablePreferenceEditor(private val preferences: SharedPreferences) :
    SimplePreferenceEditor(preferences) {
    fun setDay(newDay: Int) { setInt(DAY, newDay) }

    fun getDay(): Int = preferences.getInt(DAY, 0)

    fun setGroup(newGroup: String) { setString(GROUP, newGroup) }

    fun getGroup(): String? = preferences.getString(GROUP, null)

    fun setMilitary(isMilitary: Boolean) { setBoolean(IS_MILITARY, isMilitary) }

    fun getMilitary(): Boolean = preferences.getBoolean(IS_MILITARY, false)

    fun setPodgroupa(newPodgroupa: Int) { setInt(PODGROUPA, newPodgroupa) }

    fun getPodroupa(): Int = preferences.getInt(PODGROUPA, Para.ALL_GROUPS)

    fun setWeekMode(isUpperWeek: Boolean) { setBoolean(IS_UPPER_WEEK, isUpperWeek) }

    fun getWeekMode(): Boolean = preferences.getBoolean(IS_UPPER_WEEK, true)

}