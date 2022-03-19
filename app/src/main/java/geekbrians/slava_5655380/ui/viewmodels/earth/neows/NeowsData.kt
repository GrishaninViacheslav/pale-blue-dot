package geekbrians.slava_5655380.ui.viewmodels.earth.neows

import geekbrians.slava_5655380.domain.model.neows.NeowsResponseData

sealed class NeowsData {
    data class Success(val serverResponseData: NeowsResponseData) : NeowsData()
    data class Error(val error: Throwable) : NeowsData()
    data class Loading(val progress: Int?) : NeowsData()
}