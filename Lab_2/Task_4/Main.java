import vehicle_package.*;
import throws_package.*;

public class Main {
    public static void testCar() {
        Car cr = new Car("Ferrari", 10);

        System.out.println("марка -" + cr.getMarkName());
        cr.setMarkName("Lamborghini");
        System.out.println("марка -" + cr.getMarkName());

        System.out.println();

        System.out.println("Список моделей");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        try {
            cr.getModelByName("Huracan").setModelName("Galardo");
        }
        catch(NoSuchModelNameException e) {
            System.out.println(e);
        }

        System.out.println("Список моделей после изменения");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        System.out.println("Цена по названию");
        try {
            System.out.println(cr.getModelCostByName("Galardo"));
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        }
        
        try {
            cr.setModelCostByName("Galardo", 1233.142);
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        } catch (ModelPriceOutOfBoundsException ex) {
            System.out.println(ex);
        }

        System.out.println();

        System.out.println("Цена по названию после изменения");
        try {
            System.out.println(cr.getModelCostByName("Galardo"));
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        }

        try {
            System.out.println(cr.getModelCostByName("FQqfeqeffqe"));
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        }

        System.out.println();

        System.out.println("Список цен");
        for(double modelCost: cr.getAllModelCost()) {
            System.out.println(modelCost);
        }

        System.out.println();

        try {
            cr.addModel("Galardo", 14221.124);
        } catch(DuplicateModelNameException e) {
            System.out.println("Перехвачен " + e);
        }
        
        System.out.println("Список моделей после добавления");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        try {
            cr.deleteModelByName("Galardo");
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        }

        System.out.println("Список моделей после удаления");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }
    }
    public static void testMotorcycle() {
        Motorcycle mt = new Motorcycle("Ferrari", 10);

        System.out.println("марка -" + mt.getMarkName());
        mt.setMarkName("Lamborghini");
        System.out.println("марка -" + mt.getMarkName());

        System.out.println();

        System.out.println("Список моделей");
        for(String modelName: mt.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();
        try {
            mt.getModelByName("Huracan").setModelName("Galardo");
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        }
        

        System.out.println("Список моделей после изменения");
        for(String modelName: mt.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        System.out.println("Цена по названию");
        try {
            System.out.println(mt.getModelCostByName("Galardo"));
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        }
        
        try {
            mt.setModelCostByName("Galardo", 123.142);
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        } catch (ModelPriceOutOfBoundsException ex) {
            System.out.println(ex);
        }

        System.out.println();

        System.out.println("Цена по названию после изменения");
        try {
            System.out.println(mt.getModelCostByName("Galardo"));
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        }

        System.out.println();

        System.out.println("Список цен");
        for(double modelCost: mt.getAllModelCost()) {
            System.out.println(modelCost);
        }

        System.out.println();

        try {
            mt.addModel("TopG", 14221.124);
        } catch (DuplicateModelNameException e) {
            System.out.println("Перевачен " + e);
        }

        try {
            mt.addModel("TopG", 14221.124);
        } catch (DuplicateModelNameException e) {
            System.out.println("Перевачен " + e);
        }
        
        System.out.println("Список моделей после добавления");
        for(String modelName: mt.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();
        try {
            mt.deleteModelByName("Galardo");
        } catch (NoSuchModelNameException e) {
            System.out.println(e);
        }

        System.out.println("Список моделей после удаления");
        for(String modelName: mt.getAllModelName()) {
            System.out.println(modelName);
        }
    }
    public static void main(String[] args) {
        testCar();
        System.out.println("======================================================================");
        testMotorcycle();
        System.out.println("======================================================================");
        Car cr = new Car("Ferrari", 10);
        Motorcycle mt = new Motorcycle("Ferrari", 10);

        Vehicle vh = cr;

        vh.printAllModels();
    }
}