package vehicle_package;

import throws_package.*;

public interface Vehicle {
    String getMarkName();
    void setMarkName(String newMarkName);
    void setModelNameByName(String oldModelName, String newModelName) throws NoSuchModelNameException;
    String[] getAllModelName();
    double getModelPriceByName(String modelName) throws NoSuchModelNameException;
    void setModelPriceByName(String modelName, double newModelPrice) throws NoSuchModelNameException;
    double[] getAllModelPrice();
    void add(String modelName, double modelPrice) throws ModelPriceOutOfBoundsException, DuplicateModelNameException;
    void deleteModelByName(String modelName) throws NoSuchModelNameException;
    int getSize();
}
