import 'package:bfastui/adapters/state.adapter.dart';
import 'package:bus_poa/modules/receipts/services/receipts.service.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class ReceiptsState extends StateAdapter {
  final receiptsService = ReceiptsService();
  var loading = false;

  final textFieldControllers = {
    "receiptNo": TextEditingController(),
  };

  fetchReceiptAndPrint(BuildContext context) async {
    this.loading = true;
    var receipt = textFieldControllers["receiptNo"].text;

    if (receipt != null) {
      receiptsService.fetchReceiptDetails(receipt).then((value) {

        this.loading = false;
        receiptsService.printReceipt(value);
        if (value["id_no"] == null) {
          showError(context);
        }
        
      }).catchError((err) {
        print(err);
        this.loading = false;
        showError(context);
      });
    }
  }

  showError(BuildContext context) {
    ScaffoldMessenger.maybeOf(context).showSnackBar(const SnackBar(
      content: Text('Failed to get receipt details'),
    ));
  }
}
