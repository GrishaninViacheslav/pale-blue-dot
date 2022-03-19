package geekbrians.slava_5655380.ui.fragments.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import geekbrians.slava_5655380.R
import geekbrians.slava_5655380.databinding.DFragmentMainBinding
import geekbrians.slava_5655380.ui.viewmodels.home.PictureOfTheDayData
import geekbrians.slava_5655380.ui.viewmodels.home.PictureOfTheDayViewModel

class HomeFragment(val name: String = "PictureOfTheDayFragment") : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }
    private var _binding: DFragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DFragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(viewLifecycleOwner) { renderData(it) }
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    // TODO
                    //showError("Сообщение, что ссылка пустая")
                } else {
                    // TODO
                    //showSuccess()
                    with(binding) {
                        imageView.load(url) {
                            lifecycle(this@HomeFragment)
                            error(R.drawable.ic_load_error_vector)
                            placeholder(R.drawable.ic_no_photo_vector)
                        }
                        bottomSheetDescriptionHeader.text = serverResponseData.title
                        bottomSheetDescription.text = serverResponseData.explanation
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
                // TODO
            }
            is PictureOfTheDayData.Error -> {
                // TODO
            }
        }
    }
}