package vehicle_package;

import throws_package.*;

public interface Vehicle {
    public String getMarkName();
    public void setMarkName(String newMarkName);
    public void setModelNameByName(String oldModelName, String newModelName) throws NoSuchModelNameException, DuplicateModelNameException;
    public String[] getAllModelName();
    public double getModelPriceByName(String modelName) throws NoSuchModelNameException;
    public void setModelPriceByName(String modelName, double newModelPrice) throws NoSuchModelNameException;
    public double[] getAllModelPrice();
    public void add(String modelName, double modelPrice) throws ModelPriceOutOfBoundsException, DuplicateModelNameException;
    public void deleteModelByName(String modelName) throws NoSuchModelNameException;
    public int getSize();
}
