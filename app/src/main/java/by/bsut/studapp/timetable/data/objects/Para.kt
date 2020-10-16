package by.bsut.studapp.timetable.data.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsut.studapp.timetable.data.constants.AUDITORY
import by.bsut.studapp.timetable.data.constants.MILITARY
import by.bsut.studapp.timetable.data.constants.PARA_DAY_OF_WEEK
import by.bsut.studapp.timetable.data.constants.PARA_EXTRA_INFO
import by.bsut.studapp.timetable.data.constants.PARA_ID
import by.bsut.studapp.timetable.data.constants.PARA_NUMBER
import by.bsut.studapp.timetable.data.constants.PARA_WEEK_MODE
import by.bsut.studapp.timetable.data.constants.PODGROUPA
import by.bsut.studapp.timetable.data.constants.SUBJECT
import by.bsut.studapp.timetable.data.constants.TABLE_PARAS
import by.bsut.studapp.timetable.data.constants.TEACHER
import by.bsut.studapp.timetable.data.constants.TYPE
import by.bsut.studapp.timetable.data.enums.WeekMode

@Entity(tableName = TABLE_PARAS)
data class Para(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = PARA_ID) val id: Int? = null,
    @ColumnInfo(name = PARA_DAY_OF_WEEK) val weekday: Int,
    @ColumnInfo(name = PARA_NUMBER) val number: Int,
    @ColumnInfo(name = TEACHER) val teacher: String? = null,
    @ColumnInfo(name = TYPE) val type: Int,
    @ColumnInfo(name = AUDITORY) val auditory: String,
    @ColumnInfo(name = SUBJECT) val subject: String,
    @ColumnInfo(name = PARA_WEEK_MODE) val weekMode: Int = WeekMode.ALL.ordinal,
    @ColumnInfo(name = PARA_EXTRA_INFO) val extraInfo: String? = null,
    @ColumnInfo(name = MILITARY) val isMilitary: Boolean = false,
    @ColumnInfo(name = PODGROUPA) val podgroupa: Int = ALL_GROUPS
) { companion object { const val ALL_GROUPS = 0 } }