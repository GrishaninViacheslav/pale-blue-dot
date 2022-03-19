package geekbrians.slava_5655380.ui.mainactivity.listeners

import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import geekbrians.slava_5655380.R

class SideNavItemSelectedListener(
    private val drawer: DrawerLayout,
    private val navigation: BottomNavigationView?
) : NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_tool) {
            showToolSnackBar()
        } else if (id == R.id.nav_share) {
            showShareSnackBar()
        } else if (id == R.id.nav_gallery) {
            showGallerySnackBar()
        } else if (id == R.id.nav_send) {
            showSendSnackBar()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showSendSnackBar() {
        Snackbar.make(navigation!!, "Send", Snackbar.LENGTH_SHORT).show()
    }

    private fun showGallerySnackBar() {
        Snackbar.make(navigation!!, "Gallery", Snackbar.LENGTH_SHORT).show()
    }

    private fun showToolSnackBar() {
        Snackbar.make(navigation!!, "Настройки", Snackbar.LENGTH_SHORT).show()
    }

    private fun showShareSnackBar() {
        Snackbar.make(navigation!!, "Share", Snackbar.LENGTH_SHORT).show()
    }
}