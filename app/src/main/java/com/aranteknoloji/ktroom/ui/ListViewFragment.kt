package com.aranteknoloji.ktroom.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.aranteknoloji.ktroom.ListFragmentViewModel
import com.aranteknoloji.ktroom.R
import com.aranteknoloji.ktroom.adapters.UsersAdapter

class ListViewFragment: Fragment() {

//    private lateinit var adapter: UsersAdapter
    private val TAG = ListViewFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_listview, container, false)

        val adapter = UsersAdapter()
        val viewModel = ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
        viewModel.user().observe(this, Observer {
            adapter.users = it!!
            adapter.notifyDataSetChanged()
            Log.w(TAG, "fragment observe runs")
        })

        val recyclerList: RecyclerView = view.findViewById(R.id.recycler_list_allusers)
        recyclerList.layoutManager = LinearLayoutManager(context)
        recyclerList.adapter = adapter

        val actionBtn: FloatingActionButton = view.findViewById(R.id.add_user_btn)
        actionBtn.setOnClickListener {
//            viewModel.insertUser(User(null, "Arda", "Kucukoz"))
            activity!!.supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.go_up, android.R.anim.fade_out,
                            android.R.anim.fade_in, R.anim.go_down)
                    .replace(R.id.main_frame, AddUserFragment())
                    .addToBackStack(null)
                    .commit()
        }

        if (activity!!.findViewById<FrameLayout>(R.id.main_frame) == null) actionBtn.visibility = View.GONE

        return view
    }
}