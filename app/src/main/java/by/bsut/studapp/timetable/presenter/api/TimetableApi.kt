package by.bsut.studapp.timetable.presenter.api

import by.bsut.studapp.timetable.data.Para

interface TimetableApi {
    suspend fun getListOfParas(group: String, locale: String = "ru"): List<Para>?
}