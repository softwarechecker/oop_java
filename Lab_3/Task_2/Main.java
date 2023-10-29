import vehicle_package.*;
import throws_package.*;

import java.beans.VetoableChangeListener;
import java.io.*;
import java.util.Scanner;

import service_station_package.*;

public class Main {
    public static void main(String[] args) {
        String dataDirPath = "./data/";
        String byteFilePath = dataDirPath + "veh.dat";
        String txtFilePath = dataDirPath + "veh.txt";
        String serializeFilePath = dataDirPath + "veh.ser";

        Vehicle[] vehicleArray = new Vehicle[2];
        vehicleArray[0] = new Car("Ferrari", 10);
        vehicleArray[1] = new Motorcycle("Lambroghi", 10); 
        for (Vehicle vh: vehicleArray) {
            System.out.println("БАЙТОВЫЕ ДАННЫЕ");
            System.out.println("До сохранения");
            VehicleStationService.printAllVehicles(vh);
            try {
                FileOutputStream fos = new FileOutputStream(byteFilePath);
                VehicleStationService.outputVehicle(vh, fos);
                fos.close();
            } catch (IOException e) {
                System.out.println(e);
            }

            System.out.println("\nПосле сохранения");
            Vehicle vhFromByteFile = null;
            try {
                FileInputStream fis = new FileInputStream(byteFilePath);
                vhFromByteFile = VehicleStationService.inputVehicle(fis);
                fis.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            VehicleStationService.printAllVehicles(vhFromByteFile);
            System.out.println();

            System.out.println("СТРОКОВЫЕ ДАННЫЕ");
            System.out.println("До сохранения");
            VehicleStationService.printAllVehicles(vh);
            try {
                FileWriter fw = new FileWriter(txtFilePath);
                VehicleStationService.writeVehicle(vh, fw);
                fw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            
            System.out.println("\nПосле сохранения");
            Vehicle vhFromTxtFile = null;
            try {
                FileReader fr = new FileReader(txtFilePath);
                vhFromTxtFile = VehicleStationService.readVehicle(fr);
                fr.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            VehicleStationService.printAllVehicles(vhFromTxtFile);
            System.out.println();

            System.out.println("СЕРИАЛИЗАЦИЯ");
            System.out.println("Сериализуем...");
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializeFilePath));
                oos.writeObject(vh);
                oos.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println("После сериализации");
            Vehicle vhFromSerializeFile = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializeFilePath));
                Object oisObject = ois.readObject();
                if (oisObject instanceof Car) {
                    vhFromSerializeFile = (Car) oisObject; 
                } else if (oisObject instanceof Motorcycle) {
                    vhFromSerializeFile = (Motorcycle) oisObject;
                }

                ois.close();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e);
            }
            if (vhFromSerializeFile != null) {
                VehicleStationService.printAllVehicles(vhFromSerializeFile);
            }
            System.out.println("Введите данные класса");
            Vehicle vhFromSystemIn = VehicleStationService.readVehicle(new InputStreamReader(System.in));
            System.out.println("Считанные данные класса");
            VehicleStationService.writeVehicle(vhFromSystemIn, new OutputStreamWriter(System.out));
            System.out.println();
        }
    }
}