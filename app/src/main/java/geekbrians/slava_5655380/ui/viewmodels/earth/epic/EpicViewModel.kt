package geekbrians.slava_5655380.ui.viewmodels.earth.epic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrians.slava_5655380.BuildConfig
import geekbrians.slava_5655380.domain.model.epic.EpicResponseData
import geekbrians.slava_5655380.domain.model.epic.EpicRetrofitImpl
import geekbrians.slava_5655380.ui.viewmodels.home.PictureOfTheDayData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpicViewModel(
    private val liveDataForViewToObserve: MutableLiveData<EpicData> = MutableLiveData(),
    private val retrofitImpl: EpicRetrofitImpl = EpicRetrofitImpl()
) :
    ViewModel() {

    fun getData(): LiveData<EpicData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = EpicData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getNatural(apiKey).enqueue(object :
                Callback<List<EpicResponseData>> {
                override fun onResponse(
                    call: Call<List<EpicResponseData>>,
                    response: Response<List<EpicResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            EpicData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                EpicData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                EpicData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<List<EpicResponseData>>, t: Throwable) {
                    liveDataForViewToObserve.value = EpicData.Error(t)
                }
            })
        }
    }
}