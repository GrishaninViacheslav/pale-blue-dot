package geekbrians.slava_5655380.domain.model.apod

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodAPI {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<ApodServerResponseData>
}