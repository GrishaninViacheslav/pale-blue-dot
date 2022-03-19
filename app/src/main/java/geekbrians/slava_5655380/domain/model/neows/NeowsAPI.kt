package geekbrians.slava_5655380.domain.model.neows

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NeowsAPI {

    @GET("neo/rest/v1/feed")
    fun getClosestAsteroidsForDatePeriod(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): Call<NeowsResponseData>
}