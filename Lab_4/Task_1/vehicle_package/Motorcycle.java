package vehicle_package;

import java.io.Serializable;
import java.util.Objects;

import throws_package.*;

public class Motorcycle implements Vehicle {
    private String markName;
    private int size;
    private transient long lastModified;
    // Head - в данном случае незначащий элемент (сторож)
    private Model head;

    {
       lastModified = System.currentTimeMillis(); 
       head = new Model();
       head.next = head;
       head.prev = head;
    }

    public Motorcycle(String markName) {
        this.markName = markName;
    }

    public Motorcycle(String markName, int size) {
        this.markName = markName;
        
        if (size != 0) {
            this.size = size;
            Model tmpModel = head;
            for (int i = 0; i < size; i++) {
                Model md = new Model("Model" + i, getIntRandNumber(10000, 1000000));
                tmpModel.next = md;
                md.prev = tmpModel;
                tmpModel = tmpModel.next;
            }
            head.prev = tmpModel;
            tmpModel.next = head;
        }
    }

    public Motorcycle(String markName, int size, String[] allModelName, double[] allModelPrice) {
        this.markName = markName;

        if (size != 0) {
            this.size = size;
            Model tmpModel = head;
            for (int i = 0; i < size; i++) {
                Model md = new Model(allModelName[i], allModelPrice[i]);
                tmpModel.next = md;
                md.prev = tmpModel;
                tmpModel = tmpModel.next;
            }
            head.prev = tmpModel;
            tmpModel.next = head;
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getSimpleName());
        sb.append(" Brand: ");
        sb.append(markName);
        sb.append(" Size: ");
        sb.append(getSize());
        return new String(sb);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Motorcycle)) {
            return false;
        }
        Motorcycle oMotorcycle = (Motorcycle) o;
        if (this.markName.equals(oMotorcycle.markName) && this.getSize() == oMotorcycle.getSize()) {
            Model tmpObject1 = this.head.next;
            Model tmpObject2 = oMotorcycle.head.next;
            for (int i = 0; i < this.getSize(); i++) {
                if ((tmpObject1.modelName.equals(tmpObject2.modelName) && (tmpObject1.modelPrice == tmpObject2.modelPrice)) == false) {
                    return false;
                }
                tmpObject1 = tmpObject1.next;
                tmpObject2 = tmpObject2.next;
            }
            return true;
        }
        else { return false; }
    }

    public int hashCode() {
        return Objects.hash(markName, size, lastModified, head);
    }

    public Object clone() {
        Motorcycle cloned = null;
        try {
            cloned = (Motorcycle) super.clone();
            cloned.head = new Model();
            Model tmpClonedHead = cloned.head.next;
            Model tmpHead = this.head;
            for (int i = 0; i < getSize(); i++) {
                tmpClonedHead = tmpHead.clone();
                tmpHead = tmpHead.next;
                tmpClonedHead = tmpClonedHead.next;
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return cloned;
    }

    public long getLastModified() {
        return lastModified;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String newMarkName) {
        markName = newMarkName;
    }

    private class Model implements Serializable {
        private String modelName;
        private double modelPrice;
        private Model prev;
        private Model next;

        private Model() {}

        public Model clone() {
            Model md = null;
            try {
                md = (Model) super.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println(e);
            }
            return md;
        }

        private Model(String modelName, double modelPrice) {
            if (modelPrice < 10000.0 && modelPrice > 1000000.0) throw new ModelPriceOutOfBoundsException(modelPrice + " not in range (10000;1000000)");
            this.modelName = modelName;
            this.modelPrice = modelPrice;
        }
    }

    public void setModelNameByName(String oldModelName, String newModelName) throws NoSuchModelNameException, DuplicateModelNameException{
        if (oldModelName.equals(newModelName)) throw new DuplicateModelNameException(oldModelName);
        getModelByName(oldModelName).modelName = newModelName;
        lastModified = System.currentTimeMillis();
    }

    public String[] getAllModelName() {
        String[] allModelName = new String[size];
        Model tmpHead = head.next;
        for(int i = 0; i < size; i++) {
            allModelName[i] = tmpHead.modelName;
            tmpHead = tmpHead.next;
        }
        return allModelName;
    }

    public double getModelPriceByName(String modelName) throws NoSuchModelNameException{
        return getModelByName(modelName).modelPrice;
    }

    public void setModelPriceByName(String modelName, double newModelPrice) throws NoSuchModelNameException {
        if (newModelPrice < 10000 || newModelPrice > 1000000) throw new ModelPriceOutOfBoundsException(newModelPrice + " not in range (10000;1000000)");
        getModelByName(modelName).modelPrice = newModelPrice;
        lastModified = System.currentTimeMillis(); 
    }

    public double[] getAllModelPrice() {
        double[] allModelPrice = new double[size];
        Model tmpHead = head.next;
        for(int i = 0; i < size; i++) {
            allModelPrice[i] = tmpHead.modelPrice;
            tmpHead = tmpHead.next;
        }
        return allModelPrice;
    }

    public void add(String modelName, double modelPrice) throws ModelPriceOutOfBoundsException, DuplicateModelNameException{
        if (inTheArray(modelName)) throw new DuplicateModelNameException(modelName);
        
        Model md = new Model(modelName, modelPrice);

        head.prev.next = md;
        md.prev = head.prev;
        md.next = head;
        head.prev = md;

        size++;
        lastModified = System.currentTimeMillis();
    }

    public void deleteModelByName(String modelName) throws NoSuchModelNameException {
        Model tmpModel = getModelByName(modelName);

        tmpModel.prev.next = tmpModel.next;
        tmpModel.next.prev = tmpModel.prev;

        size--;
        lastModified = System.currentTimeMillis();
    }

    public int getSize() {
        return size;
    }

    private Model getModelByName(String modelName) throws NoSuchModelNameException {
        Model tmpHead = head.next;
        for(int i = 0; i < size; i++) {
            if(tmpHead.modelName.equals(modelName)) return tmpHead;
            tmpHead = tmpHead.next;
        }
        throw new NoSuchModelNameException(modelName);
    }

    private boolean inTheArray(String modelName) {
        Model tmpHead = head.next;
        for (int i = 0; i < size; i++) {
            if (tmpHead.modelName.equals(modelName)) {
                return true;
            }
            tmpHead = tmpHead.next;
        }
        return false;
    }

    private int getIntRandNumber(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}
