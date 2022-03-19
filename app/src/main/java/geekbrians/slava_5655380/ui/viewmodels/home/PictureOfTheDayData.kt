package geekbrians.slava_5655380.ui.viewmodels.home

import geekbrians.slava_5655380.domain.model.apod.ApodServerResponseData

sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: ApodServerResponseData) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}