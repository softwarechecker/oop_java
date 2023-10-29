import vehicle_package.*;
import throws_package.*;

import java.io.*;

import service_station_package.*;

public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicleArray = new Vehicle[2];
        vehicleArray[0] = new Car("Ferrari", 10);
        vehicleArray[1] = new Motorcycle("Lambroghi", 10); 
        for (Vehicle vh: vehicleArray) {
            System.out.println("БАЙТОВЫЕ ДАННЫЕ");
            System.out.println("До сохранения");
            VehicleStationService.printAllVehicles(vh);
            try {
                FileOutputStream fos = new FileOutputStream("./data/veh.dat");
                VehicleStationService.outputVehicle(vh, fos);
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }

            System.out.println("\nПосле сохранения");
            Vehicle vhFromByteFile = null;
            try {
                FileInputStream fis = new FileInputStream("./data/veh.dat");
                vhFromByteFile = VehicleStationService.inputVehicle(fis);
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
            VehicleStationService.printAllVehicles(vhFromByteFile);
            System.out.println();

            System.out.println("СТРОКОВЫЕ ДАННЫЕ");
            System.out.println("До сохранения");
            VehicleStationService.printAllVehicles(vh);
            try {
                FileWriter fw = new FileWriter("./data/veh.txt");
                VehicleStationService.writeVehicle(vh, fw);
                fw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            
            System.out.println("\nПосле сохранения");
            Vehicle vhFromTxtFile = null;
            try {
                FileReader fr = new FileReader("./data/veh.txt");
                vhFromTxtFile = VehicleStationService.readVehicle(fr);
                fr.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            VehicleStationService.printAllVehicles(vhFromTxtFile);
            System.out.println();
            
        }
    }
}