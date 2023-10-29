package vehicle_package;

import java.util.Arrays;

public class Car {
    private static String charArray = "ABCDEFG";
    private String markName;
    private Model[] marray;

    public Car(String markName, int marraySize) {
        this.markName = markName;
        marray = new Model[marraySize];
        marray[0] = new Model("Huracan", Math.random());
        for(int i = 1; i < marray.length; i++) {
            marray[i] = new Model(getRandName(), Math.random()*100);
        }
    }

    private String getRandName() {
        String modelName = "";
        for(int i = 0; i < 7; i++) {
            modelName = modelName + charArray.charAt(getIntRandNumber(0, 6));
        }
        int i = 0;
        while (i < getArraySize()) {
            if(marray[i].modelName == modelName) {
                modelName = getRandName();
                break;
            }
            i++;
        }
            
        return modelName;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String[] getAllModelName() {
        String[] modelAllNames = new String[marray.length];
        for(int i = 0; i < marray.length; i++) {
            modelAllNames[i] = marray[i].modelName;
        }
        return modelAllNames;
    }

    public Model getModelByName(String modelName) {
        int i;
        for(i = 0; i < marray.length; i++) {
            if (marray[i].modelName == modelName) break;
        }
        return marray[i];
    }

    public double getModelCostByName(String modelName) {
        return getModelByName(modelName).modelCost;
    }

    public void setModelCostByName(String modelName, double modelCost) {
        getModelByName(modelName).modelCost = modelCost;
    }
    
    public double[] getAllModelCost() {
        double[] allModelCost = new double[marray.length];
        for(int i = 0; i < marray.length; i++) {
            allModelCost[i] = marray[i].modelCost;
        }
        return allModelCost;
    }

    public void addModel(String modelName, double modelCost) {
        int tmpSize = marray.length;
        marray = Arrays.copyOf(marray, marray.length+1);
        marray[tmpSize] = new Model(modelName, modelCost);
    }

    public int getIdModelByName(String modelName) {
        int i;
        for(i = 0; i < marray.length; i++) {
            if (marray[i].modelName == modelName) break;
        }
        return i;
    }

    public void deleteModelByName(String modelName) {
        int id = getIdModelByName(modelName);
        Model[] tmpMarray = Arrays.copyOf(marray, marray.length-1);
        System.arraycopy(marray, 0, tmpMarray, 0, id);
        System.arraycopy(marray, id+1, tmpMarray, id, marray.length-id-1);
        marray = tmpMarray;
    }

    public int getArraySize() {
        int j = 0;
        for(int i = 0; i< marray.length; i++) {
            if (marray[i] != null) j++;
        } 
        return j;
    }

    public class Model {
        private String modelName;
        private double modelCost;

        Model(String modelName, double modelCost) {
            this.modelName = modelName;
            this.modelCost = modelCost;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }
    }

    private int getIntRandNumber(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}