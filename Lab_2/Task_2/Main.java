import vehicle_package.*;

public class Main {
    public static void main(String[] args) {
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

        cr.getElementByName("Huracan").setModelName("Galardo");

        System.out.println("Список моделей после изменения");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        System.out.println("Цена по названию");
        System.out.println(cr.getModelCostByName("Galardo"));
        cr.setModelCostByName("Galardo", 123.142);

        System.out.println();

        System.out.println("Цена по названию после изменения");
        System.out.println(cr.getModelCostByName("Galardo"));

        System.out.println();

        System.out.println("Список цен");
        for(double modelCost: cr.getAllModelCost()) {
            System.out.println(modelCost);
        }

        System.out.println();

        cr.addModel("TopG", 14221.124);
        System.out.println("Список моделей после добавления");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        cr.deleteModelByName("Galardo");

        System.out.println("Список моделей после удаления");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println("\nМотоциклы");

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

        mt.getElementByName("Huracan").setModelName("Galardo");

        System.out.println("Список моделей после изменения");
        for(String modelName: mt.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        System.out.println("Цена по названию");
        System.out.println(mt.getModelCostByName("Galardo"));
        mt.setModelCostByName("Galardo", 123.142);

        System.out.println();

        System.out.println("Цена по названию после изменения");
        System.out.println(mt.getModelCostByName("Galardo"));

        System.out.println();

        System.out.println("Список цен");
        for(double modelCost: mt.getAllModelCost()) {
            System.out.println(modelCost);
        }

        System.out.println();

        mt.addModel("TopG", 14221.124);
        System.out.println("Список моделей после добавления");
        for(String modelName: mt.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        mt.deleteModelByName("Galardo");

        System.out.println("Список моделей после удаления");
        for(String modelName: mt.getAllModelName()) {
            System.out.println(modelName);
        }
    }
}
