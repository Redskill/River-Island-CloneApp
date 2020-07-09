package com.redskill.riverislandapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.redskill.riverislandapp.activities.ProductCategorySelection
import com.redskill.riverislandapp.domain.ProductInfo
import com.redskill.riverislandapp.navbar.fragments.FavoriteFragment
import com.redskill.riverislandapp.navbar.fragments.HomeFragment
import com.redskill.riverislandapp.navbar.fragments.ShoppingFragment
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title="River Island"

        val homeFragment = HomeFragment()
        val favoriteFragment = FavoriteFragment()
        val shoppingFragment = ShoppingFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_favorite -> makeCurrentFragment(favoriteFragment)
                R.id.ic_shopping -> makeCurrentFragment(shoppingFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
}

