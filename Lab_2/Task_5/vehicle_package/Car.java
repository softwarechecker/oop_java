package vehicle_package;

import throws_package.*;

import java.util.Arrays;

public class Car implements Vehicle {
    private String markName;
    private Model[] marray;

    public Car(String markName) {
        this.markName = markName;
        this.marray = new Model[0];
    }

    public Car(String markName, int size) {
        this.markName = markName;
        marray = new Model[size];
        for(int i = 0; i < size; i++) {
            marray[i] = new Model("Model" + i, getIntRandNumber(10000, 1000000));
        }
    }
 
    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    private class Model {
        private String modelName;
        private double modelPrice;

        private Model(String modelName, double modelPrice) {
            if (modelPrice < 10000.0 && modelPrice > 1000000.0) throw new ModelPriceOutOfBoundsException(modelPrice + " not in range (10000;1000000)");
            this.modelName = modelName;
            this.modelPrice = modelPrice;
        }
    }
    
    public void setModelNameByName(String oldModelName, String newModelName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (oldModelName.equals(newModelName)) throw new DuplicateModelNameException(oldModelName);
        getModelByName(oldModelName).modelName = newModelName;
    }

    public String[] getAllModelName() {
        String[] modelAllNames = new String[marray.length];
        for(int i = 0; i < marray.length; i++) {
            modelAllNames[i] = marray[i].modelName;
        }
        return modelAllNames;
    }

    public double getModelPriceByName(String modelName) throws NoSuchModelNameException{
        return getModelByName(modelName).modelPrice;
    }

    public void setModelPriceByName(String modelName, double modelPrice) throws NoSuchModelNameException{
        if (modelPrice < 10000 || modelPrice > 1000000) throw new ModelPriceOutOfBoundsException(modelPrice + " not in range (10000;1000000)");
        getModelByName(modelName).modelPrice = modelPrice;
    }

    public double[] getAllModelPrice() {
        double[] allModelPrice = new double[marray.length];
        for(int i = 0; i < marray.length; i++) {
            allModelPrice[i] = marray[i].modelPrice;
        }
        return allModelPrice;
    }

    public void add(String modelName, double modelPrice) throws DuplicateModelNameException {
        if (inTheArray(modelName)) throw new DuplicateModelNameException(modelName);

        Model md = new Model(modelName, modelPrice);
        marray = Arrays.copyOf(marray, marray.length+1);
        marray[marray.length - 1] = md;
    }

    public void deleteModelByName(String modelName) throws NoSuchModelNameException {
        int id = getIdModelByName(modelName);
        System.arraycopy(marray, id+1, marray, id, marray.length-id-1);
        marray = Arrays.copyOf(marray, marray.length-1);
    }

    public int getSize() {
        return marray.length;
    }

    private Model getModelByName(String modelName) throws NoSuchModelNameException{
        for(int i = 0; i < marray.length; i++) {
            if (marray[i].modelName.equals(modelName)) return marray[i];
        }
        throw new NoSuchModelNameException(modelName);
    }

    private int getIdModelByName(String modelName) throws NoSuchModelNameException{
        for(int i = 0; i < marray.length; i++) {
            if (marray[i].modelName.equals(modelName)) return i;
        }
        throw new NoSuchModelNameException(modelName);
    }

    private boolean inTheArray(String modelName) {
        for(Model md: marray) {
            if (md.modelName.equals(modelName)) return true;
        }
        return false;
    }

    private int getIntRandNumber(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}