package by.bsut.studapp.timetable.presenter.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.bsut.studapp.timetable.data.Para
import by.bsut.studapp.timetable.data.constants.TABLE_PARAS

@Dao
interface ParaDao {
    @Query("SELECT * FROM $TABLE_PARAS")
    fun getAllParas(): List<Para>

    @Query("SELECT * FROM $TABLE_PARAS")
    fun getUpperWeekParas(): List<Para>

    @Query("SELECT * FROM $TABLE_PARAS")
    fun getLowerWeekParas(): List<Para>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(para: Para)

    @Query("DELETE FROM $TABLE_PARAS")
    fun dropTable()
}