package by.bsut.studapp.timetable.presenter.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.bsut.studapp.timetable.data.constants.PARA_DAY_OF_WEEK
import by.bsut.studapp.timetable.data.constants.PARA_WEEK_MODE
import by.bsut.studapp.timetable.data.constants.PODGROUPA
import by.bsut.studapp.timetable.data.constants.TABLE_PARAS
import by.bsut.studapp.timetable.data.objects.Para

@Dao
interface ParaDao {
    @Query("SELECT * FROM $TABLE_PARAS")
    fun getAllParas(): List<Para>

    @Query("SELECT * FROM $TABLE_PARAS WHERE $PARA_DAY_OF_WEEK = (:day) " +
            "AND (NOT $PARA_WEEK_MODE = (:excludedWeekMode)) " +
            "AND ($PODGROUPA = 0 OR $PODGROUPA = (:podgroup))")
    fun getDayParas(excludedWeekMode: Int, day: Int, podgroup: Int): List<Para>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(para: Para)

    @Query("DELETE FROM $TABLE_PARAS")
    fun dropTable()
}