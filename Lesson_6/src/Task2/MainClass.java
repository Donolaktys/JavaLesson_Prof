package Task2;

public class MainClass {
    public static void main(String[] args) {
        int[] array = {1, 1, 1, 4, 3};

        System.out.println(verification(array));
    }

    public static boolean verification(int[] arr) {
        boolean result = false;
        boolean oneOrFour = true;
        boolean checkChange = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 1 && arr[i] != 4) {
                oneOrFour = false;
            } else {
                if (i != 0 && arr[i] != arr[i - 1]) {
                    checkChange = true;
                }
            }
        }
        return (oneOrFour && checkChange);
    }
}
