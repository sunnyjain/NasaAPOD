package com.example.nasapod

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.nasapod.detail.ui.APODDetailView
import com.example.nasapod.list.ui.PODListView
import com.example.nasapod.navigation.NavigationCallback
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, NavigationCallback {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private var currentVisibleFragment: Fragment? = null
    var tag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null) {
            tag = savedInstanceState.getString("fragmentLoaded", null)
        }
    }

    override fun onStart() {
        super.onStart()
        if(currentVisibleFragment == null && tag == null) {

            //initial load.
            currentVisibleFragment = PODListView()
            tag = PODListView()::class.java.simpleName
            supportFragmentManager.beginTransaction()
                .add(R.id.mainNavFragment, currentVisibleFragment!!, tag!!)
                .addToBackStack(tag)
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("fragmentLoaded", tag)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount < 1) {
            finish()
        }

        if(supportFragmentManager.backStackEntryCount > 1) {
            tag = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name
            currentVisibleFragment = supportFragmentManager.findFragmentByTag(tag)
        }
    }

    override fun onSelect(id: Int, bundle: Bundle) {
        if(id == 0) {
            //list view.
            currentVisibleFragment = PODListView()
            tag = PODListView()::class.java.simpleName
        } else {
            //detail list view.
            currentVisibleFragment = APODDetailView.newInstance(bundle)
            tag = APODDetailView()::class.java.simpleName
        }
        loadFragment()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainNavFragment, currentVisibleFragment ?: PODListView(), tag ?: PODListView()::class.java.simpleName)
            .addToBackStack(tag)
            .commit()
    }
}
