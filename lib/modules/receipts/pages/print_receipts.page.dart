import 'package:bfastui/adapters/page.adapter.dart';
import 'package:bfastui/bfastui.dart';
import 'package:bus_poa/modules/receipts/components/search_receipt.component.dart';
import 'package:bus_poa/shared/config.dart';
import 'package:flutter/material.dart';

class PrintReceiptsPage extends PageAdapter {
  @override
  Widget build(args) {
    return BFastUI.component().custom((context) => Scaffold(
        backgroundColor: Config.primaryColor,
        body: ListView(children: [
          Container(
            margin: EdgeInsets.fromLTRB(0, 24, 0, 0),
            decoration: BoxDecoration(color: Config.primaryColor),
            child: Center(
              child: Text(
                "BUS POA",
                style: TextStyle(color: Colors.white, fontSize: 45),
              ),
            ),
          ),
          receiptsForm()
        ])));
  }
}
