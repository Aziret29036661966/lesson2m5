package com.example.lesson2m5.ui.acitvity.main

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson2m5.App

import com.example.lesson2m5.databinding.ActivityMainBinding

import com.example.lesson2m5.model.entity.User
import com.example.lesson2m5.model.entity.UserListener
import com.example.lesson2m5.model.entity.UserService
import com.example.lesson2m5.ui.*
import com.example.lesson2m5.ui.acitvity.main.adapter.Adapter
import com.example.lesson2m5.ui.acitvity.main.adapter.UserActionListener


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var adapterRV:Adapter

    private val userService: UserService get() = (applicationContext as App).userService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initAdapter()
    }

    private fun initAdapter() {
        adapterRV = Adapter(object : UserActionListener{
            override fun onUserDelete(user: User) {
                val listener = DialogInterface.OnClickListener { dialog, which ->
                    when(which){
                        DialogInterface.BUTTON_POSITIVE -> userService.deleteUsers(user)
                    }
                }
                dialogShow(listener)
            }

        })
        val layoutManager = LinearLayoutManager(this)
        binding.rvRc.layoutManager = layoutManager
        binding.rvRc.adapter = adapterRV
        userService.addListener(userListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        userService.removeListener(userListener)
    }

    private val userListener: UserListener= {
        adapterRV.users = it
    }
}