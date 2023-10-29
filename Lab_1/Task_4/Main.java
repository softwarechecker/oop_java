class Main {
    public static void main(String[] args) {
        MyArray msc1 = new MyArray(11);
        msc1.printArray();
        msc1.setNumberById(0, 33);
        double avg = msc1.getAverage();
        System.out.println(avg);
        msc1.printArray();
    }
}

class MyArray {
    private int[] arr;

    MyArray(int arrSize) {
        arr = new int[arrSize];
        for(int i = 0; i < arrSize; i++) {
            arr[i] = getIntRandNumber(0, 30);
        }
    }

    public int getNumberById(int id) {
        return arr[id];
    }

    public void setNumberById(int id, int number) {
        arr[id] = number;
    }

    public double getAverage() {
        double average = 0;
        for(int i: arr) {
            average += i;
        }
        average /= arr.length;
        return average;
    }

    public void printArray() {
        System.out.print("[ ");
        for(int i: arr) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }

    private int getIntRandNumber(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}
