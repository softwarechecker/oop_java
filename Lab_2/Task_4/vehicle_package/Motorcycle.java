package vehicle_package;

import throws_package.*;

public class Motorcycle implements Vehicle {
    private String markName;
    private int size;
    private long lastModified;
    private Model head;

    {
       lastModified = System.currentTimeMillis()/1000; 
    }

    public Motorcycle(String markName) {
        this.markName = markName;
    }

    public Motorcycle(String markName, int size) {
        this.markName = markName;
        createRandomList(size);
    }

    public void printAllModels() {
        Model tmpHead = head;
        int i = 0;
        while(i < size) {
            System.out.println(tmpHead.modelName + " " + tmpHead.modelCost);
            tmpHead = tmpHead.next;
            i++;
        }
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public long getLastModified() {
        return lastModified;
    }

    public Model getModelByName(String modelName) throws NoSuchModelNameException {
        Model tmpHead = head;
        for(int i = 0; i < size; i++) {
            if(tmpHead.modelName == modelName) return tmpHead;
            tmpHead = tmpHead.next;
        }
        throw new NoSuchModelNameException(modelName);
    }

    public int getIdModelByName(String modelName) throws NoSuchModelNameException {
        Model tmpHead = head;
        int i;
        for(i = 0; i < size; i++) {
            if(tmpHead.modelName == modelName) return i;
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
        double[] allModelCost = null;
        if (size != 0) {
            allModelCost = new double[size];
            Model tmpHead = head;
            for(int i = 0; i < size; i++) {
                allModelCost[i] = tmpHead.modelCost;
                tmpHead = tmpHead.next;
            }
        }
        return allModelCost;
    }

    public String[] getAllModelName() {
        String[] allModelName = null;
        if (size != 0) {
            allModelName = new String[size];
            Model tmpHead = head;
            for(int i = 0; i < size; i++) {
                allModelName[i] = tmpHead.modelName;
                tmpHead = tmpHead.next;
            }
        }
        return allModelName;
    }

    public void addModel(String modelName, double modelCost) throws ModelPriceOutOfBoundsException, DuplicateModelNameException{
        if (inTheArray(modelName)) {
            throw new DuplicateModelNameException(modelName);
        }
        Model md = new Model(modelName, modelCost);
        if (size == 0) {
            head = md;
            md.next = md;
            md.prev = md;
        }
        else {
            md.prev = head.prev;
            md.next = head;
            head.prev.next = md;
            head.prev = md;
        }

        lastModified = System.currentTimeMillis()/1000;
        
        size++;
    }

    public void deleteModelByName(String modelName) throws NoSuchModelNameException {
        if(size == 0) {
            return;
        }
        else {
            Model tmp = getModelByName(modelName);
            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;
            if (tmp == head) {
                head = tmp.next;
            }
            size--;
        }
    }

    public class Model {
        private String modelName;
        private double modelCost;
        private Model prev;
        private Model next;

        public Model(String modelName, double modelCost) throws ModelPriceOutOfBoundsException{
            if (modelCost < 10000.0 && modelCost > 1000000.0) throw new ModelPriceOutOfBoundsException(modelCost + " not in range (10000;1000000)");
            this.modelName = modelName;
            this.modelCost = modelCost;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }
    }

    private void createRandomList(int size) {
        this.size = size;
        head = new Model("Huracan", 12424.124);
        head.next = head;
        head.prev = head;
        for(int i = 1; i < size; i++) {
            Model md = new Model(getRandName(), getIntRandNumber(10000, 1000000));
            md.prev = head.prev;
            md.next = head;
            head.prev.next = md;
            head.prev = md;
        }
    }

    private String getRandName() {
        final String charArray = "ABCDEFG";
        String modelName = "";
        for(int i = 0; i < 7; i++) {
            modelName = modelName + charArray.charAt(getIntRandNumber(0, 6));
        }
        Model tmpHead = head;
        for(int i = 0; i < size; i++) {
            if(tmpHead.modelName == modelName) {
                modelName = getRandName();
                break;
            };
            tmpHead = tmpHead.next;
        }
            
        return modelName;
    }

    private int getIntRandNumber(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

    private boolean inTheArray(String modelName) {
        Model tmpHead = head;
        int i = 0;
        while (i < size) {
            if (tmpHead.modelName == modelName) {
                return true;
            }
            tmpHead = tmpHead.next;
            i++;
        }
        return false;
    }
}
