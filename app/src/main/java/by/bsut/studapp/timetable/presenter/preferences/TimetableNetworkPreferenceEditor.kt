package by.bsut.studapp.timetable.presenter.preferences

import android.content.SharedPreferences
import by.bsut.studapp.preferences.SimplePreferenceEditor

class TimetableNetworkPreferenceEditor(private val preferences: SharedPreferences) :
    SimplePreferenceEditor(preferences) {
    fun getTimetableVersion(): Int = preferences.getInt(TIMETABLE_VERSION, 0)
    fun putTimetableVersion(version: Int) { setInt(TIMETABLE_VERSION, version) }
    fun setGroup(newGroup: String) { setString(GROUP, newGroup) }
    fun getGroup(): String? = preferences.getString(GROUP, "HI21")
}