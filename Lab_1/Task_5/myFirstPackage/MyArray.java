package myFirstPackage;

public class MyArray {
    private int[] arr;

    public MyArray(int arrSize) {
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
