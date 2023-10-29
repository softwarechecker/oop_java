package vehicle_package;

import throws_package.*;

import java.util.Arrays;

public class Car implements Vehicle {
    private String markName;
    private Model[] marray;

    public Car(String markName) {
        this.markName = markName;
    }

    public Car(String markName, int size) {
        this.markName = markName;
        createRandomArray(size);
    }

    public void printAllModels() {
        for(Model md: marray) {
            System.out.println(md.modelName + " " + md.modelCost);
        }
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public Model getModelByName(String modelName) throws NoSuchModelNameException{
        int i;
        for(i = 0; i < marray.length; i++) {
            if (marray[i].modelName == modelName) return marray[i];
        }
        throw new NoSuchModelNameException(modelName);
    }

    public int getIdModelByName(String modelName) throws NoSuchModelNameException{
        int i;
        for(i = 0; i < marray.length; i++) {
            if (marray[i].modelName == modelName) return i;
        }
        throw new NoSuchModelNameException(modelName);
    }

    public double getModelCostByName(String modelName) throws NoSuchModelNameException{
        return getModelByName(modelName).modelCost;
    }

    public void setModelCostByName(String modelName, double modelCost) throws ModelPriceOutOfBoundsException, NoSuchModelNameException {
        if (modelCost < 10000 || modelCost > 1000000) throw new ModelPriceOutOfBoundsException(modelCost + " not in range (10000;1000000)");
        getModelByName(modelName).modelCost = modelCost;
    }
    
    public double[] getAllModelCost() {
        double[] allModelCost = new double[marray.length];
        for(int i = 0; i < marray.length; i++) {
            allModelCost[i] = marray[i].modelCost;
        }
        return allModelCost;
    }

    public String[] getAllModelName() {
        String[] modelAllNames = new String[marray.length];
        for(int i = 0; i < marray.length; i++) {
            modelAllNames[i] = marray[i].modelName;
        }
        return modelAllNames;
    }

    public void addModel(String modelName, double modelCost) throws ModelPriceOutOfBoundsException, DuplicateModelNameException {
        if (inTheArray(modelName)) throw new DuplicateModelNameException(modelName);
        Model md = new Model(modelName, modelCost);
        int tmpSize = marray.length;
        marray = Arrays.copyOf(marray, marray.length+1);
        marray[tmpSize] = md;
    }

    public void deleteModelByName(String modelName) throws NoSuchModelNameException {
        int id = getIdModelByName(modelName);
        Model[] tmpMarray = Arrays.copyOf(marray, marray.length-1);
        System.arraycopy(marray, 0, tmpMarray, 0, id);
        System.arraycopy(marray, id+1, tmpMarray, id, marray.length-id-1);
        marray = tmpMarray;
    }

    public class Model {
        private String modelName;
        private double modelCost;

        public Model(String modelName, double modelCost) throws ModelPriceOutOfBoundsException{
            if (modelCost < 10000.0 && modelCost > 1000000.0) throw new ModelPriceOutOfBoundsException(modelCost + " not in range (10000;1000000)");
            this.modelName = modelName;
            this.modelCost = modelCost;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }
    }

    private void createRandomArray(int size) {
        marray = new Model[size];
        marray[0] = new Model("Huracan", getIntRandNumber(10000, 1000000));
        for(int i = 1; i < marray.length; i++) {
            marray[i] = new Model(getRandName(), getIntRandNumber(10000, 1000000));
        }
    }

    private int getArraySize() {
        int j = 0;
        for(int i = 0; i< marray.length; i++) {
            if (marray[i] != null) j++;
        } 
        return j;
    }

    private int getIntRandNumber(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

    private String getRandName() {
        final String charArray = "ABCDEFG";
        String modelName = "";
        for(int i = 0; i < 7; i++) {
            modelName = modelName + charArray.charAt(getIntRandNumber(0, 6));
        }
        while (inTheArray(modelName)) {
            modelName = getRandName();
        }
            
        return modelName;
    }

    private boolean inTheArray(String modelName) {
        int i = 0;
        while (i < getArraySize()) {
            if (marray[i].modelName == modelName) {
                return true;
            }
            i++;
        }
        return false;
    }
}