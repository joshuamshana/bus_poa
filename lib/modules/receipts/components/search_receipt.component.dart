import 'package:bfastui/bfastui.dart';
import 'package:bus_poa/modules/receipts/states/receipts.state.dart';
import 'package:bus_poa/shared/config.dart';
import 'package:flutter/material.dart';

Widget submitReceipt(BuildContext context) => BFastUI.component()
    .consumer<ReceiptsState>((context, receiptsState) => Container(
        padding: EdgeInsets.fromLTRB(0, 24, 0, 24),
        width: MediaQuery.of(context).size.width,
        // height: 40,
        child: receiptsState.loading
            ? Center(
                child: CircularProgressIndicator(backgroundColor: Colors.white))
            : Container(
                height: 48,
                child: TextButton(
                    // color: Colors.green,
                    style: ButtonStyle(
                        backgroundColor:
                            MaterialStateProperty.all(Colors.green)),
                    onPressed: () {
                      receiptsState.fetchReceiptAndPrint(context);
                    },
                    child: Text("PRINT RECEIPT",
                        style: TextStyle(color: Colors.white, fontSize: 20))),
              )));

Widget receiptsForm() => BFastUI.component()
    .consumer<ReceiptsState>((context, receiptsState) => Container(
        padding: EdgeInsets.all(15),
        child: Center(
            child: Column(children: [
          Container(
              padding: EdgeInsets.fromLTRB(0, 0, 0, 0),
              child: Text("BUS POA",
                  style: TextStyle(color: Config.primaryColor, fontSize: 45))),
          receiptsFormItem(
            title: "Receipt Number",
            textController: receiptsState.textFieldControllers["receiptNo"],
          ),
          submitReceipt(context)
        ]))));

Widget receiptsFormItem({
  IconData iconData,
  String title,
  TextEditingController textController,
}) =>
    Padding(
        padding: EdgeInsets.fromLTRB(0, 0, 0, 15),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Padding(
              padding: const EdgeInsets.fromLTRB(0, 8, 0, 8),
              child: Text(
                title,
                style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.bold,
                    color: Colors.white),
              ),
            ),
            Container(
              decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.all(Radius.circular(8))),
              padding: EdgeInsets.all(8),
              child: TextField(
                style: TextStyle(fontSize: 45),
                controller: textController,
                keyboardType: TextInputType.number,
                decoration: InputDecoration(border: InputBorder.none),
              ),
            ),
          ],
        ));
