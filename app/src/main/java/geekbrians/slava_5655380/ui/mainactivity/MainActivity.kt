package geekbrians.slava_5655380.ui.mainactivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import geekbrians.slava_5655380.R
import geekbrians.slava_5655380.ui.mainactivity.listeners.BottomNavItemSelectedListener
import geekbrians.slava_5655380.ui.mainactivity.listeners.SideNavItemSelectedListener

class MainActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView
    private lateinit var toolbar: Toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            showSearch()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSearch() {
        Snackbar.make(navigation, "Go to Search", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Geekbrainsandroidmaterialdesign_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val adapter = AppFragmentPageAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = adapter.count - 1
        navigation = findViewById(R.id.navigation)
        val listener = BottomNavItemSelectedListener(viewPager, toolbar)
        navigation.setOnNavigationItemSelectedListener(listener)
        bindNavigationDrawer()
        initTitle()
    }

    private fun initTitle() {
        toolbar.post { toolbar.title = navigation.menu.getItem(0).title }
    }

    private fun bindNavigationDrawer() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val listener = SideNavItemSelectedListener(drawer, navigation)
        navigationView.setNavigationItemSelectedListener(listener)
    }

    fun onFabClicked(view: View?) {
        Snackbar.make(navigation, "Fab Share", Snackbar.LENGTH_SHORT).show()
    }
}