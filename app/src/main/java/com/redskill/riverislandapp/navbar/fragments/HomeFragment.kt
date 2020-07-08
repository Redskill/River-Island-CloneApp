package com.redskill.riverislandapp.navbar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView

import com.redskill.riverislandapp.R

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val search_bar = view?.findViewById<SearchView>(R.id.search_bar)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
