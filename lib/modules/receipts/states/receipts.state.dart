import 'package:bfastui/adapters/state.adapter.dart';
import 'package:bus_poa/modules/receipts/services/receipts.service.dart';
import 'package:flutter/material.dart';

class ReceiptsState extends StateAdapter {
  final receiptsService = ReceiptsService();
  var loading = false;

  final textFieldControllers = {
    "receiptNo": TextEditingController(),
  };

  fetchReceiptAndPrint(BuildContext context) async {
    loading = true;
    var receipt = textFieldControllers["receiptNo"].text;
    notifyListeners();
    if (receipt != null) {
      receiptsService.fetchReceiptDetails(receipt).then((value) async {
        if (value["id_no"] == null) {
          showError(context);
        }else{
          try{
            await receiptsService.printReceipt(value);
          }catch(e){
            print(e);
            showError(context);
          }
        }
      }).catchError((err) {
        print(err);
        loading = false;
        showError(context);
      }).whenComplete(() {
        loading = false;
        notifyListeners();
      });
    }
  }

  showError(BuildContext context) {
    ScaffoldMessenger.maybeOf(context).showSnackBar(const SnackBar(
      content: Text('Failed to get receipt details'),
    ));
  }
}
