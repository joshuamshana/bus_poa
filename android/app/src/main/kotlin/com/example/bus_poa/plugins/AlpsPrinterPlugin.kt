package com.example.bus_poa.plugins

//
//import android.content.Context
//import android.os.Build
//import android.util.Log
//import io.flutter.embedding.engine.plugins.FlutterPlugin
//import io.flutter.plugin.common.*
//
//class AlpsPrinterPlugin : MethodChannel.MethodCallHandler, FlutterPlugin {
//    private var applicationContext: Context? = null
//    private var methodChannel: MethodChannel? = null
//    private var eventChannel: EventChannel? = null
//
//    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
//        onAttachedToEngine(binding.applicationContext, binding.binaryMessenger)
//    }
//
//    private fun onAttachedToEngine(applicationContext: Context, messenger: BinaryMessenger) {
//        this.applicationContext = applicationContext
//        methodChannel = MethodChannel(messenger, "com.smartstock/printer")
//        eventChannel = EventChannel(messenger, "com.smartstock/printing")
//        methodChannel!!.setMethodCallHandler(this)
//    }
//
//    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
//        applicationContext = null
//        methodChannel!!.setMethodCallHandler(null)
//        methodChannel = null
//        eventChannel!!.setStreamHandler(null)
//        eventChannel = null
//    }
//
//    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
//        if (call.method == "print") {
//            try {
//                print(call, result)
//            } catch (e: Exception) {
//                result.error("FAILS TO PRINT", "error while printing", e)
//            }
//        } else {
//            result.notImplemented()
//        }
//    }
//
//    fun print(call: MethodCall, result: MethodChannel.Result) {
//        Log.e("Device+++Model", Build.MODEL)
//        val data: String = call.argument<String>("data")!!
//        // val id: String = call.getString("id", UUID.randomUUID().toString())
//        val qr: String = call.argument<String>("qr")!!
//        when {
//            Build.MODEL.trim { it <= ' ' } == "JZV3" -> {
////            JZV3Printer.getInstance().print(applicationContext, object : JZV3PrinterCallback {
////                override fun onReadToPrint() {
////                    PrinterApi.PrnClrBuff_Api()
////                    if (qr != null && qr.isNotEmpty() && qr.isNotBlank()) {
////                        PrinterApi.printAddQrCode_Api(1, 150, qr)
////                    }
////                    PrinterApi.printFeedLine_Api(10)
////                    PrinterApi.PrnFontSet_Api(24, 24, 0)
////                    // PrinterApi.PrnSetGray_Api(30);
////                    PrinterApi.PrnLineSpaceSet_Api(5.toShort(), 0)
////                    PrinterApi.PrnStr_Api(data)
////                }
////
////                override fun onError(printerError: PrinterError) {
////                    result.error("FAILS TO PRINT", printerError.toString(), null)
////                }
////
////                override fun onSuccess() {
////                    result.success("Done printing")
////                }
////            })
//            }
//            Build.MODEL.trim { it <= ' ' } == "Q2" -> {
//                AlpsPrinter.getInstance().print(applicationContext, object : AlpsPrinterCallback {
//                    override fun onSuccess() {
//                        result.success("Done Printing")
//                    }
//
//                    override fun onReadToPrint(printfManager: PrintfManager?) {
//                        printfManager!!.printText(data)
//                        printfManager.printfWrap(3)
//                    }
//
//                    override fun onError(printerError: Throwable?) {
//                        Log.e("***alps pos********", "Not Printed")
//                        result.error("Printer", printerError.toString(), printerError)
//                    }
//
//                })
//            }
//            else -> {
//                result.success("printer not supported")
//            }
//        }
//    }
//
//
//    companion object {
//        /** Plugin registration.  */
//        fun registerWith(registrar: PluginRegistry.Registrar) {
//            val instance = AlpsPrinterPlugin();
//            instance.onAttachedToEngine(registrar.context(), registrar.messenger())
//        }
//    }
//}