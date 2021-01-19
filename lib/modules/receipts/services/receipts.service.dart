import 'package:bfast/bfast.dart';
import 'package:bus_poa/modules/receipts/services/printer.service.dart';

class ReceiptsService {
  final serverUrl = "https://buspoa.co.tz/buspoa/website/Payment/getTicketJson";
  final printerService = PrinterService();

  Future fetchReceiptDetails(String receipt) async {
    return await BFast.functions().request(serverUrl + "/" + receipt).get();
  }

  Future printReceipt(Map<String, dynamic> receipt) async {
    String receiptToPrint = "";

    receipt.entries.forEach((element) {
      receiptToPrint +=
          element.key.toString() + "\n" + element.value.toString() + "\n\n";
    });

    printerService
        .posPrint(
            data: receiptToPrint, id: receipt["id_no"], qr: receipt["id_no"])
        .then((value) {

        })
        .catchError((err) {

        });
  }
}
