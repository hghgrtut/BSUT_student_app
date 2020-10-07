package by.bsut.studapp.timetable.presenter.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.bsut.studapp.timetable.data.Para
import by.bsut.studapp.timetable.data.constants.PARA_ID
import by.bsut.studapp.timetable.data.constants.TABLE_PARAS

@Dao
interface ParaDao {
    @Query("SELECT * FROM $TABLE_PARAS")
    fun getAllParas(): List<Para>

    @Query("SELECT * FROM $TABLE_PARAS WHERE $PARA_ID BETWEEN 0 AND 199")
    fun getUpperWeekParas(): List<Para>

    @Query("SELECT * FROM $TABLE_PARAS WHERE $PARA_ID NOT BETWEEN 100 AND 199")
    fun getLowerWeekParas(): List<Para>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(para: Para)

    @Query("DELETE FROM $TABLE_PARAS")
    fun dropTable()
}