package com.example.bus_poa.plugins

import android.content.Context
import android.os.Build
import android.util.Log
import com.example.bus_poa.Z91Printer
import com.fahamutech.z91smartpo.Z91PrinterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.PluginRegistry.Registrar

internal class Z91PrinterClientPlugin : MethodCallHandler, FlutterPlugin {
    private var applicationContext: Context? = null
    private var methodChannel: MethodChannel? = null
    private var eventChannel: EventChannel? = null

    override fun onAttachedToEngine(binding: FlutterPluginBinding) {
        onAttachedToEngine(binding.applicationContext, binding.binaryMessenger)
    }

    private fun onAttachedToEngine(applicationContext: Context, messenger: BinaryMessenger) {
        this.applicationContext = applicationContext
        methodChannel = MethodChannel(messenger, "com.smartstock/printer")
        eventChannel = EventChannel(messenger, "com.smartstock/printing")
        methodChannel!!.setMethodCallHandler(this)
    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
        applicationContext = null
        methodChannel!!.setMethodCallHandler(null)
        methodChannel = null
        eventChannel!!.setStreamHandler(null)
        eventChannel = null
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "print") {
            try {
                print(call,result)
            } catch (e: Exception) {
                result.error("FAILS TO PRINT","error while printing",e)
            }
        } else {
            result.notImplemented()
        }
    }

    fun print(call: MethodCall, result: MethodChannel.Result) {
        Log.e("Device+++Model", Build.MODEL)

        val data: String = call.argument<String>("data")!!
        Z91Printer.printText(data)

        val qr: String = call.argument<String>("qr")!!
        Log.i("**** QR ***", qr.toString());
        Z91Printer.printQr(qr);
        Z91Printer.printText("\n");
        result.success("ok");
    }

    companion object {
        /** Plugin registration.  */
        fun registerWith(registrar: Registrar) {
            val instance = Z91PrinterClientPlugin();
            instance.onAttachedToEngine(registrar.context(), registrar.messenger())
        }
    }
}