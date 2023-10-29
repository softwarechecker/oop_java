package service_station_package;

import java.io.*;

import vehicle_package.*;

public class VehicleStationService  {
    static public double getAvarageCostAllVehicle(Vehicle vh) {
        double cost = 0;
        double[] allModelPrice = vh.getAllModelPrice();
        for(int i = 0; i < allModelPrice.length; i++) {
            cost += allModelPrice[i];
        }
        cost /= allModelPrice.length;
        
        return cost;
    }
    static public void printAllVehicles(Vehicle vh) {
        String[] allModelName = vh.getAllModelName();
        double[] allModelPrice = vh.getAllModelPrice();
        for (int i = 0; i < vh.getSize(); i++) {
            System.out.println(allModelName[i] + " " + allModelPrice[i]);
        }
    }
    static public void outputVehicle(Vehicle vh, OutputStream out) {
        DataOutputStream dos = new DataOutputStream(out);
        try {
            String vhClass = vh.getClass().getSimpleName().toString();
            dos.writeInt(vhClass.length());
            dos.write(vhClass.getBytes());

            String vhMarkName = vh.getMarkName();
            dos.writeInt(vhMarkName.length());
            dos.write(vhMarkName.getBytes());

            int vhSize = vh.getSize();
            dos.writeInt(vhSize);

            String[] vhAllModelName = vh.getAllModelName();
            double[] vhAllModelPrice = vh.getAllModelPrice();

            for (int i = 0; i < vhSize; i++) {
                dos.writeInt(vhAllModelName[i].length());
                dos.write(vhAllModelName[i].getBytes());
                dos.writeDouble(vhAllModelPrice[i]);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    static public Vehicle inputVehicle(InputStream in) {
        DataInputStream dis = new DataInputStream(in);
        Vehicle vh = null;
        try {
            String vhClass = readString(dis);
            String vhMarkName = readString(dis);
            int vhSize = dis.readInt();

            String[] vhAllModelName = new String[vhSize];
            double[] vhAllModelPrice = new double[vhSize];
            for (int i = 0; i < vhSize; i++) {
                vhAllModelName[i] = readString(dis);
                vhAllModelPrice[i] = dis.readDouble();
            }

            switch (vhClass) {
                case "Car":
                    vh = new Car(vhMarkName, vhSize, vhAllModelName, vhAllModelPrice);
                    break;
                case "Motorcycle":
                    vh = new Motorcycle(vhMarkName, vhSize, vhAllModelName, vhAllModelPrice);
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return vh;
    }
    static public void writeVehicle(Vehicle vh, Writer out) {
        PrintWriter pw = new PrintWriter(out);
        pw.write(vh.getClass().getSimpleName()+'\n');
        pw.write(vh.getMarkName()+'\n');
        int vhSize = vh.getSize();
        pw.write(Integer.toString(vhSize)+'\n');

        String[] vhAllModelName = vh.getAllModelName();
        double[] vhAllModelPrice = vh.getAllModelPrice();
        for (int i = 0; i < vhSize; i++) {
            pw.write(vhAllModelName[i] + '\n');
            pw.write(Double.toString(vhAllModelPrice[i])+'\n');
        }
        pw.flush();
    }
    static public Vehicle readVehicle(Reader in) {
        BufferedReader br = new BufferedReader(in);
        Vehicle vh = null;
        try {
            String vhClass = br.readLine();
            String vhMarkName = br.readLine();
            int vhSize = Integer.parseInt(br.readLine());
            String[] vhAllModelName = new String[vhSize];
            double[] vhAllModelPrice = new double[vhSize];
            for (int i = 0; i < vhSize; i++) {
                vhAllModelName[i] = br.readLine();
                vhAllModelPrice[i] = Double.parseDouble(br.readLine());
            }
            switch (vhClass) {
                case "Car":
                    vh = new Car(vhMarkName, vhSize, vhAllModelName, vhAllModelPrice);
                    break;
                case "Motorcycle":
                    vh = new Motorcycle(vhMarkName, vhSize, vhAllModelName, vhAllModelPrice);
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return vh;
    }

    static private String readString(DataInputStream dis) {
        byte[] buffer = null;
        try {
            int size = dis.readInt();
            buffer = new byte[size];
            for (int i = 0; i < size; i++) {
                buffer[i] = dis.readByte();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return new String(buffer);
    }
}
