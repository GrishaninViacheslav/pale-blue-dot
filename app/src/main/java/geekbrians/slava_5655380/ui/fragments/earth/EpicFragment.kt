package geekbrians.slava_5655380.ui.fragments.earth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import geekbrians.slava_5655380.BuildConfig
import geekbrians.slava_5655380.R
import geekbrians.slava_5655380.databinding.FragmentEpicBinding
import geekbrians.slava_5655380.ui.viewmodels.earth.epic.EpicViewModel
import geekbrians.slava_5655380.ui.viewmodels.earth.epic.EpicData

class EpicFragment(val name: String = "EpicFragment") : Fragment() {

    companion object {
        fun newInstance() = EpicFragment()
    }

    private val viewModel: EpicViewModel by lazy {
        ViewModelProviders.of(this).get(EpicViewModel::class.java)
    }
    private var _binding: FragmentEpicBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(viewLifecycleOwner) { renderData(it) }
    }

    private fun renderData(data: EpicData) {
        when (data) {
            is EpicData.Success -> {
                val serverResponseData = data.serverResponseData.last()
                val dateString = serverResponseData.date
                val year = dateString.substring(0, 4)
                val month = dateString.substring(5, 7)
                val day = dateString.substring(8, 10)
                val url =  "https://api.nasa.gov/EPIC/archive/natural/$year/$month/$day/png/${serverResponseData.image}.png?api_key=${BuildConfig.NASA_API_KEY}"
                if (url.isNullOrEmpty()) {
                    // TODO
                    //showError("Сообщение, что ссылка пустая")
                } else {
                    // TODO
                    //showSuccess()
                    with(binding) {
                        imageView.load(url) {
                            lifecycle(this@EpicFragment)
                            error(R.drawable.ic_load_error_vector)
                            placeholder(R.drawable.ic_no_photo_vector)
                        }
                    }
                }
            }
            is EpicData.Loading -> {
                // TODO
            }
            is EpicData.Error -> {
                // TODO
            }
        }
    }
}