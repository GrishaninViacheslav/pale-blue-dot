package geekbrians.slava_5655380.ui.mainactivity.listeners

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import geekbrians.slava_5655380.R

class BottomNavItemSelectedListener(
    private val viewPager: ViewPager,
    private val toolbar: Toolbar?
) : BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        toolbar!!.title = item.title
        val itemId = item.itemId
        if (itemId == R.id.navigation_home) {
            viewPager.currentItem = 0
            return true
        } else if (itemId == R.id.navigation_earth) {
            viewPager.currentItem = 1
            return true
        } else if (itemId == R.id.navigation_moon) {
            viewPager.currentItem = 2
            return true
        } else if (itemId == R.id.navigation_mars) {
            viewPager.currentItem = 3
            return true
        } else if (itemId == R.id.navigation_space) {
            viewPager.currentItem = 4
            return true
        }
        return false
    }
}