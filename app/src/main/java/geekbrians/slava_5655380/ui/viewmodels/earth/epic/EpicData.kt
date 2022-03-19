package geekbrians.slava_5655380.ui.viewmodels.earth.epic

import geekbrians.slava_5655380.domain.model.epic.EpicResponseData

sealed class EpicData {
    data class Success(val serverResponseData: List<EpicResponseData>) : EpicData()
    data class Error(val error: Throwable) : EpicData()
    data class Loading(val progress: Int?) : EpicData()
}