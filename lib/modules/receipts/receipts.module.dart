import 'package:bfastui/adapters/module.dart';
import 'package:bfastui/adapters/router.dart';
import 'package:bfastui/bfastui.dart';
import 'package:bus_poa/modules/receipts/pages/print_receipts.page.dart';
import 'package:bus_poa/modules/receipts/states/receipts.state.dart';

class ReceiptsModule extends BFastUIChildModule{
  @override
  void initRoutes(String moduleName) {
       BFastUI.navigation(moduleName: moduleName)
        .addRoute(BFastUIRouter(
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