package geekbrians.slava_5655380.ui.viewmodels.earth.neows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrians.slava_5655380.BuildConfig
import geekbrians.slava_5655380.domain.model.neows.NeowsResponseData
import geekbrians.slava_5655380.domain.model.neows.NeowsRetrofitImpl
import geekbrians.slava_5655380.ui.viewmodels.home.PictureOfTheDayData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NeowsViewModel(
    private val liveDataForViewToObserve: MutableLiveData<NeowsData> = MutableLiveData(),
    private val retrofitImpl: NeowsRetrofitImpl = NeowsRetrofitImpl()
) :
    ViewModel() {

    fun getData(startDate: String, endDate: String): LiveData<NeowsData> {
        sendServerRequest(startDate, endDate)
        return liveDataForViewToObserve
    }

    private fun sendServerRequest(startDate: String, endDate: String) {
        liveDataForViewToObserve.value = NeowsData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getClosestAsteroidsForDatePeriod(startDate, endDate, apiKey).enqueue(object :
                Callback<NeowsResponseData> {
                override fun onResponse(
                    call: Call<NeowsResponseData>,
                    response: Response<NeowsResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            NeowsData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                NeowsData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                NeowsData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<NeowsResponseData>, t: Throwable) {
                    liveDataForViewToObserve.value = NeowsData.Error(t)
                }
            })
        }
    }
}