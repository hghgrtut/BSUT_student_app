package by.bsut.studapp.timetable.data.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsut.studapp.timetable.data.constants.SUBJECT_EXTRA_INFO
import by.bsut.studapp.timetable.data.constants.SUBJECT_NAME
import by.bsut.studapp.timetable.data.constants.TABLE_SUBJECTS

@Entity(tableName = TABLE_SUBJECTS)
data class Subject(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = SUBJECT_NAME) val name: String,
    @ColumnInfo(name = SUBJECT_EXTRA_INFO) val notes: String? = null
)