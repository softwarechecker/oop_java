class Main {
    public static void main(String[] args) {      
        for(char ch: args[0].toCharArray()) {
            System.out.println(ch);
        }
    }
    /*
    public static void main(String[] args) {
        for(int i = 0; i < args[0].length(); i++) {
            System.out.println(args[0].charAt(i));
        }
    }
    */
}
