package by.bsut.studapp.timetable.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsut.studapp.timetable.data.constants.AUDITORY
import by.bsut.studapp.timetable.data.constants.MILITARY
import by.bsut.studapp.timetable.data.constants.PARA_DAY_OF_WEEK
import by.bsut.studapp.timetable.data.constants.PARA_EXTRA_INFO
import by.bsut.studapp.timetable.data.constants.PARA_ID
import by.bsut.studapp.timetable.data.constants.PARA_NUMBER
import by.bsut.studapp.timetable.data.constants.PODGROUPA
import by.bsut.studapp.timetable.data.constants.SUBJECT
import by.bsut.studapp.timetable.data.constants.TABLE_PARAS
import by.bsut.studapp.timetable.data.constants.TEACHER
import by.bsut.studapp.timetable.data.constants.TYPE
import by.bsut.studapp.timetable.data.constants.WEEK_MODE

@Entity(tableName = TABLE_PARAS)
data class Para(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = PARA_ID) val id: Int? = null,
    @ColumnInfo(name = PARA_DAY_OF_WEEK) val weekDay: Int,
    @ColumnInfo(name = PARA_NUMBER) val number: Int,
    @ColumnInfo(name = TEACHER) val teacher: String,
    @ColumnInfo(name = TYPE) val type: Int,
    @ColumnInfo(name = AUDITORY) val auditory: Int,
    @ColumnInfo(name = SUBJECT) val subject: String,
    @ColumnInfo(name = WEEK_MODE) val weekMode: Int = ALL_WEEKS,
    @ColumnInfo(name = PARA_EXTRA_INFO) val extraInfo: String? = null,
    @ColumnInfo(name = MILITARY) val isMilitary: Boolean = false,
    @ColumnInfo(name = PODGROUPA) val podgroupa: Int = ALL_GROUPS
) {
    companion object {
        const val ALL_GROUPS = 0
        const val ALL_WEEKS = 0
        const val UPPER_WEEK = 1
        const val LOWER_WEEK = 2
    }
}