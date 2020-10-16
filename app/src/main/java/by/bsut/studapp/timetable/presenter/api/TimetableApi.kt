package by.bsut.studapp.timetable.presenter.api

import by.bsut.studapp.timetable.data.TimetableApiData

interface TimetableApi {
    suspend fun getListOfParas(group: String, locale: String = "ru"): TimetableApiData?
}