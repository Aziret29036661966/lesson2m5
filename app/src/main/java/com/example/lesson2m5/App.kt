package com.example.lesson2m5

import android.app.Application
import com.example.lesson2m5.model.entity.UserService

class App : Application(){
    val userService = UserService()
}