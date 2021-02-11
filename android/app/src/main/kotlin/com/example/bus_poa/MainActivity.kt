package com.example.bus_poa

import androidx.annotation.NonNull
import com.example.bus_poa.plugins.Z91PrinterClientPlugin
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        flutterEngine.plugins.add(Z91PrinterClientPlugin(this))
    }
}
