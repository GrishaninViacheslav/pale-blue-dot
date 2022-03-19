package geekbrians.slava_5655380.domain.model.epic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EpicAPI {

    @GET("EPIC/api/natural")
    fun getNatural(@Query("api_key") apiKey: String): Call<List<EpicResponseData>>
}