package com.example.bus_poa.plugins

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Build.*
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

internal class Z91PrinterClientPlugin(private val activity: Activity) : MethodCallHandler,
    FlutterPlugin {
    private val PERMISSION_BLUETOOTH = 12222;
    private val PERMISSION_BLUETOOTH_ADMIN = 432222;
    private val PERMISSION_BLUETOOTH_CONNECT = 43989922;
    private val PERMISSION_BLUETOOTH_SCAN = 4388722;

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
                print(call, result)
            } catch (e: Exception) {
                result.error("FAILS TO PRINT", "error while printing", e)
            }
        } else {
            result.notImplemented()
        }
    }

    fun print(call: MethodCall, result: MethodChannel.Result) {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.BLUETOOTH),
                PERMISSION_BLUETOOTH
            )
        } else if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.BLUETOOTH_ADMIN),
                PERMISSION_BLUETOOTH_ADMIN
            );
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                PERMISSION_BLUETOOTH_CONNECT
            );
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.BLUETOOTH_SCAN),
                PERMISSION_BLUETOOTH_SCAN
            );
        } else {
            if (!Printooth.hasPairedPrinter()) {
                startActivityForResult(
                    activity,
                    Intent(activity, ScanningActivity::class.java),
                    ScanningActivity.SCANNING_FOR_PRINTER,
                    null
                )
                return
            }
            Log.e("Device+++Model", MODEL)
            var data: String = call.argument<String>("data")!!
            data = data.split("\n").joinToString("\n") { it.trim() }
            val qr: String? = call.argument<String>("qr")
            val barCodeWriter = QRCodeWriter()
            val width = 250
            val height = 250
            val qrImageBitMatrix: BitMatrix =
                barCodeWriter.encode(qr ?: "test", BarcodeFormat.QR_CODE, width, height)
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until width) {
                    bmp.setPixel(x, y, if (qrImageBitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            val qrImage = ImagePrintable.Builder(bmp).setNewLinesAfter(10).build()
            val printables = ArrayList<Printable>()
            val printable = TextPrintable.Builder()
                .setText(data)
                .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                .build()
            printables.add(printable)
            printables.add(qrImage)
            Printooth.printer().print(printables)
        }
        result.success("ok")

//        }
    }
}