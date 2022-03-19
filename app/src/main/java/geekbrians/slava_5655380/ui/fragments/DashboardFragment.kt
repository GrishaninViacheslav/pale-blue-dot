package geekbrians.slava_5655380.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import geekbrians.slava_5655380.R
import geekbrians.slava_5655380.TimberLogger

class DashboardFragment(val childDashboardFragments: ArrayList<Pair<String, Fragment>>) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        val adapter = GooglePlusFragmentPageAdapter(
            childFragmentManager, requireArguments().getString(ARG_NAME)
        )
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = adapter.count - 1
        val tabLayout: TabLayout = view.findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        lifecycle.addObserver(TimberLogger(this))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.send, menu)
    }

    private inner class GooglePlusFragmentPageAdapter(fm: FragmentManager?, private val name: String?) :
        FragmentPagerAdapter(
            fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
        override fun getItem(position: Int): Fragment {
            return childDashboardFragments[position].second
        }

        override fun getCount(): Int {
            return childDashboardFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return childDashboardFragments[position].first
        }
    }

    companion object {
        private const val ARG_NAME = "arg_name"
        @JvmStatic
        fun newInstance(name: String?, childDashboardFragments: ArrayList<Pair<String, Fragment>>): DashboardFragment {
            val bundle = Bundle()
            bundle.putString(ARG_NAME, name)
            val dashboardFragment = DashboardFragment(childDashboardFragments)
            dashboardFragment.arguments = bundle
            return dashboardFragment
        }
    }
}