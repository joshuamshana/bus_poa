import 'package:bfastui/bfastui.dart';
import 'package:bus_poa/modules/receipts/states/receipts.state.dart';
import 'package:bus_poa/shared/config.dart';
import 'package:flutter/material.dart';

Widget printReceiptButton(BuildContext context) {
  return Container(
    margin:
        EdgeInsets.fromLTRB(0, MediaQuery.of(context).size.height / 3, 0, 0),
    width: MediaQuery.of(context).size.width / 1.3,
    height: 40,
    child: RaisedButton(
      // elevation: 8,
      color: Colors.green,
      onPressed: () {
        print("Printing Receipt");
      },
      child: Center(
        child: Text(
          "Print Receipt",
          style: TextStyle(color: Colors.white, fontSize: 20),
        ),
      ),
    ),
  );
}

Widget submitReceipt(BuildContext context) {
  return BFastUI.component()
      .consumer<ReceiptsState>((context, receiptsState) => Container(
            margin: EdgeInsets.fromLTRB(0, 90, 0, 0),
            width: MediaQuery.of(context).size.width / 1.5,
            height: 40,
            child: RaisedButton(
              // elevation: 8,
              color: Colors.green,
              onPressed: () {
                receiptsState.fetchReceiptAndPrint(context);
              },
              child: Center(
                child: Text(
                  "PRINT RECEIPT",
                  style: TextStyle(color: Colors.white, fontSize: 20),
                ),
              ),
            ),
          ));
}

Widget receiptsForm() {
  return BFastUI.component().consumer<ReceiptsState>((context, receiptsState) =>
      Container(
          margin: EdgeInsets.fromLTRB(
              0, MediaQuery.of(context).size.height / 15, 0, 0),
          padding: EdgeInsets.all(15),
          child: Form(
              child: Center(
                  child: Column(
            children: [
              Center(
                  child: Container(
                      margin: EdgeInsets.fromLTRB(0, 0, 0, 50),
                      child: Text("BUS POA",
                          style: TextStyle(
                            color: Config.primaryColor,
                            fontSize: 45,
                          )))),
              receiptsFormItem(
                  iconData: Icons.person,
                  title: "Receipt Number",
                  textController:
                      receiptsState.textFieldControllers["receiptNo"]),
              submitReceipt(context),
              Container(
                margin: EdgeInsets.fromLTRB(0, 60, 0, 0),
                  child:
                      receiptsState.loading ? CircularProgressIndicator(backgroundColor: Colors.green,) : null)
            ],
          )))));
}

Widget receiptsFormItem(
    {IconData iconData, String title, TextEditingController textController}) {
  return Padding(
      padding: EdgeInsets.fromLTRB(0, 15, 0, 15),
      child: ListTile(
          leading: Icon(
            iconData,
            size: 40,
            color: Config.primaryColor,
          ),
          title: Text(
            title,
            style: TextStyle(
              fontSize: 20,
              fontWeight: FontWeight.bold,
            ),
          ),
          subtitle: TextFormField(
            controller: textController,
          )));
}
