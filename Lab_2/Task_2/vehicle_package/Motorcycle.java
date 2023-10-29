package vehicle_package;

public class Motorcycle {
    private static String charArray = "ABCDEFG";
    private String markName;
    private int size;
    private long lastModified;
    private Model head;

    public Motorcycle(String markName, int size) {
        this.markName = markName;

        addModel("Huracan", 12424.124);

        for(int i = 1; i < size; i++) {
            addModel(getRandName(), Math.random()*100);
        }
    }

    private String getRandName() {
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

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String[] getAllModelName() {
        String[] allModelName = new String[size];
        Model tmpHead = head;
        for(int i = 0; i < size; i++) {
            allModelName[i] = tmpHead.modelName;
            tmpHead = tmpHead.next;
        }
        return allModelName;
    }

    public Model getModelByName(String modelName) {
        Model tmpHead = head;
        for(int i = 0; i < size; i++) {
            if(tmpHead.modelName == modelName) break;
            tmpHead = tmpHead.next;
        }
        return tmpHead;
    }

    public double getModelCostByName(String modelName) {
        return getModelByName(modelName).modelCost;
    }

    public void setModelCostByName(String modelName, double modelCost) {
        getModelByName(modelName).modelCost = modelCost;
    }
    
    public double[] getAllModelCost() {
        double[] allModelCost = new double[size];
        Model tmpHead = head;
        for(int i = 0; i < size; i++) {
            allModelCost[i] = tmpHead.modelCost;
            tmpHead = tmpHead.next;
        }
        return allModelCost;
    }

    public void addModel(String modelName, double modelCost) {
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

    public int getIdModelByName(String modelName) {
        Model tmpHead = head;
        int i;
        for(i = 0; i < size; i++) {
            if(tmpHead.modelName == modelName) break;
        }
        return i;
    }

    public void deleteModelByName(String modelName) {
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
