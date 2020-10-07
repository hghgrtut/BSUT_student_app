package by.bsut.studapp.timetable.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsut.studapp.timetable.data.constants.AUDITORY
import by.bsut.studapp.timetable.data.constants.PARA_EXTRA_INFO
import by.bsut.studapp.timetable.data.constants.PARA_ID
import by.bsut.studapp.timetable.data.constants.SUBJECT
import by.bsut.studapp.timetable.data.constants.TABLE_PARAS
import by.bsut.studapp.timetable.data.constants.TEACHER
import by.bsut.studapp.timetable.data.constants.TYPE

@Entity(tableName = TABLE_PARAS)
data class Para(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = PARA_ID) val paraId: Int,
    @ColumnInfo(name = TEACHER) val teacher: String,
    @ColumnInfo(name = TYPE) val type: Int,
    @ColumnInfo(name = AUDITORY) val auditory: Int,
    @ColumnInfo(name = SUBJECT) val subject: String,
    @ColumnInfo(name = PARA_EXTRA_INFO) val extraInfo: String? = null
)