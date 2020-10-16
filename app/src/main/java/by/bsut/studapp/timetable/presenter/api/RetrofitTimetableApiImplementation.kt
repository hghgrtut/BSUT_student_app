package by.bsut.studapp.timetable.presenter.api

import by.bsut.studapp.constants.DATA_GITHUB_URL
import by.bsut.studapp.timetable.data.TimetableApiData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitTimetableApiImplementation : TimetableApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(DATA_GITHUB_URL)
        .build()

    private val apiService = retrofit.create(RetrofitTimetableApi::class.java)

    override suspend fun getListOfParas(group: String, locale: String): TimetableApiData? {
        try {
            val response = apiService.getListOfParas(group, locale)
            if (!response.isSuccessful) throw IllegalStateException(response.errorBody().toString())
            return response.body()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        return null
    }
}