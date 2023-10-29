import vehicle_package.*;
import throws_package.*;
import service_station_package.*;

public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicleArray = new Vehicle[2];
        vehicleArray[0] = new Car("Ferrari", 10);
        vehicleArray[1] = new Motorcycle("Lambroghi", 10); 
        for (Vehicle vh: vehicleArray) {
            try {
                vh.add("Model123", 111111);
            } catch (DuplicateModelNameException e) {
                System.out.println(e);
            }

            System.out.println(vh.getMarkName());

            System.out.println("AllModelName:");
            for (String st: vh.getAllModelName()) {
                System.out.println(st);
            }

            System.out.println("AllModelPrice:");
            for (double db: vh.getAllModelPrice()) {
                System.out.println(db);
            }

            System.out.println("Avarage price is " + VehicleStationService.getAvarageCostAllVehicle(vh));
            try {
                vh.setModelPriceByName("Model123", 100000);
            } catch (NoSuchModelNameException e) {
                System.out.println(e);
            }
            VehicleStationService.printAllVehicles(vh);
            try {
                vh.deleteModelByName("Model123");
            } catch (NoSuchModelNameException e) {
                System.out.println(e);
            }
            System.out.println("After deleting");
            VehicleStationService.printAllVehicles(vh);
            System.out.println();
        }
        
    }
}