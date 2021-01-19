package com.example.bus_poa

import android.app.Application

class Z91App: Application() {
    override fun onCreate() {
        super.onCreate()
        Z91Printer.init();
    }
}