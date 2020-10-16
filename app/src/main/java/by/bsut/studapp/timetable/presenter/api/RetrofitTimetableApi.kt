package by.bsut.studapp.timetable.presenter.api

import by.bsut.studapp.timetable.data.TimetableApiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitTimetableApi {
    @GET("timetable/{locale}/{group}.json")
    suspend fun getListOfParas(
        @Path("group") group: String,
        @Path("locale") locale: String = "ru"
    ): Response<TimetableApiData>
}