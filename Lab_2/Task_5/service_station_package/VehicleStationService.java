package service_station_package;

import vehicle_package.Vehicle;

public class VehicleStationService  {
    static public double getAvarageCostAllVehicle(Vehicle vh) {
        double cost = 0;
        if (vh.getSize() != 0) {
            double[] allModelCost = vh.getAllModelPrice();
            for(int i = 0; i < allModelCost.length; i++) {
                cost += allModelCost[i];
            }
            cost /= allModelCost.length;
        }
        
        return cost;
    }
    static public void printAllVehicles(Vehicle vh) {
        if (vh.getSize() != 0) {
            String[] allModelName = vh.getAllModelName();
            double[] allModelPrice = vh.getAllModelPrice();
            for (int i = 0; i < vh.getSize(); i++) {
                System.out.println(allModelName[i] + " " + allModelPrice[i]);
            }
        }
    }
}
