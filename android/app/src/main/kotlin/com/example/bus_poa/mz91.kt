package com.example.bus_poa

import android.app.Activity
//import com.fahamutech.z91smartpo.Z91PrinterCustomPrintCallback
//import com.fahamutech.z91smartpo.Z91PrinterPlugin
//import com.zcs.sdk.Printer
//import com.zcs.sdk.SdkResult
//import com.zcs.sdk.print.PrnStrFormat

object Z91Printer{
//    val printer = Z91PrinterPlugin()

    fun init(){
//        printer.init()
    }

    fun printText(activity: Activity, data: String){
//        printer.printText(data)
//        printer.customPrint(activity) { activity, printer, prnStrFormat ->
//            prnStrFormat!!.textSize = 30;
//            printer!!.setPrintAppendString("\n", prnStrFormat)
//            printer.setPrintAppendString(data, prnStrFormat)
//            printer.setPrintAppendString("\n", prnStrFormat)

//            val printStatus = printer.setPrintStart()
//            if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
//                //                        homeActivity.runOnUiThread(new Runnable() {
//                //                            @Override
//                //                            public void run() {
//                //                                DialogUtils.show(homeActivity, homeActivity.getString(R.string.printer_out_of_paper));
//                //
//                //                            }
//                //                        });
//            }
        }

    fun printQr(qr: String){
//        printer.printQr(qr)
    }

}