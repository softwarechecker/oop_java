import myFirstPackage.MyArray;

class Main {
    public static void main(String[] args) {
        MyArray myArr = new MyArray(11);
        myArr.printArray();
        myArr.setNumberById(0, 33);
        double avg = myArr.getAverage();
        System.out.println(avg);
        myArr.printArray();
    }
}