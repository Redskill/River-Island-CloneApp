package com.redskill.riverislandapp.navbar.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.annotation.MainThread
import com.redskill.riverislandapp.MainActivity

import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.activities.ProductCategorySelection

/**
 * A simple [Fragment] subclass.
 */
class ShoppingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_shopping, container, false)
        val shop_now_button = view?.findViewById<Button>(R.id.shop_now_button)

        shop_now_button?.setOnClickListener {
            val intent = Intent(activity, ProductCategorySelection::class.java)
            startActivity(intent)

        }
        // Inflate the layout for this fragment
        return view

    }
}
