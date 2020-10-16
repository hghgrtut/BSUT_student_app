package by.bsut.studapp.timetable.presenter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.bsut.studapp.timetable.data.constants.TABLE_PARAS
import by.bsut.studapp.timetable.data.objects.Para
import by.bsut.studapp.timetable.presenter.dao.ParaDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Para::class], version = 1)
abstract class ParaRoomDatabase : RoomDatabase() {
    abstract fun paraDao(): ParaDao

    companion object {
        @Volatile
        private var INSTANCE: ParaRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ParaRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ParaRoomDatabase::class.java, TABLE_PARAS)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}