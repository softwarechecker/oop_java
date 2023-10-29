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

        System.out.println("Список моделей после изменения");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }

        System.out.println();

        System.out.println("Цена по названию");
        System.out.println(cr.getModelCostByName("Huracan"));
        cr.setModelCostByName("Huracan", 123.142);

        System.out.println();

        System.out.println("Цена по названию после изменения");
        System.out.println(cr.getModelCostByName("Huracan"));

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

        cr.deleteModelByName("Huracan");

        System.out.println("Список моделей после удаления");
        for(String modelName: cr.getAllModelName()) {
            System.out.println(modelName);
        }


    }
}
