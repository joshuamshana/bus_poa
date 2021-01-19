package com.example.bus_poa

import com.fahamutech.z91smartpo.Z91PrinterPlugin

object Z91Printer{
    val printer = Z91PrinterPlugin()

    fun init(){
        printer.init()
    }

    fun printText(data: String){
        printer.printText(data)
    }

    fun printQr(qr: String){
        printer.printQr(qr)
    }

}