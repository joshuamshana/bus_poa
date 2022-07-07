package com.example.bus_poa

import android.app.Application
import com.mazenrashed.printooth.Printooth

class Z91App: Application() {
    override fun onCreate() {
        Printooth.init(this)
        super.onCreate()
    }
}