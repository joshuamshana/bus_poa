import 'package:bfastui/adapters/main-module.adapter.dart';
import 'package:bfastui/adapters/router.adapter.dart';
import 'package:bfastui/bfastui.dart';
import 'package:bus_poa/modules/receipts/receipts.module.dart';

class BusPoaApp extends MainModuleAdapter {
  @override
  void initRoutes(String moduleName) {
    BFastUI.navigation(moduleName: moduleName)
        .addRoute(
          RouterAdapter('/receipts',
              module: BFastUI.childModule(ReceiptsModule())),
        );
        
  }

  @override
  void initStates(String moduleName) {}
}
