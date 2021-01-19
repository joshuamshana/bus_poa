import 'package:bfast/bfast.dart';
import 'package:bfast/bfast_config.dart';
import 'package:bfastui/bfastui.dart';
import 'package:bus_poa/app/app.module.dart';
import 'package:bus_poa/shared/config.dart';
import 'package:flutter/material.dart';

void main() {
  _connectWithBFastCloudProject();
  runApp(
    BFastUI.module(BusPoaApp()).start(
      initialPath: '/receipts',
      title: "PatPat",
      theme: ThemeData(
        fontFamily: Config.fontFamily,
        primarySwatch: Config.primaryColor,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
    ),
  );
}

void _connectWithBFastCloudProject() {
  BFast.init(AppCredentials('', ''));
}



