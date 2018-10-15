package com.aranteknoloji.ktroom.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.aranteknoloji.ktroom.ListFragmentViewModel
import com.aranteknoloji.ktroom.R
import com.aranteknoloji.ktroom.db.User

class AddUserFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)
        val viewModel = ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)

        val firstname: EditText = view.findViewById(R.id.name_edittext)
        val lastname: EditText = view.findViewById(R.id.last_name_edittext)
        val adduser: Button = view.findViewById(R.id.add_user)
        adduser.setOnClickListener {
            if (!TextUtils.isEmpty(firstname.text.toString()) && !TextUtils.isEmpty(lastname.text.toString())) {
                val user = User(null, firstname.text.toString(), lastname.text.toString())
                viewModel.insertUser(user)
                popStack()
            }
        }


        return view
    }

    fun popStack() {
        if (activity!!.supportFragmentManager.backStackEntryCount > 0) {
            activity!!.supportFragmentManager.popBackStack()
        }
    }
}