import 'package:bfast/bfast.dart';
import 'package:bfastui/bfastui.dart';
import 'package:bus_poa/modules/receipts/services/printer.service.dart';
import 'package:bus_poa/modules/receipts/states/receipts.state.dart';

class ReceiptsService {
  final serverUrl =
      "https://buspoatz.co.tz/buspoa/website/Payment/getTicketJson";
  final printerService = PrinterService();

  Future fetchReceiptDetails(String receipt) async {
    return await BFast.functions().request(serverUrl + "/" + receipt).get();
  }

  Future printReceipt(Map<String, dynamic> receipt) async {
    String receiptToPrint = "";
    receiptToPrint += '-------------------------------\n';
    receiptToPrint +=
        "TRA VFD: ${receipt["tra_vfd"]} \n-------------------------------\n";
    receiptToPrint += "Ticket No : ${receipt["id_no"]} \n";
    receiptToPrint += '-------------------------------\n';
    receiptToPrint += 'BUS POA \n-------------------------------\n';

    // receipt.entries.forEach((element) {
    receiptToPrint += 'Bus Operator: ${receipt["owner"]} \n';
    receiptToPrint += 'Trip Route Name : ${receipt["trip_route_name"]} \n';
    receiptToPrint += 'Departure Date : ${receipt["assign_date"]} \n';
    receiptToPrint += 'Passenger Name : ${receipt["passenger_name"]} \n';
    receiptToPrint += 'Seat No : ${receipt["seat_numbers"]} \n';
    receiptToPrint += 'Pick up Point: ${receipt["pickup_trip_location"]} \n';
    receiptToPrint += 'Drop Point : ${receipt["drop_trip_location"]} \n';
    receiptToPrint += "Price: ${receipt["price"]} \n";
    receiptToPrint += "Registration No: ${receipt["reg_no"]} \n";
    receiptToPrint += "Passenger No : ${receipt['phone']} \n";
    receiptToPrint += "Contact No : +255 753 320 249 \n\n";
    receiptToPrint += "Reporting time 45min before departure. \n\n";

    // receiptToPrint += element.key.toString() + " : " + element.value.toString() + "\n";
    // });

    printerService.posPrint(
        data: receiptToPrint, id: receipt["id_no"], qr: receipt["id_no"]);
  }
}
