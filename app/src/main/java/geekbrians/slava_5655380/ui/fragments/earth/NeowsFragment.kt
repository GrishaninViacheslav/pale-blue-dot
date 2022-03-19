package geekbrians.slava_5655380.ui.fragments.earth

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import geekbrians.slava_5655380.BuildConfig
import geekbrians.slava_5655380.R
import geekbrians.slava_5655380.databinding.FragmentEpicBinding
import geekbrians.slava_5655380.databinding.FragmentNeowsBinding
import geekbrians.slava_5655380.ui.viewmodels.earth.epic.EpicData
import geekbrians.slava_5655380.ui.viewmodels.earth.epic.EpicViewModel
import geekbrians.slava_5655380.ui.viewmodels.earth.neows.NeowsData
import geekbrians.slava_5655380.ui.viewmodels.earth.neows.NeowsViewModel
import java.text.SimpleDateFormat
import java.util.*

class NeowsFragment(val name: String = "NeowsFragment") : Fragment() {

    companion object {
        fun newInstance() = NeowsFragment()
    }

    private val viewModel: NeowsViewModel by lazy {
        ViewModelProviders.of(this).get(NeowsViewModel::class.java)
    }
    private var _binding: FragmentNeowsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNeowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val currDate = Calendar.getInstance()
        val currDateString = SimpleDateFormat("yyyy-MM-dd").format(currDate.time)
        currDate.add(Calendar.DAY_OF_YEAR, 7)
        val nextWeekDateString = SimpleDateFormat("yyyy-MM-dd").format(currDate.time)
        viewModel.getData(currDateString, nextWeekDateString)
            .observe(viewLifecycleOwner) { renderData(it) }
    }

    private fun renderData(data: NeowsData) {
        when (data) {
            is NeowsData.Success -> {
                val currDate = Calendar.getInstance()
                val currDateString = SimpleDateFormat("yyyy-MM-dd").format(currDate.time)
                val neoData = data.serverResponseData.near_earth_objects[currDateString]?.last()
                Log.d("[MYLOG]", "neoData: ${neoData}")
                if (neoData != null) {
                    binding.asteroidPreviewItem.text = neoData.name
                }
            }
            is NeowsData.Loading -> {
                Log.d("[MYLOG]", "NeowsData.Loading")
                // TODO
            }
            is NeowsData.Error -> {
                Log.d("[MYLOG]", "NeowsData.Error: ${data.error}")
                // TODO
            }
        }
    }
}