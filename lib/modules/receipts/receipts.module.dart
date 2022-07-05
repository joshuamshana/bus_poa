import 'package:bfastui/adapters/child-module.adapter.dart';
import 'package:bfastui/adapters/router.adapter.dart';
import 'package:bfastui/bfastui.dart';
import 'package:bus_poa/modules/receipts/pages/print_receipts.page.dart';
import 'package:bus_poa/modules/receipts/states/receipts.state.dart';

class ReceiptsModule extends ChildModuleAdapter{
  @override
  void initRoutes(String moduleName) {
       BFastUI.navigation(moduleName: moduleName)
        .addRoute(RouterAdapter(
          '/',
          guards: [],
          page: (context, args) => PrintReceiptsPage(),
        ));
  }
  
  @override
  void initStates(String moduleName) {
      BFastUI.states(moduleName: moduleName)
        .addState((_) => ReceiptsState());
  }

  @override
  String moduleName() {
    return 'receipts';
  }
}